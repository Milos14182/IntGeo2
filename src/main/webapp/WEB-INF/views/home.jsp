<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<div class="body-content">
    <div class="container marketing">
        <div class="row">
            <div class="col-md-6">
                <h2>
                    <a href="<c:url value="/game" />"> 
                        <img class="img-circle"
                             src="<c:url value="/resources/images/play.png" />"
                             alt="Play image" width="140" height="140"> 
                        <spring:message code="home.label.play" />
                    </a>
                </h2>
            </div>
        </div>
    </div>
    <div>
        <h1>
            <b><spring:message code="howto.play.title" /></b>
            <spring:message code="howto.play.title.rules" />
        </h1>
        <div>
            <div>
                <spring:message code="howto.play.title.desc" />
            </div>
            <br /> <br /> <b><spring:message code="howto.play.rules.title" /></b><br />
            <br />
            <div>
                <spring:message code="howto.play.rules" />
                <br />
                <spring:message code="howto.play.values" />
                <b><spring:message code="play.label.state" /></b>,<b><spring:message
                        code="play.label.city" /></b>,<b><spring:message
                        code="play.label.river" /></b>,<b><spring:message
                        code="play.label.mountain" /></b>,<b><spring:message
                        code="play.label.lake" /></b>,<b><spring:message
                        code="play.label.plant" /></b>
                    <spring:message code="howto.play.and" />
                <b><spring:message code="play.label.animal" /></b><br /> <br />
                <h4>
                    <spring:message code="howto.play.winner" />
                </h4>
                <br /> <br /> <b><spring:message code="howto.play.scores" /></b><br />
                <spring:message code="howto.play.left.ten" />
                <b>10</b>
                <spring:message code="howto.play.right.ten" />
                <br />
                <spring:message code="howto.play.left.ten" />
                <b>5</b>
                <spring:message code="howto.play.right.five" />
                <br />
                <spring:message code="howto.play.special" />
            </div>
        </div>
    </div>
</div>