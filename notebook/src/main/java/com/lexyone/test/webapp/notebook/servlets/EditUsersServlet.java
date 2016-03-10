package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.dao.DaoFactory;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class EditUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 4200799529231065138L;

    private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
        	if(request.getParameter("update") != null && request.getParameter("id") != null) {
        		Long id = Long.valueOf(request.getParameter("id"));
        		User user = DaoFactory.getInstance().getUsersDao().getUser(id);
        		if(user.getId() == id) {
            		request.setAttribute("user", user);
            		request.setAttribute("mode", "update");
                	request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
        		} else {
            		request.setAttribute("errorMessage", "Пользователь с номером "+id+" не найден.");
                	request.getRequestDispatcher("/error.jsp").forward(request, response);
    			}
        	} else {
           		response.setContentType("text/html");
             	response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
              	response.setHeader("Location", "/NoteBook/watch_users");
        	}
		} catch (Exception e) {
    		request.setAttribute("errorMessage", "Нет соединеня с базой данных.");
        	request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doWork(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doWork(request, response);
    }
}
