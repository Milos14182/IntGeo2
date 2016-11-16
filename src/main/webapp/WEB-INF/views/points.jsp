<%@ page language="java" contentType="text/html; charset=Windows-1250"
	pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="body-content">
	<c:if test="${userData == null}">
		Login to see points
	</c:if>
	<c:if test="${userData != null}">
		<div>Player - ${userData.firstname} ${userData.lastname}</div>
		<h1>Score</h1>
		<c:forEach var="gameData" items="${gameDatas}">
				<c:if test="${gameData.username==userData.username}">
					<div>Game id: ${gameData.gameId} - ${gameData.score} points</div>
				</c:if>
		</c:forEach>
	</c:if>
</div>
