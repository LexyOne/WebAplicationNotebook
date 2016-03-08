package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExampleServlet extends HttpServlet {

	private static final long serialVersionUID = -8913034020795693391L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("message", "Devcolibri");
    	request.getRequestDispatcher("/examples/example.jsp").forward(request, response);    	
    }
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "Devcolibri");
    	request.getRequestDispatcher("/examples/example.jsp").forward(request, response);    	
	}
}
