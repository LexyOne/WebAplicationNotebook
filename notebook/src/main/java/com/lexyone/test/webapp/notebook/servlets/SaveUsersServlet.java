package com.lexyone.test.webapp.notebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.dao.DaoFactory;
import com.lexyone.test.webapp.notebook.datasource.entities.User;

public class SaveUsersServlet extends HttpServlet {

	private static final long serialVersionUID = -2836962898374556667L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setAttribute("mode", request.getParameter("mode"));
    	
    	User user = new User();
    	
    	if(request.getParameter("id") != null && !request.getParameter("id").toString().isEmpty()) {
    		user.setId(Long.valueOf(request.getParameter("id")));
    	}
    	
    	user.setSurname(request.getParameter("surname"));
    	user.setName(request.getParameter("name"));
    	user.setPhone(request.getParameter("phone"));
    	user.setAge(Integer.valueOf(request.getParameter("age")));
    	user.setGender(request.getParameter("gender"));
    	
    	if(!user.isCorrect()) {
           	request.setAttribute("user", user);

           	request.setAttribute("error", "������ ����� ��������� �� ���������!");
        	
           	request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
        	return;
    	}

    	DaoFactory.getInstance().getUsersDao().saveUser(user);    	

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "/NoteBook/watch_users");
    }
}
