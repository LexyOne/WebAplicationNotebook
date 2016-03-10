package com.lexyone.test.webapp.notebook.datasource.dao;

import java.util.List;

import com.lexyone.test.webapp.notebook.datasource.entities.User;

public interface UsersDao {
	
	Long getMaxUserId();
	void saveUser(User user);
    User getUser(Long id);
    List<User> getAllUsers();
    List<User> getUsersByMask(User.Filter filter);
    
    class DaoImpl {
        public static boolean isPersisted(User user) {
    		return user.getId() != null;
        }
    	public static boolean isEqualKey(User user, Long id) {
    		return user.getId() == id;
    	}
    	public static boolean isEqualKey(User lhs, User rhs) {
    		return isEqualKey(lhs, rhs.getId());
    	}
    }
}
