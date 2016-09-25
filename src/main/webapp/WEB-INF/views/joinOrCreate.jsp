<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<body>
	<div class="row">
		<div class="col-lg-5">
			<a href="<c:url value="/game/createGame" />"> <img
				class="img-circle"
				src="<c:url value="/resources/images/play.png" />" alt="Play image"
				width="100" height="100">
			</a>
		</div>
		<div class="col-lg-7">
			<h2>
				<spring:message code="game.label.createNew" />
			</h2>
		</div>
	</div>
	<div>
		<c:if test="${not empty inactiveGames}">
			<c:forEach var="game" items="${inactiveGames}">
				<div class="row">
					<div class="col-md-3">
						<spring:message code="game.label.inactiveGame" />${game.id}
					</div>
					<div class="col-md-3">${game.numberOfPlayers}</div>
					<div>
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
</body>