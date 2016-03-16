<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding='UTF-8'
    		errorPage="/error.jsp"
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
	
    <c:if test="${message != null}">
		<div align="center">
			<h3><font color="blue"> ${message} </font></h3><br/>
		</div>    
    </c:if>


	<c:choose>
	   	<c:when test="${users.isEmpty()}">
			<div align="center">
				<c:choose>
				    <c:when test="${mode == 'find'}"> <h2> По вашему запросу ничего не найдено. </h2> </c:when>
				    <c:otherwise> <h2> Таблица пользователей пуста. </h2> </c:otherwise>
				</c:choose>
			</div>    
		</c:when>
		<c:otherwise>
			<c:choose>
			   	<c:when test="${mode == 'find'}">
					<c:set var="tableCaption">Результаты поиска для ${findMode}</c:set>
				</c:when>
				<c:otherwise>
					<c:set var="tableCaption">Таблица пользователей</c:set>
				</c:otherwise>
			</c:choose>
			<div align="center">
				<h2> <c:out value="${tableCaption}"/> </h2>
			</div>    
			<div id="idUsersTable" align="center">

				<table>
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
										<input 	name="id" value="${user.id}" readonly hidden />
										<button name="update" title="Редактировать пользователя" value="true"> Редактировать </button>
									</form>
						   		</td>
						  	</tr>
				
				
					  	</c:forEach>
				  
				  	</tbody>
				  
				</table>

			</div>
		</c:otherwise>
	</c:choose>

	<div align="center">
	    <c:if test="${mode == 'edit' && !users.isEmpty()}">
			<h3><font color="blue">Выберите пользователя для редактирования.</font></h3>
			<h5>Редактирование доступно по нажатию кнопки 'Редактировать'.</h5> 
		</c:if>
	</div>
	
	
	
	
	
	
	

</body>

</html>