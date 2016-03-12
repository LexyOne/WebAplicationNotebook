package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.User;
import com.lexyone.test.webapp.notebook.services.UserServiceFactory;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*;

public class EditUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 4200799529231065138L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doWork(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doWork(request, response);
    }
    
    private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(isUpdateRequest(request) && hasUserId(request)) {
    		Long id = Long.valueOf(request.getParameter("id"));
    		try {
        		User user = UserServiceFactory.getNewUserService().load(id);
           		request.setAttribute("user", user);
           		request.setAttribute("mode", "update");
               	forward("/edit_user.jsp", request, response);
			} catch (Exception e) {
    			showError("Пользователь с номером "+id+" не найден!", request, response);
			}
    	} else {
    		redirect("/NoteBook/watch_users", response);
    	}
    }
    
    private boolean isUpdateRequest(HttpServletRequest request) {
    	return (request.getParameter("update") != null); 
    }

    private boolean hasUserId(HttpServletRequest request) {
    	return (request.getParameter("id") != null); 
    }

}
