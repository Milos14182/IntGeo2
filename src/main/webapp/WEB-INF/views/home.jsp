<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<body>
	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-md-4">
				<h2>
					<a href="<c:url value="/game" />"> <img class="img-circle"
						src="<c:url value="/resources/images/play.png" />"
						alt="Play image" width="140" height="140"> <spring:message
							code="home.label.play" />
					</a>
				</h2>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-md-4">
				<h2>
					<a href="<c:url value="/points" />"> <img class="img-circle"
						src="<c:url value="/resources/images/coin.png" />"
						alt="Points image" width="140" height="140"> <spring:message
							code="home.label.points" />
					</a>
				</h2>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-md-4">
				<h2>
					<a href="<c:url value="/how_to" />"> <img class="img-circle"
						src="<c:url value="/resources/images/how_to.png" />"
						alt="How to play image" width="140" height="140"> <spring:message
							code="home.label.howToPlay" />
					</a>
				</h2>
			</div>
			<!-- /.col-lg-4 -->
		</div>


	</div>
</body>
</html>