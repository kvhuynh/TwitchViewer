<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="h" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ attribute name="name" required="true" %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <nav id="bootstrap-overrides" class="navbar navbar-expand fixed-top navbar-light bg-dark text-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <h:sidebar name="test" />
      <div class="d-flex justify-content-around collapse navbar-collapse text-light" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="/">Twitch Homepage</a>
                    <form:form action="/channels/search/submit" modelAttribute="channel" method="POST" class="col-5">
                        <div class="form-group">
                            <form:input class="form-control" path="displayName" type="text" placeholder="Search for anything"></form:input>
                        </div>
                    </form:form> 
          <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="#">Channels </a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="#">Emotes</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="/about">About</a>
            </li>
            <li class="nav-item">
              <% if (session.getAttribute("uuid") == null) { %>
                <a class="nav-link" href="/login-register">Login</a>
              <% } else {%>
                <div class="dropdown">
                  <button class="btn btn-secondary dropdown-toggle bg-dark border-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <c:out value="${userName.twitchUserName}"/>
                  </button>
                  <div class="dropdown-menu bg-dark" aria-labelled by="dropdownMenuButton">
                    <a class="dropdown-item" href="/profile">Profile</a>
                    <a class="dropdown-item" href="/friends">Friends</a>
                    <a class="dropdown-item" href="/logout">Logout</a>
                  </div>
                </div>
              <% } %>
            </li> 
            </ul>
        </div>
        </div>
    </nav>
