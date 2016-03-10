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
	
	<h1><p align="center">"Ваша записная книжка!!!"</p></h1>

	<p align="center">
		<button 		name="home" 
						style="width:100px" 
						title="Главная страница" 
						onclick="location.href='home';"
						> Главная  </button>
		<button 		name="view" 
						style="width:100px" 
						title="Просмотр пользователей" 
						onclick="location.href='watch_users';"
						> Просмотр </button>
		<button 		name="add" 
						style="width:100px" 
						title="Добавление пользователей" 
						onclick="location.href='add_users';"
						> Добавить </button>
		<button 		name="edit" 
						style="width:100px" 
						title="Редактирование пользователя" 
						onclick="location.href='upd_users';"
						> Изменить </button>
		<button 		name="find" 
						style="width:100px" 
						title="Поиск пользователей" 
						onclick="location.href='find_users';"
						>  Найти   </button>
 	</p>

<%-- 
	<p align="center">
		<button title="go to exampe page" onclick="location.href='example';"> go to exampe page </button>
	</p>
--%>
	
</body>

</html>