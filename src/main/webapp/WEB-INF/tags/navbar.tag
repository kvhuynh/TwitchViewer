c<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="name" required="true" %>
    <nav id="bootstrap-overrides" class="navbar navbar-expand fixed-top navbar-light bg-dark text-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
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
                <a class="nav-link" href="/logout">Logout</a>
              <% } %>
            </li> 
            </ul>
        </div>
        </div>
    </nav>
