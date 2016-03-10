package com.lexyone.test.webapp.notebook.datasource.dao;

import java.util.LinkedList;
import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class UsersDaoTestImpl implements UsersDao {

	private static LinkedList<User> users = makeTestUsers(5);
	
	private static LinkedList<User> makeTestUsers(int count) {
		LinkedList<User> users = new LinkedList<User>();
		count++;
		for (int i = 1; i < count; i++) {
			users.add(User.makeTestUser(i));
		}
		return users;
	}

	public Long getMaxUserId() {
		return !users.isEmpty() ? users.getLast().getId() : 0;
	}

	public void saveUser(User user) {
		if(DaoImpl.isPersisted(user)) {
			for (int i = 0; i < users.size(); i++) {
				if(DaoImpl.isEqualKey(users.get(i), user)) {
					users.set(i, new User(user));
					return;
				}
			}
			throw new IndexOutOfBoundsException();
		} else {
			Long newId = getMaxUserId()+1;
			User newUser = new User(user);
			newUser.setId(newId);
			users.add(newUser);
			user.setId(newId);
		}
	}

	public User getUser(Long id) {
		for (User current : users) {
			if(DaoImpl.isEqualKey(current, id)) {
				return new User(current);
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public List<User> getAllUsers() {
		return new LinkedList<User>(users);
	}

	public List<User> getUsersByMask(String filter) {
		return getAllUsers(); 
	}

}
