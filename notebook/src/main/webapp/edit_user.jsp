<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding='UTF-8'
    		errorPage="/error.jsp"
    		%>

<%@page import="com.lexyone.test.webapp.notebook.datasource.entities.User"%>
<%@page import="com.lexyone.test.webapp.notebook.datasource.entities.Phone"%>
    		
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
    

    <div align="center" ${hiddenIfNotError}>
		<br/><br/>
		<font face="Times New Roman" size="5" color="red">Ошибка: ${error}</font><br/>
		<br/><br/>
    </div>
    
   <div align="center">
		<form name="user_form" action="/NoteBook/save_users" method="POST" onsubmit="return validate_user();" autocomplete="off">
		
			<input 	name="mode"
					value="${mode}"
					hidden 
					/>
			
			<table>
			
				<c:choose>
				    <c:when test="${mode == 'add'}">
						<caption><h2>Добавление нового пользователя</h2></caption>
				    </c:when>
				    <c:otherwise>
						<caption><h2>Редактирование пользователя</h2></caption>
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
									placeholder="Фамилия"
									maxlength="<%= User.MAX_SURNAME_LENGTH %>" 
									required 
							/>
						</td>
					</tr>
					
					<tr>
						<td>Имя:</td>
						<td>
							<input 	name="name" 
									value="${user.name}" 
									style="width: 100%;" 
									placeholder="Имя"
									maxlength="<%= User.MAX_NAME_LENGTH %>" 
									required 
							/>
						</td>
					</tr>
					
					<tr>
						<td>Телефон:</td>
						<td>
							<input 	name="phone" 
									value="${user.phone}" 
									style="width: 100%;" 
									placeholder="<%= Phone.getHintPattern() %>"
									<%--
										pattern="<%= Phone.getRegexPattern() %>"
									--%>
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
										<option selected disabled value=""></option>
								    </c:when>
								    <c:otherwise>
										<option disabled></option>
								    </c:otherwise>
								</c:choose>
								<c:forEach var="ageValue" begin="<%= User.MIN_AGE %>" end="<%= User.MAX_AGE %>">
									<c:choose>
									    <c:when test="${ageValue == user.age}">
											<option selected value="${ageValue}">${ageValue}</option>
									    </c:when>
									    <c:otherwise>
											<option value="${ageValue}">${ageValue}</option>
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
				<button type="reset" style="width:120px" title="Сбросить введенные данные"> Сбросить </button>
				</p>
			
		</form>
   </div>
	
</body>

<script>
var MIN_SURNAME_LENGTH = <%= User.MIN_SURNAME_LENGTH %>;
var MIN_NAME_LENGTH = <%= User.MIN_NAME_LENGTH %>;

function validate_user() {
	document.user_form.surname.value = document.user_form.surname.value.trim();
	document.user_form.name.value = document.user_form.name.value.trim();
	document.user_form.phone.value = document.user_form.phone.value.trim();
	
	if (document.user_form.surname.value.length < MIN_SURNAME_LENGTH ) {
		alert ( "Пожалуйста правильно заполните поле 'Фамилия'.\rТребуется минимум "+MIN_SURNAME_LENGTH+" символов." );
		return false;
	}
	
	if ( document.user_form.name.value.length < MIN_NAME_LENGTH ) {
		alert ( "Пожалуйста правильно заполните поле 'Имя'.\rТребуется минимум "+MIN_NAME_LENGTH+" символов." );
		return false;
	}

	if (!validate_phone(document.user_form.phone.value) ) {
		alert ( "Пожалуйста правильно заполните поле 'Телефон'.\rТребуется ввод данных в формате '<%= Phone.getHintPattern() %>'." );
		return false;
	}
	
    if ( document.user_form.age.selectedIndex == 0 ) {
		alert ( "Пожалуйста правильно заполните поле 'Возраст'." );
		return false;
    }
    
    if ( document.user_form.gender.selectedIndex == 0 ) {
		alert ( "Пожалуйста правильно заполните поле 'Пол'." );
		return false;
    }
    
	return true;

	function validate_phone(phone) {
		var reg = new RegExp("<%= Phone.getRegexPattern().replaceAll("\\\\", "\\\\\\\\") %>");
		return reg.test(phone);
	}
}
</script>

</html>