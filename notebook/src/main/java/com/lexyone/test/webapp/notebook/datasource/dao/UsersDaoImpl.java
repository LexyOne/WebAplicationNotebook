package com.lexyone.test.webapp.notebook.datasource.dao;

import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class UsersDaoImpl implements UsersDao {

	@Override
	public Long getMaxUserId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public User getUser(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getAllUsers() {
		throw new UnsupportedOperationException();
	}

    @Override
	public List<User> getUsersByMask(User.Filter filter) {
		throw new UnsupportedOperationException();
	}
	
}
