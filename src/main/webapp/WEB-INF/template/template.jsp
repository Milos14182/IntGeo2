<!DOCTYPE HTML>
<%@ include file="/WEB-INF/template/includes.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
         pageEncoding="ISO-8859-2"%>

<html>
    <head>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <meta name="description"
              content="<tiles:insertAttribute name="page_description" ignore="true"/>">
<!--        <link href="<c:url value="/resources/css/template.css"/>"
              rel="stylesheet" type="text/css" />-->
        <link href="<c:url value="/resources/images/how_to.png" />"  rel="icon">
        <c:url var="cssURL" value="/resources/css/home.css" />

        <link href="<c:url value="/resources/css/bootstrap.min.css" />"
              rel="stylesheet">
        <script
        src="<c:url value="/resources/js/ie-emulation-modes-warning.js" />"></script>
        <link href="<c:url value="/resources/css/carousel.css" />"
              rel="stylesheet">

        <link href="${cssURL}" rel="stylesheet" type="text/css" />

        <!--<script src="<c:url value="/resources/js/jquery/jquery-1.11.3.js"/>"></script>-->
        <script src="<c:url value="/resources/js/main.js"/>"></script>
        <!--<script src="<c:url value="/resources/js/sockjs-0.3.4.min.js" />"></script>-->
        <script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
        <script src="<c:url value="/resources/js/stomp.min.js" />"></script>
        <script src="<c:url value="/resources/js/greetingsSocket.js" />"></script>

        <meta charset="utf-8">

    </head>
    <body>
        <div id="banner">
            <tiles:insertAttribute name="header" />
        </div>
        <div class="content">
            <tiles:insertAttribute name="content" />
        </div>
        <div></div>
        <div id="footer_wrapper">
            <tiles:insertAttribute name="footer" />
        </div>

        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/js/holder.min.js" />"></script>
        <script
        src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"></script>
    </body>
</html>