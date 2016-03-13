package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class RequestDispatcher {

    public static void showUsers(List<User> users, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", users);
		forward("/watch_users.jsp", request, response);
    }
    
    public static void showError(String error, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage", error);
    	forward("/error.jsp", request, response);
    }
    
    public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
    }
    
    public static void redirect(String path, HttpServletResponse response) throws IOException {
		response.sendRedirect(path);
    }
    
}
