<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<body>
    <div style="width: 99.2%">
        <div class="row createGame">
            <div class="col-lg-2">
                <a href="<c:url value="/game/createGame" />"> <img
                        class="img-circle"
                        src="<c:url value="/resources/images/play.png" />" alt="Play image"
                        width="100" height="100">
                </a>
            </div>
            <div class="col-lg-1">
                <h2>
                    <spring:message code="game.label.createNew" />
                </h2>
            </div>
        </div>
        <br />
        <br />
        <div>
            <c:if test="${not empty inactiveGames}">
                <c:forEach var="game" items="${inactiveGames}">
                    <div class="col-lg-3 gameRow">
                        <div class="col-md-3">
                            <spring:message code="game.label.inactiveGame" />${game.id}
                        </div>
                        <div class="col-md-1">${game.numberOfPlayers}</div>
                        <div class="gameLink">
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
    </div>
</body>