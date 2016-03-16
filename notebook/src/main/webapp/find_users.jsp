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

    <c:if test="${error == null}">
		<c:set var="hiddenIfNotError">hidden</c:set>
    </c:if>
    
    <div align="center" ${hiddenIfNotError}>
		<br/><br/>
		<font face="Times New Roman" size="5" color="red">${error}</font><br/>
		<br/><br/>
    </div>

    <div align="center">
		<form name="find_form" action="" method="GET" onsubmit="return validate_find();" autocomplete="off">
	
			<table>
	
				<caption><h2>Поиск пользователя</h2></caption>
				
				<col span="1">
				<col width="150" span="1">
				<col width="300" span="1">
	
			  	<tbody bgcolor="#e8e8e8">
			  	
					<tr>
						<td><input type="radio" name="findBy" value="BY_ID" required onchange="findByChanged(this.value)"></td>
						<td>Уникальный номер:</td>
						<td>
							<input 	name="id"
									placeholder="Уникальный номер"
									maxlength="10" 
									style="width: 100%;" 
							/>
						</td>
					</tr>
					
					<tr>
						<td><input type="radio" name="findBy" value="BY_SURNAME" required onchange="findByChanged(this.value)"></td>
						<td>Фамилия:</td>
						<td>
							<input 	name="surname" 
									placeholder="Фамилия"
									maxlength="<%= User.MAX_SURNAME_LENGTH %>" 
									style="width: 100%;" 
							/>
						</td>
					</tr>
					
					<tr>
						<td><input type="radio" name="findBy" value="BY_NAME" required onchange="findByChanged(this.value)"></td>
						<td>Имя:</td>
						<td>
							<input 	name="name" 
									placeholder="Имя"
									maxlength="<%= User.MAX_NAME_LENGTH %>" 
									style="width: 100%;" 
							/>
						</td>
					</tr>
					
					<tr>
						<td><input type="radio" name="findBy" value="BY_PHONE" required onchange="findByChanged(this.value)"></td>
						<td>Телефон:</td>
						<td>
							<input 	name="phone" 
									placeholder="<%= Phone.getHintPattern() %>"
									<%--
										pattern="<%= Phone.getRegexPattern() %>"
									--%>
									style="width: 100%;" 
							/>
						</td>
					</tr>
					
					<tr>
						<td><input type="radio" name="findBy" value="BY_AGE" required onchange="findByChanged(this.value)"></td>
						<td>Возраст:</td>
						<td>
							<select	name="age"
									style="width: 100%;" 
									>
								<option selected disabled value=""></option>
								<c:forEach var="ageValue" begin="<%= User.MIN_AGE %>" end="<%= User.MAX_AGE %>">
									<option value="${ageValue}">${ageValue}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<td><input type="radio" name="findBy" value="BY_GENDER" required onchange="findByChanged(this.value)"></td>
						<td>Пол:</td>
						<td>
							<select	name="gender"
									style="width: 100%;" 
									>
								<option disabled selected></option>
								<option value="M"> Мужской </option>
								<option value="W"> Женский </option>
							</select>
						</td>
					</tr>
	
			  	</tbody>
	
			</table>
	
			<p align="center">
				<button name="find" value="true" style="width:120px" title="Найти пользователя"> Найти </button>
			</p>
		</form>	
    </div>
    

</body>

<script>

findByChanged();

var MIN_SURNAME_LENGTH = <%= User.MIN_SURNAME_LENGTH %>;
var MIN_NAME_LENGTH = <%= User.MIN_NAME_LENGTH %>;

function trimTextInputs() {
	document.find_form.id.value = document.find_form.id.value.trim();
	document.find_form.surname.value = document.find_form.surname.value.trim();
	document.find_form.name.value = document.find_form.name.value.trim();
	document.find_form.phone.value = document.find_form.phone.value.trim();
}

function findByChanged(value) {
	trimTextInputs();

	switch(value) {
	case "BY_ID":
		document.find_form.id.readOnly = false;
		document.find_form.id.required = true;
		document.find_form.id.style.visibility = "visible";
		break;
	case "BY_SURNAME":
		document.find_form.surname.readOnly = false;
		document.find_form.surname.required = true;
		document.find_form.surname.style.visibility = "visible";
		break;
	case "BY_NAME":
		document.find_form.name.readOnly = false;
		document.find_form.name.required = true;
		document.find_form.name.style.visibility = "visible";
		break;
	case "BY_PHONE":
		document.find_form.phone.readOnly = false;
		document.find_form.phone.required = true;
		document.find_form.phone.style.visibility = "visible";
		break;
	case "BY_AGE":
		document.find_form.age.readOnly = false;
		document.find_form.age.required = true;
		document.find_form.age.style.visibility = "visible";
		break;
	case "BY_GENDER":
		document.find_form.gender.readOnly = false;
		document.find_form.gender.required = true;
		document.find_form.gender.style.visibility = "visible";
		break;
	}
	
	if(value != "BY_ID") {
		document.find_form.id.value = "";
		document.find_form.id.readOnly = true;
		document.find_form.id.required = false;
		document.find_form.id.style.visibility = "hidden";
	}
	if(value != "BY_SURNAME") {
	  	document.find_form.surname.value = "";
		document.find_form.surname.readOnly = true;
		document.find_form.surname.required = false;
		document.find_form.surname.style.visibility = "hidden";
	}
	if(value != "BY_NAME") {
	  	document.find_form.name.value = "";
		document.find_form.name.readOnly = true;
		document.find_form.name.required = false;
		document.find_form.name.style.visibility = "hidden";
	}
	if(value != "BY_PHONE") {
	  	document.find_form.phone.value = "";
		document.find_form.phone.readOnly = true;
		document.find_form.phone.required = false;
		document.find_form.phone.style.visibility = "hidden";
	}
	if(value != "BY_AGE") {
	  	document.find_form.age.value = "";
		document.find_form.age.readOnly = true;
		document.find_form.age.required = false;
		document.find_form.age.style.visibility = "hidden";
	}
	if(value != "BY_GENDER") {
	  	document.find_form.gender.value = "";
		document.find_form.gender.readOnly = true;
		document.find_form.gender.required = false;
		document.find_form.gender.style.visibility = "hidden";
	}
}

function validate_find() {
	trimTextInputs();
	
	switch(document.find_form.findBy.value) {
	case "BY_ID":
		if(!isNumeric(document.find_form.id.value)) {
			alert ( "Пожалуйста правильно заполните поле 'Уникальный номер'." );
			return false;
		}		  	
		break;
	case "BY_SURNAME":
		break;
	case "BY_NAME":
		break;
	case "BY_PHONE":
		if (!validate_phone(document.find_form.phone.value) ) {
			alert ( "Пожалуйста правильно заполните поле 'Телефон'.\rТребуется ввод данных в формате '<%= Phone.getHintPattern() %>'." );
			return false;
		}
		break;
	case "BY_AGE":
		break;
	case "BY_GENDER":
		break;
	}
	return true;

	function isNumeric(n) {
		return !isNaN(parseFloat(n)) && isFinite(n);
	}

	function validate_phone(phone) {
		var reg = new RegExp("<%= Phone.getRegexPattern().replaceAll("\\\\", "\\\\\\\\") %>");
		return reg.test(phone);
	}

	function validate_phone(phone) {
		var reg = new RegExp("<%= Phone.getRegexPattern().replaceAll("\\\\", "\\\\\\\\") %>");
		return reg.test(phone);
	}
}

</script>

</html>