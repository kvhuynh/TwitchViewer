<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script>
</script>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">   
	<title><c:out value="${channelData.get('display_name')} - Stream"/></title>
</head>
	<body>
		<h:navbar name="test" />
		<div class="twitch d-flex">
			<div class="twitch-video d-flex flex-column">
				<iframe
				src="https://player.twitch.tv/?channel=${channelData.get('login')}&parent=localhost&autoplay=true"
				allowfullscreen>
				</iframe>
				<div class="page-body">
					<div class="d-flex align-items-center">
						<img class="profile-pic" src="${channelData.get('profile_image_url')}" alt="">
						<div></div>
						<h1 class="text-light"><c:out value="${channelData.get('display_name')}"/></h1>
						<button id="submit" value="${channelData.get('login')}/favorite" class="favorite btn btn-danger" title="Toggle Favorite">
							<i class="bi bi-star"></i>
						</button>
					</div>
					<div class="form-group d-flex flex-column justify-content-center">
						<div id="comment-section">
							<div class="form-group comment-box p-3">
								<c:choose>
									<c:when test="${userName.getId() != null}">
										<input type="text" name="" id="comment" class="form-control">
										<button onClick="onClick(this)" value="${channelData.get('login')}" class="btn btn-dark">post</button>
									</c:when>
									<c:otherwise>
										<a href="/login-register" class="text-decoration-none">
											<p>Log in to comment!</p>
										</a>
									</c:otherwise>
								</c:choose>
								<%-- <form:errors class="text-light" path="commentBody"></form:errors> --%>
							</div>
							<c:forEach var="comment" items="${comments.getComments()}">
								<c:set var="commenter" value="${comment.getUser()}"/>
								<div class="card p-3 bg-dark text-light m-3">
									<div class="d-flex justify-content-between align-items-center">
										<div class="user d-flex flex-row align-items-center">
										<!-- <img src="https://i.imgur.com/hczKIze.jpg" width="30" class="user-img rounded-circle mr-2"> -->
											<span>
												<small class="font-weight-bold text-primary">
													<a href="/profile/${commenter.getTwitchUserName()}" class="text-decoration-none fw-bold">
														<c:out value="${commenter.getTwitchUserName()}"/>
													</a>
													<small class="text-secondary">
														<h:dateDifference commentDate="${comment.getCreatedAt().time}"/>
													</small>
												</small>
												<br>
												<small class="font-weight-bold">
													<c:out value="${comment.getCommentBody()}"/>
												</small>
											</span>

											
										</div>
										<small>
											<i class="dropdown bi bi-three-dots-vertical btn btn-primary">
											</i>
										</small>
									</div>
								<div class="action mt-2 align-items-center">
									<div class="comment-actions d-flex justify-content-between align-items-center ">
										<div class="comment-crud ">
											<c:if test ="${userName.getId() == commenter.getId()}">
												<c:if test="${comment.getIsEditing()}">
												<input type="text" id="comment" class="form-control" value="${comment.getCommentBody()}" />
												<button onClick="submitEdit(this)" value="${channelData.get('login')}" data-value="${comment.getId()}" data-channel="${channelData.get('login')}" class="btn btn-dark">post</button>
												</c:if>
												<a href="javascript:void(0)" id="editComment" onClick="editComment(this)" data-value="${comment.getId()}" data-channel="${channelData.get('login')}" class="text-decoration-none"><small class="text-success">Edit</small></a>
												<a href="javascript:void(0)" id="deleteComment" onClick="deleteComment(this)" data-value="${comment.getId()}" data-channel="${channelData.get('login')}" class="text-decoration-none"><small class="text-danger">Delete</small></a>
												<c:out value="${comment.getId()}"/>
											</c:if>
										</div>
										<div class="likes-dislikes">
											<i class="bi bi-hand-thumbs-up"></i>
											<i class="bi bi-hand-thumbs-down"></i>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
							
						</div>
					</div>
				</div>
			</div>
			<div class="chat">
				<iframe src="https://www.twitch.tv/embed/${channelData.get('login')}/chat?darkpopout&parent=localhost"
				height="100%"
				width="100%"
				</iframe>
			</div>
		</div>
	</body>
</html>
