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
	private static final String SELECT_ALL_SQL = "select ID, SURNAME, NAME, AGE, GENDER, PHONE from USERS";
	private static final String SELECT_ORDER = "order by ID";
	private static final String SELECT_BY_ID_SQL = "select ID, SURNAME, NAME, AGE, GENDER, PHONE from USERS WHERE ID = ?";
	private static final String UPDATE_SQL = "update USERS set SURNAME = ?, NAME = ?, AGE = ?, GENDER = ?, PHONE = ? where (ID = ?)";
	private static final String INSERT_SQL = "insert into USERS (SURNAME, NAME, AGE, GENDER, PHONE) values (?, ?, ?, ?, ?)";
	
	public Long getMaxUserId() {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			return getMaxUserIdImpl(conn);
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			ConnectionPool.getInstance().putback(conn);
		}
	}
	
	public void saveUser(User user) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			if(!DaoImpl.isPersisted(user)) {
				addUserImpl(conn, user);
			} else {
				updateUserImpl(conn, user);
			}
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			ConnectionPool.getInstance().putback(conn);
		}
	}
	
	public User getUser(Long id) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			return getUserImpl(conn, id);
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			ConnectionPool.getInstance().putback(conn);
		}
	}
	
	public List<User> getAllUsers() {
		return getUsersByMask("");
	}
	
	public List<User> getUsersByMask(String filter) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().retrieve();
			return getUsersByMaskImpl(conn, filter);
		} catch (SQLException e) {
			throw new SqlRuntimeException();
		} finally {
			ConnectionPool.getInstance().putback(conn);
		}
	}
	
	
	public Long getMaxUserIdImpl(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_MAX_ID_SQL);
			if(!rs.next()) throw new SqlRuntimeException();
			return rs.getLong("MAX");
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		}
	}

	private void addUserImpl(Connection conn, User user) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(INSERT_SQL);
			setUser(ps, user);
			if(0 == ps.executeUpdate()) throw new SqlRuntimeException();
			user.setId(getMaxUserId());
		} finally {
			if(ps != null) ps.close();
		}
	}

	private void updateUserImpl(Connection conn, User user) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(UPDATE_SQL);
			setUser(ps, user);
			ps.setLong(6, user.getId());
			if(0 == ps.executeUpdate()) throw new SqlRuntimeException();
		} finally {
			if(ps != null) ps.close();
		}
	}
	
	private void setUser(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getSurname());
		ps.setString(2, user.getName());
		ps.setInt(3, user.getAge());
		ps.setString(4, user.getGender().getId());
		ps.setString(5, user.getPhone().toString());
	}
	
	public User getUserImpl(Connection conn, Long id) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_BY_ID_SQL);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(!rs.next()) throw new SqlRuntimeException();
			return loadUser(rs);
		} finally {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
		}
	}

	public List<User> getUsersByMaskImpl(Connection conn, String filter) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_SQL+" "+filter+" "+SELECT_ORDER);
			LinkedList<User> users = new LinkedList<User>();
			while(rs.next()){
				users.add(loadUser(rs));
			}
			return users;
		} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		}
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
