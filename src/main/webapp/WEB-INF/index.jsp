<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<link rel="stylesheet" href="/css/main.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    
    
	<title>Insert title here</title>
</head>
<body>
        <h:navbar name="test" />
        <!-- <div class="container ">
            <div class="row h-100">
                <form:form action="/channels" modelAttribute="channel" method="POST" class="col-12">
                    <div class="form-group">
                    <form:label path="displayName" class="text-light" for="formGroupExampleInput">Search Streamers:</form:label>
                    <form:input path="displayName" type="text" class="form-control" id="formGroupExampleInput" placeholder="Riot Games"></form:input>
                    </div>
                </form:form>   
            </div>
        </div> -->

        

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
      
        <div>
            <canvas id="myChart"></canvas>
          </div>


          <h1 class="text-light d-flex align-items-center justify-content-center">Favorites</h1>
    
        <!-- <iframe src="https://www.twitch.tv/embed/extraemily/chat?parent=localhost"
            height="1280"
            width="720">
        </iframe> -->
          <!-- <iframe
        src="https://player.twitch.tv/?channel=extraemily&parent=localhost&autoplay=false"
        height="720"
        width="1280"
        allowfullscreen> -->
                        <!-- <p style="color:white" ><c:out value="${popularChannel.get('user_name')}" /></p> -->
                      <!-- <iframe
        src="https://player.twitch.tv/?channel=${popularChannel.get('user_login')}&parent=localhost&autoplay=false"
        height="720"
        width="1280"
        allowfullscreen>
        </iframe> -->



          <div class="card-deck d-flex flex-wrap justify-content-center">
            <c:forEach var="popularChannel" items="${popularChannels}">
                <c:set var="thumbnail_image" value ="${popularChannel.get('thumbnail_url')}"></c:set>
            <div class="card m-3" style="width: 18rem;">
                <a class="text-decoration-none text-dark" href="channels/${popularChannel.get('user_login')}">
                    <img class="card-img-top" src="${thumbnail_image.replace('{width}', '1280').replace('{height}', '720')}" alt="stream thumbnail for '${popularChannel.get('user_name')}'">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${popularChannel.get('user_name')}"/></h5>
                        <p class="card-text text-truncate"><c:out value="${popularChannel.get('title')}"/></p>
                        <p class="card-text"><c:out value="${popularChannel.get('game_name')}"/></p>
                    </div>
                </a>
            </div>
            </c:forEach>
            </div>
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
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</body>
  
</html>