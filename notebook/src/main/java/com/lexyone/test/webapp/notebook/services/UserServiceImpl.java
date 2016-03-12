package com.lexyone.test.webapp.notebook.services;

import java.util.LinkedList;
import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.dao.UsersDao;
import com.lexyone.test.webapp.notebook.datasource.entities.Gender;
import com.lexyone.test.webapp.notebook.datasource.entities.Phone;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class UserServiceImpl implements UserService {
	
	private UsersDao usersDao;
	
	public UserServiceImpl(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void save(User user) {
		usersDao.saveUser(user);
	}

	public User load(Long id) {
		return usersDao.getUser(id);
	}

	public List<User> getAll() {
		return usersDao.getAllUsers();
	}

	public List<User> findById(Long id) {
		LinkedList<User> users = new LinkedList<User>();
		users.add(usersDao.getUser(id));
		return users;
	}

	public List<User> findBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByPhone(Phone phone) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByAge(Integer age) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByGender(Gender gender) {
		// TODO Auto-generated method stub
		return null;
	}

}
