<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
    <div class="container">
        <c:url
            value="/profile/edit/{username}?${_csrf.parameterName}=${_csrf.token}"
            var="edit" />
        <form:form modelAttribute="userData" method="POST" action="${edit}"
                   enctype="multipart/form-data">
            <h2 class="form-signin-heading">
                <spring:message code="register.label.header" />
            </h2>
            <div class="row">
                <div class="col-md-7">
                    <c:if test="${not empty userData.base64Image}">
                        <img class="featurette-image img-responsive center-block"
                             src="data:image/png;base64,${userDetails.base64Image}"
                             alt="User image" />
                    </c:if>
                    <c:if test="${empty userData.base64Image}">
                        <img class="featurette-image img-responsive center-block"
                             data-src="holder.js/500x500/auto" alt="Generic placeholder image">
                    </c:if>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="form-group">
                <form:label path="email">
                    <spring:message code="register.label.email" />
                </form:label>
                <form:input path="email" id="inputEmail" class="form-control" />
                <form:errors path="email" cssClass="error" />
            </div>

            <form:input type="hidden" path="username" />

            <div class="form-group">
                <form:label path="password">
                    <spring:message code="register.label.password" />
                </form:label>
                <form:password path="password" id="inputPassword"
                               class="form-control"/>
                <form:errors path="password" cssClass="error" />
            </div>
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
                <form:input path="lastname" id="inputLastname" class="form-control" />
                <form:errors path="lastname" cssClass="error" />
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
                        <form:errors path="userImage" cssClass="error" />
                    </div>
                    <div style="padding-top: 15px;" class="col-md-6">
                        <div class="btn btn-primary" onClick="removeUserImage()">
                            <spring:message code="register.button.deletePicture" />
                        </div>
                    </div>
                </div>
            </div>
            <br />
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <spring:message code="profile.button.edit" />
            </button>
        </form:form>
    </div>
</body>