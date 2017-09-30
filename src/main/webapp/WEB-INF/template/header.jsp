<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                            aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/"/>"> <img
                            class="ig-logo" alt="Intresting Geography"
                            src="<c:url value="/resources/images/logo.png" />" />
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">

                    <ul class="nav navbar-nav">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href='<c:url value ="/profile"/>'><spring:message
                                        code="header.label.profile" /></a></li>
                                </sec:authorize>
                        <li><a href='<c:url value ="/about"/>'><spring:message
                                    code="header.label.about" /></a></li>
                        <li><a href='<c:url value ="/contact"/>'><spring:message
                                    code="header.label.contact" /></a></li>
                                <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href='<c:url value ="/game"/>'><spring:message
                                        code="home.label.play" /></a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href='<c:url value ="/admin"/>'><spring:message
                                        code="header.label.admin" /></a></li>
                                </sec:authorize>
                        <li><a href='<c:url value ="/scoreboard"/>'><spring:message
                                    code="header.label.scoreboard" /></a></li>
                    </ul>

                    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <c:url value="/login" var="login" />
                        <form class="navbar-form navbar-right" id="loginUser"
                              method="POST" action="${login}">                            
                            <div class="form-group">
                                <input name="username" type="text" placeholder="Username"
                                       class="form-control" />
                            </div>
                            <div class="form-group">
                                <input name="password" type="password" placeholder="Password"
                                       class="form-control" />
                            </div>
                            <button type="submit" class="btn btn-success">
                                <spring:message code="home.label.signIn" />
                            </button>
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <div class="navbar-right sign-out-button">
                            <div class="row">
                                <div class="col-md-6 user-name">${userDetails.firstname}
                                    ${userDetails.lastname}</div>
                                <div class="col-md-1">
                                    <a href='<c:url value="/logout"/>' class="btn btn-success"><spring:message
                                            code="home.label.signOut" /></a>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </nav> 
        <c:if test="<%= request.getParameter(\"error\")!=null %>">
            <div style="margin-top: -20px" class="alert warning">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span>
                <spring:message code="login.error.message"></spring:message>
                </div>
        </c:if>  
    </div>
</div>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide"
                 src="<c:url value="/resources/images/slide1.jpg" />"
                 alt="First slide">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <c:url value="/register" var="register" />
                            <a class="btn btn-lg btn-primary" href='${register}' role="button"><spring:message
                                    code="home.label.signUp" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <a class="btn btn-lg btn-primary" href="${home}" role="button"><spring:message
                                    code="home.label.learnMore" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
        </div>
        <div class="item">
            <img class="second-slide"
                 src="<c:url value="/resources/images/slide2.jpg" />"
                 alt="Second slide">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <c:url value="/register" var="register" />
                            <a class="btn btn-lg btn-primary" href='${register}' role="button"><spring:message
                                    code="home.label.signUp" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <a class="btn btn-lg btn-primary" href="${home}" role="button"><spring:message
                                    code="home.label.learnMore" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
        </div>
        <div class="item">
            <img class="third-slide"
                 src="<c:url value="/resources/images/slide3.jpg" />"
                 alt="Third slide">
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <c:url value="/register" var="register" />
                            <a class="btn btn-lg btn-primary" href='${register}' role="button"><spring:message
                                    code="home.label.signUp" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')">
                <div class="container">
                    <div class="carousel-caption">
                        <p>
                            <a class="btn btn-lg btn-primary" href="${home}" role="button"><spring:message
                                    code="home.label.learnMore" /></a>
                        </p>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button"
       data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
                             aria-hidden="true"></span> <span class="sr-only">Previous</span>
    </a> <a class="right carousel-control" href="#myCarousel" role="button"
            data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
                             aria-hidden="true"></span> <span class="sr-only">Next</span>
    </a>
</div>