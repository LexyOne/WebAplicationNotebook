package com.lexyone.test.webapp.notebook.services;

import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.entities.Gender;
import com.lexyone.test.webapp.notebook.datasource.entities.Phone;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public interface UserService {

	void save(User user);
    User load(Long id);
    
    List<User> getAll();
    
    List<User> findById(Long id);
    List<User> findBySurname(String surname);
    List<User> findByName(String name);
    List<User> findByPhone(Phone phone);
    List<User> findByAge(Integer age);
    List<User> findByGender(Gender gender);
    
}
