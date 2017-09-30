<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<div class="body-content">
    <div class="row create-game-row">
        <form:form class="col-lg-5 col-md-5 col-sm-5 create-game-column" method="get" action="/game/createGame">
            <button class="create-game-button" type="submit">
                <img
                    class="img-circle"
                    src="<c:url value="/resources/images/play.png" />" alt="Play image"
                    width="100" height="100">
            </button>
            <input class="form-control create-game-input" name="gamePoints" value="300">
        </form:form>
        <div class="col-lg-5 col-md-5 col-sm-5 create-game-column">
            <h2>
                <spring:message code="game.label.createNew" />
            </h2>
        </div>
    </div>
    <br />
    <br />     
    <c:if test="${not empty startedGames}">
        <h2 class="row create-game-row">
            <spring:message code="game.label.startedGames" />
        </h2>
        <br />
        <br />
        <c:forEach var="game" items="${startedGames}">
            <div class="row col-lg-4">
                <div class="col-md-3">
                    <spring:message code="game.label.inactiveGame" />${game.id}
                </div>
                <div class="col-md-1">${game.numberOfPlayers}</div>
                <div class="col-md-3 gameLink">
                    <a href="<c:url value="/game/joinGame/${game.id}" />"> <img
                            class="img-circle"
                            src="<c:url value="/resources/images/play.png" />"
                            alt="Join game" width="40" height="40">
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <br />
    <br />
    <c:if test="${not empty inactiveGames}">
        <h2 class="row create-game-row">
            <spring:message code="game.label.inactiveGames" />
        </h2>
        <br />
        <br />
        <c:forEach var="game" items="${inactiveGames}">
            <div class="row col-lg-4">
                <div class="col-md-3">
                    <spring:message code="game.label.inactiveGame" />${game.id}
                </div>
                <div class="col-md-1">${game.numberOfPlayers}</div>
                <div class="col-md-3 gameLink">
                    <a href="<c:url value="/game/joinGame/${game.id}" />"> <img
                            class="img-circle"
                            src="<c:url value="/resources/images/play.png" />"
                            alt="Join game" width="40" height="40">
                    </a>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>