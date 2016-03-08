package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoHomeServlet extends HttpServlet {

	private static final long serialVersionUID = 369368584921360908L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      response.setContentType("text/html");
//      response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//      response.setHeader("Location", "/NoteBook/home");    
		response.sendRedirect("/NoteBook/home");
	}
	
}
