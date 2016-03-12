package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.User;
import com.lexyone.test.webapp.notebook.services.UserServiceFactory;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*; 

public class WatchUsersServlet extends HttpServlet {

	private static final long serialVersionUID = -7140743907878638415L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<User> users = UserServiceFactory.getNewUserService().getAll();
			request.setAttribute("users", users);
			forward("/watch_users.jsp", request, response);
		} catch (Exception e) {
			showError("Ошибка соединеня с базой данных.", request, response);
		}
	}
    
}
