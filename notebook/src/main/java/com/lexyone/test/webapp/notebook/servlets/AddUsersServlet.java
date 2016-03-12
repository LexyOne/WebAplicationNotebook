package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*;

public class AddUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 2922368924755219019L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mode", "add");
    	forward("/edit_user.jsp", request, response);
    }
}
