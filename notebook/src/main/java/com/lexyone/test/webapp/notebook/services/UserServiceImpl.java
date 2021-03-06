package com.lexyone.test.webapp.notebook.services;

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
		return usersDao.getUsersById(id);
	}

	public List<User> findBySurname(String surname) {
		return usersDao.getUsersBySurname(surname);
	}

	public List<User> findByName(String name) {
		return  usersDao.getUsersByName(name);
	}

	public List<User> findByPhone(Phone phone) {
		return usersDao.getUsersByPhone(phone);
	}

	public List<User> findByAge(Integer age) {
		return usersDao.getUsersByAge(age);
	}

	public List<User> findByGender(Gender gender) {
		return usersDao.getUsersByGender(gender);
	}

}
