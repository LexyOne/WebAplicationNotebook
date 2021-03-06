package com.lexyone.test.webapp.notebook.services;

import com.lexyone.test.webapp.notebook.datasource.dao.DaoFactory;

public class UserServiceFactory {
    private UserServiceFactory() {
    }

    public static UserService getNewUserService(){
        return new UserServiceImpl(DaoFactory.getInstance().getUsersDao());
    }
}
