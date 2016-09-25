<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
	<div class="container">
		<h2>
			<spring:message code="contact.label.header" />
		</h2>
		<c:url value="/contact" var="contact" />
		<form:form modelAttribute="contact" name="contactUs" id="contactForm"
			method="POST" action="${contact}">
			<div class="form-group">
				<form:label path="firstname">
					<spring:message code="register.label.firstname" />
				</form:label>
				<form:input path="firstname" id="inputFirstname"
					class="form-control" />
				<form:errors path="firstname" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label path="lastname">
					<spring:message code="register.label.lastname" />
				</form:label>
				<form:input path="lastname" id="inputLastname"
					class="form-control" />
				<form:errors path="lastname" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label path="email">
					<spring:message code="register.label.email" />
				</form:label>
				<form:input path="email" id="inputEmail" class="form-control" />
				<form:errors path="email" cssClass="error" />
			</div>
			<div class="form-group">
				<form:label path="age">
					<spring:message code="register.label.age" />
				</form:label>
				<form:input path="age" id="inputAge" class="form-control" />
				<form:errors path="age" cssClass="error" />
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
				<form:label path="message">
					<spring:message code="contact.label.message" />
				</form:label>
				<form:textarea rows="5" id="inputMessage" path="message"
					class="form-control" />
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				<spring:message code="contact.label.button" />
			</button>
		</form:form>
	</div>
</body>