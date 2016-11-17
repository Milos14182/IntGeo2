<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
    <div class="container">
        <h2>
            <spring:message code="about.label.header" />
        </h2>
        <spring:message code="about.text" />
    </div>

    <!-- Bootstrap core JavaScript
================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery.min.js" />" rel="stylesheet"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />" rel="stylesheet"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="<c:url value="/resources/js/holder.min.js" />" rel="stylesheet"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />" rel="stylesheet"></script>
</body>
</html>