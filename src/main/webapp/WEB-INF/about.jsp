<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="h" %>
<!-- For form submission and validations -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- for bootstrap -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/about.css"/>
	<link rel="stylesheet" href="/css/index.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    
	<title>Insert title here</title>
</head>
<body>
        <h:navbar name="test" />
        <h1 class="d-flex justify-content-start text-light">About</h1>
		<h2>Meet the creator</h2>
		<img src="images/profile-image-min.jpg" alt="kevin's profile picture" class="profile-picture">
		<h3>Lack of passion in biomedical research drove Kevin Huynh to step out of his comfort zone to make the switch to an exciting career in development! He enjoys tackling problems pertaining to data management and user experience.</h3>
		<h2>contact 253-332-8063</h2>
		<h2>email: kvhuynh820@gmail.com</h2>
		
		
</body>
  
</html>

