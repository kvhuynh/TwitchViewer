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
	<link rel="stylesheet" href="/css/login.css"/>
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<title>Login</title>
</head>
	<body>
		<h:navbar name="test" />

    <!-- original section -->

    <!-- <section class="vh-100">
        <div class="container-fluid h-custom">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
              <img src="https://www.freepnglogos.com/uploads/twitch-app-logo-png-3.png"
                class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
              <form:form action="/login" modelAttribute="newLogin" method="POST">
                <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                    <h1>Welcome Back</h1>
                </div>
      
                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0"></p>
                </div>
      

                <div class="form-outline mb-4">
                  <form:errors path="email"></form:errors>
                  <form:input path="email" class="form-control form-control-lg" placeholder="Enter a valid email address"></form:input>
                  <form:label path="email" class="form-label">Email address</form:label>
                </div>
      

                <div class="form-outline mb-3">
                    <form:errors path="password"></form:errors>
                    <form:input path="password" type="password" class="form-control form-control-lg"
                    placeholder="Enter password" ></form:input>
                    <form:label path="password" class="form-label">Password</form:label>
                </div>
      
                <div class="d-flex justify-content-between align-items-center">

                  <div class="form-check mb-0">
                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                    <label class="form-check-label" for="form2Example3">
                      Remember me
                    </label>
                  </div>
                  <a href="#!" class="text-light">Forgot password?</a>
                </div>
      
                <div class="text-center text-lg-start mt-4 pt-2">
                  <button type="submit" class="btn btn-primary btn-lg"
                    style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                  <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="#!"
                      class="link-danger">Register</a></p>
                </div>
              </form:form>
            </div>
          </div>
        </div>
      </section> -->
          



    <section class="vh-100">
        <div class="container-fluid h-custom">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
              <img src="https://www.freepnglogos.com/uploads/twitch-app-logo-png-3.png"
                class="img-fluid" alt="Sample image">
            </div>
            <div id="carousel" class="carousel slide col-md-8 col-lg-6 col-xl-4 offset-xl-1" data-ride="carousel" data-interval="false">
              <div class="carousel-inner">

                <c:choose>
                  <c:when test="${hasRegistrationError == null}">
                    <div class="carousel-item active">
                      <form:form action="/login" modelAttribute="newLogin" method="POST">
                        <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                          <!-- <h1 class="lead fw-normal mb-0 me-3">Welcome Back</h1> -->
                            <h1>Welcome Back</h1>
                        </div>
              
                        <div class="divider d-flex align-items-center my-4">
                          <p class="text-center fw-bold mx-3 mb-0"></p>
                        </div>
              
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                          <form:input path="email" class="form-control form-control-lg" placeholder="Enter a valid email address"></form:input>
                          <form:label path="email" class="form-label">Email address</form:label>
                          <br>
                          <form:errors path="email"></form:errors>
  
                        </div>
              
                        <!-- Password input -->
                        <div class="form-outline mb-3">
                            <form:input path="password" type="password" class="form-control form-control-lg"
                            placeholder="Enter password" ></form:input>
                            <form:label path="password" class="form-label">Password</form:label>
                            <br>
  
                            <form:errors path="password"></form:errors>
                        </div>
              
                        <div class="d-flex justify-content-between align-items-center">
                          <!-- Checkbox -->
                          <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                            <label class="form-check-label" for="form2Example3">
                              Remember me
                            </label>
                          </div>
                          <a href="#!" class="text-light">Forgot password?</a>
                        </div>
              
                        <div class="text-center text-lg-start mt-4 pt-2">
                          <button type="submit" class="btn btn-primary btn-lg"
                            style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                          <p class="small fw-bold mt-2 pt-1 mb-0">
                            Don't have an account?
                            <a href="#carousel" class="carousel-control link-danger" data-slide="next">Register</a>
                            </p>
                        </div>
                      </form:form>
                  </div>
  
                  <div class="carousel-item">
                    <form:form action="/register" modelAttribute="newUser" method="POST">
                      <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                          <h1>Register</h1>
                      </div>
                      <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0"></p>
                      </div>
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="twitchUserName" placeholder="Username..."></form:input>
                          <form:label path="twitchUserName">User Name</form:label>
                          <form:errors path="twitchUserName"></form:errors>
                      </div>               
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="email" placeholder="123@gmail.com"></form:input>
                          <form:label path="email">Email</form:label>
                          <form:errors path="email"></form:errors>
                      </div>     
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="password" placeholder="**************"></form:input>
                          <form:label path="password">Password</form:label>
                          <form:errors path="password"></form:errors>
  
                      </div>
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="confirm" placeholder="**************"></form:input>
                          <form:label path="confirm">Confirm Password</form:label>
                          <form:errors path="confirm"></form:errors>
                      </div>
                      <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                          style="padding-left: 2.5rem; padding-right: 2.5rem;">Register</button>
                          <p class="small fw-bold mt-2 pt-1 mb-0">
                            <a href="#carousel" class="carousel-control link-danger" data-slide="prev">Go Back</a>
                            </p>
                      </div>
                  </form:form>
                  </div>
                  </c:when>
                  <c:otherwise>
                    <div class="carousel-item">
                      <form:form action="/login" modelAttribute="newLogin" method="POST">
                        <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                          <!-- <h1 class="lead fw-normal mb-0 me-3">Welcome Back</h1> -->
                            <h1>Welcome Back</h1>
                        </div>
              
                        <div class="divider d-flex align-items-center my-4">
                          <p class="text-center fw-bold mx-3 mb-0"></p>
                        </div>
              
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                          <form:input path="email" class="form-control form-control-lg" placeholder="Enter a valid email address"></form:input>
                          <form:label path="email" class="form-label">Email address</form:label>
                          <br>
                          <form:errors path="email"></form:errors>
  
                        </div>
              
                        <!-- Password input -->
                        <div class="form-outline mb-3">
                            <form:input path="password" type="password" class="form-control form-control-lg"
                            placeholder="Enter password" ></form:input>
                            <form:label path="password" class="form-label">Password</form:label>
                            <br>
  
                            <form:errors path="password"></form:errors>
                        </div>
              
                        <div class="d-flex justify-content-between align-items-center">
                          <!-- Checkbox -->
                          <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                            <label class="form-check-label" for="form2Example3">
                              Remember me
                            </label>
                          </div>
                          <a href="#!" class="text-light">Forgot password?</a>
                        </div>
              
                        <div class="text-center text-lg-start mt-4 pt-2">
                          <button type="submit" class="btn btn-primary btn-lg"
                            style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                          <p class="small fw-bold mt-2 pt-1 mb-0">
                            Don't have an account?
                            <a href="#carousel" class="carousel-control link-danger" data-slide="next">Register</a>
                            </p>
                        </div>
                      </form:form>
                  </div>
  
                  <div class="carousel-item active">
                    <form:form action="/register" modelAttribute="newUser" method="POST">
                      <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                          <h1>Register</h1>
                      </div>
                      <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0"></p>
                      </div>
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="twitchUserName" placeholder="Username..."></form:input>
                          <form:label path="twitchUserName">User Name</form:label>
                          <form:errors path="twitchUserName"></form:errors>
                      </div>               
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="email" placeholder="123@gmail.com"></form:input>
                          <form:label path="email">Email</form:label>
                          <form:errors path="email"></form:errors>
                      </div>     
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="password" placeholder="**************"></form:input>
                          <form:label path="password">Password</form:label>
                          <form:errors path="password"></form:errors>
  
                      </div>
                      <div class="mb-3">
                          <form:input class="form-control" type="text" path="confirm" placeholder="**************"></form:input>
                          <form:label path="confirm">Confirm Password</form:label>
                          <form:errors path="confirm"></form:errors>
                      </div>
                      <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                          style="padding-left: 2.5rem; padding-right: 2.5rem;">Register</button>
                          <p class="small fw-bold mt-2 pt-1 mb-0">
                            <a href="#carousel" class="carousel-control link-danger" data-slide="prev">Go Back</a>
                            </p>
                      </div>
                  </form:form>
                  </div>
                  </c:otherwise>
                </c:choose>
  
              </div>
            </div>

          </div>
        </div>
      </section>
	</body>
</html>