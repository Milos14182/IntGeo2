<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Windows-1250">
    </head>
    <body>
        <div class='container'>
            <div class="row">
                <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
                    <div class='well profile'>
                        <div class="col-sm-12">
                            <div class="col-xs-12 col-sm-8">
                                <h2>${userDetails.firstname}&nbsp${userDetails.lastname}</h2>
                                <p>
                                    <strong><spring:message code='register.label.username' />:
                                    </strong>${userDetails.username}
                                </p>
                                <p>
                                    <strong><spring:message code='register.label.age' />:</strong>
                                    ${userDetails.age}
                                </p>
                                <p>
                                    <strong><spring:message code='register.label.email' />:</strong>
                                    ${userDetails.email}
                                </p>
                                <p>
                                    <strong><spring:message code='register.label.gender' />:</strong>
                                    <c:if test="${userDetails.gender == 'Male'}">
                                        <spring:message code='register.label.gender.male' />
                                    </c:if>
                                    <c:if test="${userDetails.gender == 'Female'}">
                                        <spring:message code='register.label.gender.female' />
                                    </c:if>
                                </p>
                                <p>
                                    <strong><spring:message code='play.label.city' />:</strong>
                                    ${userDetails.city.name}
                                </p>
                            </div>
                            <div class="col-xs-12 col-sm-4 text-center">
                                <figure>
                                    <c:if test="${not empty userDetails.base64Image}">
                                        <img src="data:image/png;base64,${userDetails.base64Image}"
                                             alt="User image" class="img-circle img-responsive" />
                                    </c:if>
                                    <c:if test="${empty userDetails.base64Image}">
                                        <img data-src="holder.js/500x500/auto"
                                             alt="Generic placeholder image"
                                             class="img-circle img-responsive">
                                    </c:if>
                                </figure>
                            </div>
                            <div class="col-xs-12 col-sm-8">
                                <a href="<c:url value='/profile/edit/${userDetails.username}'/>"
                                   class="btn btn-primary"> <spring:message
                                        code="profile.button.edit" />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>