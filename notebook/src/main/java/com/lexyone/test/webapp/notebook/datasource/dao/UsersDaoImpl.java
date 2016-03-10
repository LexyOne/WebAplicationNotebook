package com.lexyone.test.webapp.notebook.datasource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.ConnectionPool;
import com.lexyone.test.webapp.notebook.datasource.SqlRuntimeException;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class UsersDaoImpl implements UsersDao {
	
	private static final String SELECT_MAX_ID_SQL = "select MAX(ID) from USERS";
	private static final String SELECT_ALL_SQL = "select ID, SURNAME, NAME, AGE, GENDER, PHONE from USERS ";
	private static final String SELECT_ORDER = " order by ID";
	private static final String SELECT_BY_ID_SQL = "select ID, SURNAME, NAME, AGE, GENDER, PHONE from USERS WHERE ID = ?";
	private static final String UPDATE_SQL = "update USERS set SURNAME = ?, NAME = ?, AGE = ?, GENDER = ?, PHONE = ? where (ID = ?)";
	private static final String INSERT_SQL = "insert into USERS (SURNAME, NAME, AGE, GENDER, PHONE) values (?, ?, ?, ?, ?)";
	
	public Long getMaxUserId() {
		Long maxId = (long) 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_MAX_ID_SQL);
			while(rs.next()){
				maxId = rs.getLong("MAX");
			}
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new SqlRuntimeException();
			}
			if(conn != null) ConnectionPool.getInstance().putback(conn);
		}

		if(maxId == 0) throw new SqlRuntimeException();
		return maxId;
	}

	public void saveUser(User user) {
		if(DaoImpl.isPersisted(user)) {
			updateUser(user);
		} else {
			addUser(user);
		}
	}
	
	private void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			ps = conn.prepareStatement(INSERT_SQL);
			
			setUser(ps, user);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				throw new SqlRuntimeException();
			}
			if(conn != null) ConnectionPool.getInstance().putback(conn);
		}
		
		if(res == 0) throw new SqlRuntimeException();
		
		user.setId(getMaxUserId());
	}

	private void updateUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		int res = 0;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			ps = conn.prepareStatement(UPDATE_SQL);
			
			setUser(ps, user);
			ps.setLong(6, user.getId());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				throw new SqlRuntimeException();
			}
			if(conn != null) ConnectionPool.getInstance().putback(conn);
		}
		
		if(res == 0) throw new SqlRuntimeException();
	}
	
	private void setUser(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getSurname());
		ps.setString(2, user.getName());
		ps.setInt(3, user.getAge());
		ps.setString(4, user.getGender().getId());
		ps.setString(5, user.getPhone().toString());
	}
	
	public User getUser(Long id) {
		LinkedList<User> users = new LinkedList<User>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			ps = conn.prepareStatement(SELECT_BY_ID_SQL);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				users.add(loadUser(rs));
			}
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e) {
				throw new SqlRuntimeException();
			}
			if(conn != null) ConnectionPool.getInstance().putback(conn);
		}
		
		return !users.isEmpty() ? users.getFirst() : new User(); 
	}

	public List<User> getAllUsers() {
		return getUsersByMask("");
	}
	
	public List<User> getUsersByMask(String filter) {
		LinkedList<User> users = new LinkedList<User>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_SQL+filter+SELECT_ORDER);
			while(rs.next()){
				users.add(loadUser(rs));
			}
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new SqlRuntimeException();
			}
			if(conn != null) ConnectionPool.getInstance().putback(conn);
		}
		
		return users;
	}
	
	private User loadUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getLong("ID"));
		user.setSurname(rs.getString("SURNAME"));
		user.setName(rs.getString("NAME"));
		user.setAge(rs.getInt("AGE"));
		user.setGender(rs.getString("GENDER"));
		user.setPhone(rs.getString("PHONE"));

		return user;
	}

}
