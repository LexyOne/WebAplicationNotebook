package com.lexyone.test.webapp.notebook.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class ConnectionPool {
    private static ConnectionPool instance = null;

    public static synchronized ConnectionPool getInstance(){
        if(instance == null) {
            instance = new ConnectionPool(10);
        }
        return instance;
    }
	
	private LinkedList<Connection> availableConns = new LinkedList<Connection>();
	private LinkedList<Connection> usedConns = new LinkedList<Connection>();

	private String driverClass;
	private String login;
	private String password;
	private String url;
	
	private ConnectionPool(int initConnCnt) {
        try {
            FileInputStream fis;
            Properties property = new Properties();
    		
            fis = new FileInputStream("src/main/resources/db.properties");
            property.load(fis);

            driverClass = property.getProperty("jdbc.driverClass");
            login = property.getProperty("jdbc.login");
            password = property.getProperty("jdbc.password");
            url = "jdbc:firebirdsql:localhost/3050:"+property.getProperty("jdbc.url")+"?encoding=WIN1251";

            fis.close();
            
   			try {
				Class.forName(driverClass);

	   			while(availableConns.size() < initConnCnt) {
	      			availableConns.add(getConnection());
	       		}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
          
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public synchronized Connection retrieve() throws SQLException {
		Connection newConn = null;
		if (availableConns.size() == 0) {
			newConn = getConnection();
		} else {
			newConn = availableConns.getLast();
			availableConns.removeLast();
		}
		usedConns.add(newConn);
		return newConn;
	}

	public synchronized void putback(Connection c) throws NullPointerException {
	if (c != null) {
		if (usedConns.remove(c)) {
			availableConns.add(c);
		} else {
			throw new NullPointerException("Connection not in the usedConns array");
		}
	 } 
    }

	public int getAvailableConnsCnt() {
		return availableConns.size();
	}
}
