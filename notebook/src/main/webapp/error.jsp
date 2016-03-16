<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding='UTF-8' isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Your NoteBook</title>
</head>

<body>
	<%@ include file="/header.jsp"%>

	<div align="center">
		<br/><br/>
		<font face="Times New Roman" size="6" color="red">Ошибка!!!</font><br/>
		<font face="Times New Roman" size="5" color="red">${errorMessage}</font><br/>
	</div>
	
</body>

</html>