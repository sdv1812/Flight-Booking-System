<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a new user</title>

<link href="/css/bootstrap.min.css" rel="stylesheet"> 

</head>
<body>

	<nav role="navigation">
	<ul>
		<li><a href="/login">Back</a></li>
	</ul>
	</nav>

	<h1>Create a new user</h1>
<div class="container">
	<form:form method="POST" modelAttribute="form" class="form-signin">
		<h2 class="form-signin-heading">Create your account</h2>
		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label>Email </label>
				<form:input type="text" path="email" class="form-control"
					placeholder="Email" autofocus="true"></form:input>
				<form:errors path="email"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<label>Password </label>
				<form:input type="password" path="password" class="form-control"
					placeholder="Password"></form:input>
				<form:errors path="password"></form:errors>
			</div>
		</spring:bind>

		<spring:bind path="passwordRepeated">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<label>Confirm Password </label>
				<form:input type="password" path="passwordRepeated"
					class="form-control" placeholder="Confirm your password"></form:input>
				<form:errors path="passwordRepeated"></form:errors>
			</div>
		</spring:bind>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	</form:form>
<spring:hasBindErrors name="form">
<c:forEach var="error" items="${errors.allErrors}">
	<b><spring:message message="${error}" /></b>
	<br />
</c:forEach>
    </spring:hasBindErrors>
	</div>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/tether.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>