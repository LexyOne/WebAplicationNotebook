package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.*; 

public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = -1781949954761584950L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	forward("/home.jsp", request, response);
    }

}
