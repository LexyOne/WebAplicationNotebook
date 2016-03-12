package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.User;
import com.lexyone.test.webapp.notebook.services.UserServiceFactory;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*; 

public class SaveUsersServlet extends HttpServlet {

	private static final long serialVersionUID = -2836962898374556667L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = loadUser(request);
   	
    	if(!user.isCorrect()) {
    		request.setAttribute("mode", request.getParameter("mode"));
           	request.setAttribute("user", user);
           	request.setAttribute("error", "Данные пользователя заполнены не верно.");
			forward("/edit_user.jsp", request, response);
    	} else {
    		try {
    			UserServiceFactory.getNewUserService().save(user);    	
        		redirect("/NoteBook/watch_users", response);
			} catch (Exception e) {
				showError("Ошибка соединеня с базой данных.", request, response);
			}
    	}
    }
    
    private User loadUser(HttpServletRequest request) {
    	User user = new User();
    	
    	if(hasUserId(request)) {
    		user.setId(Long.valueOf(request.getParameter("id")));
    	}
   	
    	user.setSurname(request.getParameter("surname"));
    	user.setName(request.getParameter("name"));
    	user.setPhone(request.getParameter("phone"));
    	user.setAge(Integer.valueOf(request.getParameter("age")));
    	user.setGender(request.getParameter("gender"));
    	
    	return user;
    }
    
    private boolean hasUserId(HttpServletRequest request) {
    	return (request.getParameter("id") != null && !request.getParameter("id").toString().isEmpty()); 
    }
    
}
