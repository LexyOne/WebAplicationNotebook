<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding='UTF-8'
    		errorPage="error.jsp"
    		%>

<%@page import="com.lexyone.test.webapp.notebook.datasource.entities.User"%>
    		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<title>Your NoteBook</title>
</head>

<body>
	<%@ include file="/header.jsp" %>

    <c:if test="${mode == 'add'}">
		<c:set var="hiddenIfAddMode">hidden</c:set>
    </c:if>
    
    <c:if test="${error == null}">
		<c:set var="hiddenIfNotError">hidden</c:set>
    </c:if>
    
    <h3> <p ${hiddenIfNotError} align="center">
    <font color="red"> <br/> Ошибка: ${error} </font> 
    </p> </h3>
	
	<form action="/NoteBook/save_users" method="POST" autocomplete="off">
		<input 	name="mode"
				value="${mode}"
				hidden 
				/>

		<table  align="center">

			<c:choose>
			    <c:when test="${mode == 'update'}">
					<caption><h2>Редактирование пользователя</h2></caption>
			    </c:when>
			    <c:otherwise>
					<caption><h2>Добавление нового пользователя</h2></caption>
			    </c:otherwise>
			</c:choose>
			
			<col width="150" span="1">
			<col width="300" span="1">

		  	<tbody bgcolor="#e8e8e8">
				<tr ${hiddenIfAddMode}>
					<td>Уникальный номер:</td>
					<td>
						<input 	name="id"
								value="${user.id}" 
								style="width: 100%;" 
								readonly 
						/>
					</td>
				</tr>
				<tr>
					<td>Фамилия:</td>
					<td>
						<input 	name="surname" 
								value="${user.surname}" 
								style="width: 100%;" 
								placeholder="фамилия"
								maxlength="100" 
								required
								autofocus
						/>
					</td>
				</tr>
				<tr>
					<td>Имя:</td>
					<td>
						<input 	name="name" 
								value="${user.name}" 
								style="width: 100%;" 
								placeholder="имя"
								maxlength="100" 
								required 
						/>
					</td>
				</tr>
				<tr>
					<td>Телефон:</td>
					<td>
						<input 	name="phone" 
								value="${user.phone}" 
								type="tel" 
								style="width: 100%;" 
								placeholder="+(xxx)xxx-xx-xx"
								pattern="\+\([0-9]{3}\)[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
								required 
						/>
					</td>
				</tr>
				<tr>
					<td>Возраст:</td>
					<td>
						<select	name="age"
								style="width: 100%;" 
								required 
								>
							<c:choose>
							    <c:when test="${null == user.age}">
									<option selected disabled></option>
							    </c:when>
							    <c:otherwise>
									<option selected disabled></option>
							    </c:otherwise>
							</c:choose>
							<c:forEach var="ageValue" begin="<%= User.MIN_AGE %>" end="<%= User.MAX_AGE %>">
								<c:choose>
								    <c:when test="${ageValue == user.age}">
										<option selected>${ageValue}</option>
								    </c:when>
								    <c:otherwise>
										<option>${ageValue}</option>
								    </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Пол:</td>
					<td>
						<select	name="gender"
								style="width: 100%;" 
								required 
								>
							<c:choose>
							    <c:when test="${user.gender == 'MAN'}">
									<option disabled></option>
									<option value="M" selected> Мужской </option>
									<option value="W"> Женский </option>
							    </c:when>
							    <c:when test="${user.gender == 'WOMEN'}">
									<option disabled></option>
									<option value="M"> Мужской </option>
									<option value="W" selected> Женский </option>
							    </c:when>
							    <c:otherwise>
									<option disabled selected></option>
									<option value="M"> Мужской </option>
									<option value="W"> Женский </option>
							    </c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
		  	</tbody>

		</table>

		<p align="center">
			<button name="save" value="true" style="width:120px" title="Сохранить пользователя"> Сохранить </button>
		</p>
	</form>
	
</body>

</html>