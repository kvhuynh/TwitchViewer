<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="/css/sidebar.css"/>
<%@ attribute name="name" required="true" %>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>



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
    <ul id="favorites" class="p-0">
        <c:forEach var="favoriteChannel" items="${userName.getChannels()}">
          <a href="/channels/${favoriteChannel.getLogin()}" class="text-decoration-none">
            <div class="d-flex align-items-center mb-3 channel">
              <img class="profile-pic-sidebar" src="${favoriteChannel.getProfileImageUrl()}" alt="" />
              <li><c:out value="${favoriteChannel.getDisplayName()}"/></li>
            </div>
          </a>
        </c:forEach>
    </ul>
    <% } %>
  </div>
  
</div>



