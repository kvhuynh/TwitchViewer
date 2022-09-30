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
	<!-- for bootstrap -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/main.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
	<body>
        <div class="container mt-5">
            <div class="row">
                <h1 class="d-flex justify-content-center" style="color:#5895EA;">Twitch Viewer</h1>
            </div>
            <div class="row mt-3">
                <div class="col-6">
                    <h2>New Instructor</h2>
                    <form:form action="/register" modelAttribute="newUser" method="POST">
                        <div class="mb-3">
                            <form:label path="twitchUserName">User Name:</form:label>
                            <form:input class="form-control" type="text" path="twitchUserName"></form:input>
                            <form:errors path="twitchUserName"></form:errors>
                        </div>               
                        <div class="mb-3">
                            <form:label path="email">Email:</form:label>
                            <form:input class="form-control" type="text" path="email"></form:input>
                 			<form:errors path="email"></form:errors>
                        </div>     
                        <div class="mb-3">
                            <form:label path="password">Password:</form:label>
                            <form:input class="form-control" type="text" path="password"></form:input>
                 			<form:errors path="password"></form:errors>

                        </div>
                        <div class="mb-3">
                            <form:label path="confirm">Confirm Password:</form:label>
                            <form:input class="form-control" type="text" path="confirm"></form:input>
                            <form:errors path="confirm"></form:errors>
                        </div>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form:form>
                </div>
                <div class="col-6">
                    <h2>Login</h2>
                    <form:form action="/login" modelAttribute="newLogin" method="POST">
                        <div class="mb-3">
                            <form:label path="email">Email:</form:label>
                            <form:input class="form-control" type="text" path="email"></form:input>
                            <form:errors path="email"></form:errors>

                        </div>
                        <div class="mb-3">
                            <form:label path="password">Password:</form:label>
                            <form:input class="form-control" type="text" path="password"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                        <button type="submit" class="btn btn-success">Login</button>
                    </form:form>
                </div>
            </div>
        </div>
	</body>
</html>