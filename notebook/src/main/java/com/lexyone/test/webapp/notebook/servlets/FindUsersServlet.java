package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*;

public class FindUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1433273474124925973L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	forward("/find_users.jsp", request, response);
    }
}
