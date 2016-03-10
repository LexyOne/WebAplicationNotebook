<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding='UTF-8'
    		errorPage="error.jsp"
    		%>
    		
    		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<title>Your NoteBook</title>
</head>

<body>

	<%@ include file="/header.jsp" %>
	
	<table align="center">
		<caption><h2>Таблица пользователей</h2></caption>
		
		<col width="80" span="1">
		<col width="200" span="2">
		<col width="80" span="1">
		<col width="100" span="1">
		<col width="150" span="1">
		
		<thead bgcolor="silver">
		<tr valign="middle" align="center">
	   		<td>
	   			Номер
	   		</td>
	   		<td>
	   			Фамилия
	   		</td>
	   		<td>
	   			Имя
	   		</td>
	   		<td>
	   			Возраст
	   		</td>
	   		<td>
	   			Пол
	   		</td>
	   		<td>
	   			Телефон
	   		</td>
	   		<td>
	   		</td>
	  	</tr>
	  	</thead>
	  
	  	<tbody bgcolor="#e8e8e8">

		  	<c:forEach items="${users}" var="user">
	
			  	<tr>
					<td>${user.id}</td>
			   		<td>${user.surname}</td>
			   		<td>${user.name}</td>
			   		<td>${user.age}</td>
			   		<td>
						<c:choose>
						    <c:when test="${user.gender == 'MAN'}"> Мужской </c:when>
						    <c:when test="${user.gender == 'WOMEN'}"> Женский </c:when>
						</c:choose>
					</td>
			   		<td>${user.phone}</td>
			   		<td>
						<form action="upd_users" method="POST">
							<input 	name="id" hidden readonly value="${user.id}"/>
							<button name="update" title="Редактировать пользователя" value="true"> Изменить </button>
						</form>
			   		</td>
			  	</tr>
	
	
		  	</c:forEach>
	  
	  	</tbody>
	  
	</table>

</body>

</html>