package com.lexyone.test.webapp.notebook.datasource.dao;

public class DaoFactory {
    private static DaoFactory instance = null;
    private static UsersDao usersDao = null;

    public static synchronized DaoFactory getInstance(){
        if(instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public UsersDao getUsersDao() {
        if (usersDao == null){
        	usersDao = new UsersDaoTestImpl();
        }
        return usersDao;
    }
}