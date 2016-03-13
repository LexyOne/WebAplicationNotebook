package com.lexyone.test.webapp.notebook.servlets;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.forward;
import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.showError;
import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.showUsers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.User;
import com.lexyone.test.webapp.notebook.services.UserServiceFactory;

public class FindUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1433273474124925973L;
	
	enum FindMode { UNKNOWN, BY_ID, BY_SURNAME, BY_NAME, BY_PHONE, BY_AGE, BY_GENDER };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(isFindMode(request)) {
    		try {
        		User userMask = loadUser(request);
        		
        		switch(getFindMode(request)) {
        		case BY_ID:
        			showUsers(UserServiceFactory.getNewUserService().findById(userMask.getId()), request, response);
        			return;
        		case BY_SURNAME:
        			showUsers(UserServiceFactory.getNewUserService().findBySurname(userMask.getSurname()), request, response);
        			return;
        		case BY_NAME:
        			showUsers(UserServiceFactory.getNewUserService().findByName(userMask.getName()), request, response);
        			return;
        		case BY_PHONE:
        			showUsers(UserServiceFactory.getNewUserService().findByPhone(userMask.getPhone()), request, response);
        			return;
        		case BY_AGE:
        			showUsers(UserServiceFactory.getNewUserService().findByAge(userMask.getAge()), request, response);
        			return;
        		case BY_GENDER:
        			showUsers(UserServiceFactory.getNewUserService().findByGender(userMask.getGender()), request, response);
        			return;
    			default:
    	       		request.setAttribute("error", "SASAS");
    	           	forward("/find_users.jsp", request, response);
    	           	return;
        		}
			} catch (Exception e) {
				showError("Ошибка соединеня с базой данных.", request, response);
			}
    	} else {
           	forward("/find_users.jsp", request, response);
    	}
    }
    
    private boolean isFindMode(HttpServletRequest request) {
    	return (request.getParameter("findBy") != null);
    }
    
    private FindMode getFindMode(HttpServletRequest request) {
    	String findMode = request.getParameter("findBy");
    	if(findMode.equals("BY_ID")) return FindMode.BY_ID;
    	if(findMode.equals("BY_SURNAME")) return FindMode.BY_SURNAME;
    	if(findMode.equals("BY_NAME")) return FindMode.BY_NAME;
    	if(findMode.equals("BY_PHONE")) return FindMode.BY_PHONE;
    	if(findMode.equals("BY_AGE")) return FindMode.BY_AGE;
    	if(findMode.equals("BY_GENDER")) return FindMode.BY_GENDER;
    	return FindMode.UNKNOWN;
    }

    private User loadUser(HttpServletRequest request) {
    	User user = new User();

    	user.setId(loadAsLong("id", request));
    	user.setSurname(request.getParameter("surname"));
    	user.setName(request.getParameter("name"));
    	user.setPhone(request.getParameter("phone"));
    	user.setAge(loadAsInteger("age", request));
    	user.setGender(request.getParameter("gender"));
    	
    	return user;
    }
    
    private Long loadAsLong(String param, HttpServletRequest request) {
    	try {
			return Long.valueOf(request.getParameter(param));
		} catch (Exception e) {
			return null;
		}
    }
    
    private Integer loadAsInteger(String param, HttpServletRequest request) {
    	try {
			return Integer.valueOf(request.getParameter(param));
		} catch (Exception e) {
			return null;
		}
    }
    
}
