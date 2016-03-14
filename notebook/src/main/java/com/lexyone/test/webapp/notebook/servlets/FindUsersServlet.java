package com.lexyone.test.webapp.notebook.servlets;

import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.forward;
import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.showError;
import static com.lexyone.test.webapp.notebook.servlets.RequestDispatcher.showUsers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lexyone.test.webapp.notebook.datasource.entities.Gender;
import com.lexyone.test.webapp.notebook.datasource.entities.Phone;
import com.lexyone.test.webapp.notebook.services.UserServiceFactory;

public class FindUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1433273474124925973L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(isFindMode(request)) {
    		try {
    			if(	checkFindById(request, response) ||
    				checkFindBySurname(request, response) ||
    				checkFindByName(request, response) ||
    				checkFindByPhone(request, response) ||
    				checkFindByAge(request, response) ||
    				checkFindByGender(request, response) ) {
    				return;
    			} else {
    				throw new IllegalArgumentException();
    			}
			} catch (IllegalArgumentException e) {
	       		request.setAttribute("error", "Параметры запроса указаны неверно. Повторите попытку.");
	           	forward("/find_users.jsp", request, response);
			} catch (Exception e) {
				showError("Ошибка соединеня с базой данных.", request, response);
			}
    	} else {
           	forward("/find_users.jsp", request, response);
    	}
    }
    
    private boolean isFindMode(HttpServletRequest request) {
    	return (request.getParameter("findBy") != null);
    }
    
    private boolean checkFindById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_ID")) return false;
    	Long id = loadAsLong("id", request);
   		if(id == null) throw new IllegalArgumentException();
   		request.setAttribute("mode", "find");
		request.setAttribute("message", "Результаты поиска для 'Номер' = '"+id+"'.");
		showUsers(UserServiceFactory.getNewUserService().findById(id), request, response);
		return true;
    }
    
    private boolean checkFindBySurname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_SURNAME")) return false;
    	String surname = request.getParameter("surname");
   		if(surname.trim().isEmpty()) throw new IllegalArgumentException();
   		request.setAttribute("mode", "find");
   		request.setAttribute("message", "Результаты поиска для 'Фамилия' = '"+surname+"'.");
		showUsers(UserServiceFactory.getNewUserService().findBySurname(surname), request, response);
		return true;
    }
    
    private boolean checkFindByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_NAME")) return false;
    	String name = request.getParameter("name");
   		if(name.trim().isEmpty()) throw new IllegalArgumentException();
   		request.setAttribute("mode", "find");
   		request.setAttribute("message", "Результаты поиска для 'Имя' = '"+name+"'.");
		showUsers(UserServiceFactory.getNewUserService().findByName(name), request, response);
		return true;
    }
    
    private boolean checkFindByPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_PHONE")) return false;
    	Phone phone = Phone.valueOf(request.getParameter("phone"));
    	if(!phone.isCorrect()) throw new IllegalArgumentException();
   		request.setAttribute("mode", "find");
    	request.setAttribute("message", "Результаты поиска для 'Тедефон' = '"+phone+"'.");
		showUsers(UserServiceFactory.getNewUserService().findByPhone(phone), request, response);
		return true;
    }
    
    private boolean checkFindByAge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_AGE")) return false;
    	Integer age = loadAsInteger("age", request);
   		if(age == null) throw new IllegalArgumentException();
   		request.setAttribute("mode", "find");
		request.setAttribute("message", "Результаты поиска для 'Возраст' = '"+age+"'.");
		showUsers(UserServiceFactory.getNewUserService().findByAge(age), request, response);
		return true;
    }
    
    private boolean checkFindByGender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String findMode = request.getParameter("findBy");
    	if(!findMode.equals("BY_GENDER")) return false;
    	Gender gender = Gender.identify(request.getParameter("gender"));
    	String genderStr = (gender == Gender.MAN) ? "Мужской" : "Женский";
   		request.setAttribute("mode", "find");
		request.setAttribute("message", "Результаты поиска для 'Пол' = '"+genderStr+"'.");
		showUsers(UserServiceFactory.getNewUserService().findByGender(gender), request, response);
		return true;
    }
    
    private Long loadAsLong(String param, HttpServletRequest request) {
    	try {
			return Long.valueOf(request.getParameter(param));
		} catch (Exception e) {
			return null;
		}
    }
    
    private Integer loadAsInteger(String param, HttpServletRequest request) {
    	try {
			return Integer.valueOf(request.getParameter(param));
		} catch (Exception e) {
			return null;
		}
    }
    
}
