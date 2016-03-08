package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.dao.DaoFactory;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class WatchUsersServlet extends HttpServlet {

	private static final long serialVersionUID = -7140743907878638415L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = DaoFactory.getInstance().getUsersDao().getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/watch_users.jsp").forward(request, response);
	}
}
