<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="h" %>
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
	<link rel="stylesheet" href="/css/show-one.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<c:url value='/js/favorite.js'/>" defer></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">   

	<title>Insert title here</title>
</head>
	<body>
		<h:navbar name="test" />
		<div class="d-flex justify-content-center">
			<div>
				<iframe
				src="https://player.twitch.tv/?channel=${channelData.get('login')}&parent=localhost&autoplay=true"
				height="720"
				width="1280"
				allowfullscreen>
				</iframe>
				<div class="d-flex align-items-center">
					<img class="profile-pic" src="${channelData.get('profile_image_url')}" alt="">
					<div></div>
					<h1 class="text-light"><c:out value="${channelData.get('display_name')}"/></h1>
					<button id="submit" value="${channelData.get('login')}/favorite" class="btn btn-danger">
						<i class="bi bi-star"></i>
					</button>
				</div>
			</div>
			<iframe src="https://www.twitch.tv/embed/${channelData.get('login')}/chat?darkpopout&parent=localhost"
			height="1000"
			width="350">
			</iframe>
		</div>
	</body>
</html>
