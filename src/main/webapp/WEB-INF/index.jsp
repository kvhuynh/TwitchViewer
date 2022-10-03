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
	<link rel="stylesheet" href="/css/index.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script type="text/javascript" src="<c:url value='/js/favorite.js'/>"></script>
	<title>Insert title here</title>
</head>
<body>
        <h:navbar name="test" />
        <div class="d-flex align-items-center justify-content-center" style="height: 100vh">
            <div class="container ">
                <div class="row h-100">
                    <form:form action="channels/search/submit" modelAttribute="channel" method="POST" class="col-12">
                        <div class="form-group">
                            <form:label path="displayName" class="text-light" for="formGroupExampleInput">Search Streamers:</form:label>
                            <form:input path="displayName" type="text" class="form-control" placeholder="Riot Games"></form:input>
                        </div>
                    </form:form>   
                </div>
            </div>
        </div>
      
        <button class="btn btn-light" id="toggle-favorites">click me</button>
            <div>
              <a href="#top-channels" class="btn btn-block btn-light" style="margin-top: 5px;">find top</a>
            </div>


        <h1><c:out value=""/></h1>

        <div>
          <canvas id="myChart"></canvas>
        </div>

        <h1 class="text-light d-flex align-items-center justify-content-center">Favorites</h1>
          <% if (session.getAttribute("uuid") == null) { %>
            <a class="nav-link" href="/login-register">Sign in to see your live favorites</a>
          <% } else { %>
            <p>DISPLAY FAVORITES HERE</p>
          <% } %>

        <h1 id="top-channels" class="text-light d-flex align-items-center justify-content-center">Top 100 Live Channels</h1>
          <div class="card-deck d-flex flex-wrap justify-content-center">
              <c:forEach var="popularChannel" items="${popularChannels}">
                <c:set var="thumbnail_image" value ="${popularChannel.get('thumbnail_url')}"></c:set>
              <div id="card-border" class="card m-3" style="width: 25rem; height: 21.5rem;">
                  <a class="text-decoration-none text-dark" href="channels/${popularChannel.get('user_login')}">
                    <div class="header">
                      <img class="card-img-top" src="${thumbnail_image.replace('{width}', '1280').replace('{height}', '720')}" alt="stream thumbnail for '${popularChannel.get('user_name')}'">
                      <p class="viewer-count card-text"><c:out value="${popularChannel.get('viewer_count')}"/> viewers</p>
                    </div>
                      <div class="card-body p-1 text-light">
                          <p></p>
                          <h5 class="card-title"><c:out value="${popularChannel.get('user_name')}"/></h5>
                          <p class="card-text text-truncate"><c:out value="${popularChannel.get('title')}"/></p>
                          <p class="card-text"><c:out value="${popularChannel.get('game_name')}"/></p>
                      </div>
                  </a>
              </div>
              </c:forEach>
            </div>
            <!-- <canvas id="myChart" width="400" height="400"></canvas>
            <script>
                const labels = [
                  'January',
                  'February',
                  'March',
                  'April',
                  'May',
                  'June',
                ];
              
                const data = {
                  labels: labels,
                  datasets: [{
                    label: 'My First dataset',
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: [0, 10, 5, 2, 20, 30, 45],
                  }]
                };
              
                const config = {
                  type: 'line',
                  data: data,
                  options: {}
                };
              </script>
              <script>
                const myChart = new Chart(
                  document.getElementById('myChart'),
                  config
                );
              </script>
                -->
                <script>
                  $(document).ready(function() {
                    $("#toggle-favorites").click(function() {
                        alert("handler for .click() called.")
                    });
                });
                
                </script>
</body>
  
</html>