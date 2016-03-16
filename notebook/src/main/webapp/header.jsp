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
	<style type="text/css"> body { margin:0; padding:0; } </style>
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/fonts/fonts-min.css" />
</head>

<body>

	<div align="center">
		<font face="Times New Roman" size="7">Ваша записная книжка</font><br/><br/>
	</div>

	<div align="center">
	
		<button 		name="home" 
						style="width:110px" 
						title="Главная страница" 
						onclick="location.href='home';"
						> Главная  </button>
						
		<button 		name="view" 
						style="width:110px" 
						title="Просмотр пользователей" 
						onclick="location.href='watch_users';"
						> Просмотр </button>
						
		<button 		name="add" 
						style="width:110px" 
						title="Добавление пользователей" 
						onclick="location.href='add_users';"
						> Добавить </button>
						
		<button 		name="edit" 
						style="width:110px" 
						title="Редактирование пользователя" 
						onclick="location.href='upd_users';"
						> Редактировать </button>
						
		<button 		name="find" 
						style="width:110px" 
						title="Поиск пользователей" 
						onclick="location.href='find_users';"
						>  Найти   </button>
						
		<br/><br/>
 	</div>
	
</body>

</html>