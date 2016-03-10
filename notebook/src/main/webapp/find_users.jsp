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

	<form action="/NoteBook/watch_users" method="GET" autocomplete="off">
		<input 	name="mode"
				value="${mode}"
				hidden 
				/>

		<table  align="center">

			<caption><h2>Поиск пользователя</h2></caption>
			
			<col span="1">
			<col width="150" span="1">
			<col width="300" span="1">

		  	<tbody bgcolor="#e8e8e8">
				<tr>
					<td><input type="radio" name="findBy" value="ID" required></td>
					<td>Уникальный номер:</td>
					<td>
						<input 	name="id"
								value="${user.id}" 
								style="width: 100%;" 
								autofocus
						/>
					</td>
				</tr>
				<tr>
					<td><input type="radio" name="findBy" value="SURNAME" required></td>
					<td>Фамилия:</td>
					<td>
						<input 	name="surname" 
								value="${user.surname}" 
								style="width: 100%;" 
								placeholder="фамилия"
								maxlength="50" 
						/>
					</td>
				</tr>
				<tr>
					<td><input type="radio" name="findBy" value="NAME" required></td>
					<td>Имя:</td>
					<td>
						<input 	name="name" 
								value="${user.name}" 
								style="width: 100%;" 
								placeholder="имя"
								maxlength="50" 
						/>
					</td>
				</tr>
				<tr>
					<td><input type="radio" name="findBy" value="PHONE" required></td>
					<td>Телефон:</td>
					<td>
						<input 	name="phone" 
								value="${user.phone}" 
								type="tel" 
								style="width: 100%;" 
								placeholder="+(xxx)xxx-xx-xx"
								pattern="\+\([0-9]{3}\)[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
						/>
					</td>
				</tr>
				<tr>
					<td><input type="radio" name="findBy" value="AGE" required></td>
					<td>Возраст:</td>
					<td>
						<select	name="age"
								style="width: 100%;" 
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
					<td><input type="radio" name="findBy" value="GENDER" required></td>
					<td>Пол:</td>
					<td>
						<select	name="gender"
								style="width: 100%;" 
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
			<button name="find" value="true" style="width:120px" title="Найти пользователя"> Найти </button>
		</p>
	</form>


</body>

</html>