<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="body-content">
    <div class="scoreboard-header">
        <div class="row scoreboard-header-row">
            <div class="col-sm-3 col-md-3 col-xs-3">
                <a class="scoreboard-tab btn btn-info" href='<c:url value ="/scoreboard?type=montly"/>'>
                    <spring:message code="scoreboard.label.montly" />
                </a>
            </div>
            <div class="col-sm-3 col-md-3 col-xs-3">
                <a class="scoreboard-tab btn btn-info" href='<c:url value ="/scoreboard?type=weekly"/>'>
                    <spring:message code="scoreboard.label.weekly" />
                </a>
            </div>
            <div class="col-sm-3 col-md-3 col-xs-3">
                <a class="scoreboard-tab btn btn-info" href='<c:url value ="/scoreboard?type=daily"/>'>
                    <spring:message code="scoreboard.label.daily" />
                </a>
            </div>
            <div class="col-sm-3 col-md-3 col-xs-3">
                <a class="scoreboard-tab btn btn-info" href='<c:url value ="/scoreboard?type=winnings"/>'>
                    <spring:message code="scoreboard.label.winnings" />
                </a>
            </div>
        </div>
    </div>
    <br />
    <div class="scoreboard-body">
        <c:set var="typePath" value='<%= 
            request.getQueryString() != null ? request.getQueryString().split("=")[1].split("&")[0] : "montly"%>' />
        <ul class="nav nav-tabs scoreboard-tabs">
            <li class="active tab"><a class="tab-a" href='<c:url value ="/scoreboard?type=${typePath}&score=1000"/>'>1000</a></li>
            <li class="tab"><a class="tab-a" href='<c:url value="/scoreboard?type=${typePath}&score=750" />'>750</a></li>
            <li class="tab"><a class="tab-a" href='<c:url value="/scoreboard?type=${typePath}&score=500" />'>500</a></li>
            <li class="tab"><a class="tab-a" href='<c:url value="/scoreboard?type=${typePath}&score=300" />'>300</a></li>
            <li class="tab"><a class="tab-a" href='<c:url value="/scoreboard?type=${typePath}&score=100" />'>100</a></li>
            <li class="tab"><a class="tab-a" href='<c:url value="/scoreboard?type=${typePath}&score=50" />'>50</a></li>
        </ul>
        <div  class="scoreboard-body">
            <div class="row scoreboard-tabs">
                <div class="col-sm-2 col-md-2 col-xs-2 scoreboard-image">
                    <div class="scoreboard-body-column">
                    </div>
                </div>
                <div class="col-sm-2 col-md-2 col-xs-2">
                    <div class="scoreboard-body-column"><spring:message code="scoreboard.header.firstname" /></div>
                </div>
                <div class="col-sm-2 col-md-2 col-xs-2">
                    <div class="scoreboard-body-column"><spring:message code="scoreboard.header.lastname" /></div>
                </div>
                <div class="col-sm-2 col-md-2 col-xs-2">
                    <div class="scoreboard-body-column"><spring:message code="scoreboard.header.city" /></div>
                </div>
                <div class="col-sm-2 col-md-2 col-xs-2">
                    <div class="scoreboard-body-column"><spring:message code="scoreboard.header.score" /></div>
                </div>
            </div>
            <br />
            <c:forEach var="scoreboard" items="${scoreboards}">
                <div class="row scoreboard-body-row">
                    <div class="col-sm-2 col-md-2 col-xs-2 scoreboard-image">
                        <div class="scoreboard-body-column">
                            <c:if test="${not empty scoreboard.userImage}">
                                <img src="data:image/png;base64,${scoreboard.userImage}"
                                     alt="User image" class="img-circle img-responsive" />
                            </c:if>
                            <c:if test="${empty scoreboard.userImage}">
                                <img data-src="holder.js/500x500/auto"
                                     alt="Generic placeholder image"
                                     class="img-circle img-responsive">
                            </c:if>
                        </div>
                    </div>
                    <div class="col-sm-2 col-md-2 col-xs-2">
                        <div class="scoreboard-body-column">${scoreboard.firstname}</div>
                    </div>
                    <div class="col-sm-2 col-md-2 col-xs-2">
                        <div class="scoreboard-body-column">${scoreboard.lastname}</div>
                    </div>
                    <div class="col-sm-2 col-md-2 col-xs-2">
                        <div class="scoreboard-body-column">${scoreboard.city}</div>
                    </div>
                    <div class="col-sm-2 col-md-2 col-xs-2">
                        <div class="scoreboard-body-column">${scoreboard.score}</div>
                    </div>
                </div>
                <hr class="scoreboard-line" />
            </c:forEach>
        </div>
    </div>
</div>