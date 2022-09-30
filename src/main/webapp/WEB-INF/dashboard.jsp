<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!-- For form submission and validations -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="/js/app.js"></script>

	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

	<link rel="stylesheet" href="/css/main.css"/>

	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://cdn.tailwindcss.com"></script>
	<title>Insert title here</title>
</head>
	<body>
		<h1>yo, <c:out value="${twitchName}"/> </h1>
		<p>your twitch id is <c:out value="${twitchId}" /></p>
	</body>
</html>