<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="name" required="true" %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value='/js/sidebarRefresh.js'/>"></script>



<button id="open-menu" class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions"><i style="font-size:24px" class="fa">&#xf0c9;</i></button>

<div class="offcanvas offcanvas-start bg-dark text-light" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Favorite Channels</h5>
    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <% if (session.getAttribute("uuid") == null) { %>
      <a class="nav-link" href="/login-register">Login or create an account to view your favorite channels</a>
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
        <ul id="favorites">
            <c:forEach var="favoriteChannel" items="${userName.getChannels()}">
              <li><c:out value="${favoriteChannel.getDisplayName()}"/></li>
            </c:forEach>
        </ul>
      </div>
    <% } %>
  </div>
</div>

