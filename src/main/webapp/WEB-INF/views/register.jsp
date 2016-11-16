<%@ page language="java" contentType="text/html; charset=Windows-1250"
	pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-1250">
</head>
<body>
	<div class="container">
		<c:url value="/register?${_csrf.parameterName}=${_csrf.token}"
			var="register" />
		<form:form modelAttribute="userData" method="POST"
			action="${register}" enctype="multipart/form-data" accept-charset="Windows-1250">
			<h2 class="form-signin-heading">
				<spring:message code="register.label.header" />
			</h2>
			<div class="form-group">
				<form:label path="email">
					<spring:message code="register.label.email" />
				</form:label>
				<form:input path="email" id="inputEmail" class="form-control" />
				<form:errors path="email" cssClass="error">
					<spring:message code="registration.error.email" />
				</form:errors>
			</div>

			<div class="form-group">
				<form:label path="username">
					<spring:message code="register.label.username" />
				</form:label>
				<form:input path="username" id="inputUsername" class="form-control" />
				<form:errors path="username" cssClass="error">
					<spring:message code="registration.error.username" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="password">
					<spring:message code="register.label.password" />
				</form:label>
				<form:password path="password" id="inputPassword"
					class="form-control" />
				<form:errors path="password" cssClass="error">
					<spring:message code="registration.error.password" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="firstname">
					<spring:message code="register.label.firstname" />
				</form:label>
				<form:input path="firstname" id="inputFirstname"
					class="form-control" />
				<form:errors path="firstname" cssClass="error">
					<spring:message code="registration.error.firstname" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="lastname">
					<spring:message code="register.label.lastname" />
				</form:label>
				<form:input path="lastname" id="inputLastname" class="form-control" />
				<form:errors path="lastname" cssClass="error">
					<spring:message code="registration.error.lastname" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="age">
					<spring:message code="register.label.age" />
				</form:label>
				<form:input path="age" id="inputAge" class="form-control" />
				<form:errors path="age" cssClass="error">
					<spring:message code="registration.error.age" />
				</form:errors>
			</div>
			<div class="form-group">
				<form:label path="gender">
					<spring:message code="register.label.gender" />
				</form:label>
				<form:radiobutton path="gender" value="Male" />
				<spring:message code="register.label.gender.male" />
				<form:radiobutton path="gender" value="Female" />
				<spring:message code="register.label.gender.female" />
			</div>
			<div class="form-group">
				<form:label path="city">
					<spring:message code="register.label.city" />
				</form:label>
				<form:select class="form-control" path="city.name">
					<c:forEach var="city" items="${citys}">
						<form:option value="${city.name}" label="${city.name}" />
					</c:forEach>
				</form:select>
			</div>
			<div class="row">
				<div class="form-group">
					<div class="col-md-6">
						<form:label path="userImage">
							<spring:message code="register.label.userImage" />
						</form:label>
						<form:input type="file" path="userImage" />
						<form:errors path="userImage" class="control-label" />
					</div>
					<div style="padding-top: 15px;" class="col-md-6">
						<div class="btn btn-primary" onClick="removeUserImage()">
							<spring:message code="register.button.deletePicture" />
						</div>
					</div>
				</div>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				<spring:message code="register.button.register" />
			</button>
		</form:form>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"
		rel="stylesheet"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"
		rel="stylesheet"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<c:url value="/resources/js/holder.min.js" />"
		rel="stylesheet"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"
		rel="stylesheet"></script>
</body>
</html>