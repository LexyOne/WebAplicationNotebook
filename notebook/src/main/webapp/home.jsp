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
	<div align="center">
		<img src="${pageContext.request.contextPath}/resources/notebook.jpg" alt="notebook" /><br/><br/>
	</div>
</body>

</html>