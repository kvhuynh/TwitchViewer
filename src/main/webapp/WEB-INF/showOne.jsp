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
	<script type="text/javascript" src="<c:url value='/js/comment.js'/>" defer></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">   

	<title><c:out value="${channelData.get('display_name')} - Stream"/></title>
</head>
	<body>
		<!-- <h:navbar name="test" /> -->
		<div class="twitch d-flex">
			<div class="twitch-video d-flex flex-column">
				<iframe
				src="https://player.twitch.tv/?channel=${channelData.get('login')}&parent=localhost&autoplay=true"
				height="100%"
				width="100%"
				allowfullscreen>
				</iframe>
				<div class="page-body">
					<div class="d-flex align-items-center">
						<img class="profile-pic" src="${channelData.get('profile_image_url')}" alt="">
						<div></div>
						<h1 class="text-light"><c:out value="${channelData.get('display_name')}"/></h1>
						<button id="submit" value="${channelData.get('login')}/favorite" class="btn btn-danger">
							<i class="bi bi-star"></i>
						</button>
					</div>
					<div class="form-group d-flex flex-column justify-content-center">
						<h1>Comments</h1>
						<div id="comment-section">
							<c:forEach var="comment" items="${comments.getComments()}">
								<h1 class="text-light"><c:out value="${comment.getCommentBody()}"/></h1>
							</c:forEach>
						</div>
						<input type="text" name="" id="comment">
						<button onClick="onClick(this)" value="${channelData.get('login')}">post</button>
						<!-- TODO MAKE IT SO THE COMMENTER, DATE, AND TIME SHOW UP ON THE COMMENT AND MAKE IT LOOK NICE -->
					</div>
				</div>
			</div>
			<div class="chat">
				<iframe src="https://www.twitch.tv/embed/${channelData.get('login')}/chat?darkpopout&parent=localhost"
				height="100%"
				width="100%">
				</iframe>
			</div>
		</div>

	</body>
</html>
