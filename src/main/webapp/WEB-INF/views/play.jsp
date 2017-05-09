<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body onload="connect(stompClient)">
    <audio autoplay loop>
        <source src="<c:url value="/resources/images/music.mp3" />">
    </audio>
    <div class="container playContainer">
        <input id="input_username" type="hidden" value="${userDetails.username}"/>
        <div id="character" class="pull-left character">${character}</div>
        <div id="scorePerRound" class="pull-right character">${scorePerRound}</div>
        <div id="roundTimer" class="pull-right character"></div>
        <div id="playerImage">
            <figure>
                <c:if test="${not empty userDetails.base64Image}">
                    <img src="data:image/png;base64,${userDetails.base64Image}"
                         alt="${userDetails.firstname}" class="img-circle-player img-responsive" />
                </c:if>
                <c:if test="${empty userDetails.base64Image}">
                    <img data-src="holder.js/500x500/auto"
                         alt="${userDetails.firstname}"
                         class="img-circle-player img-responsive">
                </c:if>
            </figure>
        </div>
        <div class="playTable">
            <c:forEach var="i" begin="0" end="5">
                <div id="user-${i}" style="margin-left: ${i*21}px; width: 20px">
                    <div id="stateResult-${i}"></div>
                    <div id="cityResult-${i}"></div>
                    <div id="mountainResult-${i}"></div>
                    <div id="riverResult-${i}"></div>
                    <div id="lakeResult-${i}"></div>
                    <div id="animalResult-${i}"></div>
                </div>
            </c:forEach>
        </div>

        <br /> <br />
        <div class="answersSend"><spring:message code="play.label.answersSend" /></div>
        <div class="row">
            <div class="form-inline">
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.state" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_state" type="text" class="form-control" />
                    </div>
                    <div id="stateResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.city" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_city" type="text" class="form-control" />
                    </div>
                    <div id="cityResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.mountain" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_mountain" type="text" class="form-control" />
                    </div>
                    <div id="mountainResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.river" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_river" type="text" class="form-control" />
                    </div>
                    <div id="riverResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <br /> <br />
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.lake" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_lake" type="text" class="form-control" />
                    </div>
                    <div id="lakeResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.animal" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_animal" type="text" class="form-control" />
                    </div>
                    <div id="animalResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-3">
                        <spring:message code="play.label.plant" />
                    </div>
                    <br />
                    <div class="col-md-9">
                        <input id="input_plant" type="text" class="form-control" />
                    </div>
                    <div id="plantResult-${userDetails.username}" class="col-md-9">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="btn btn-primary btn-block" onclick="sendNum(stompClient)">
                            <spring:message code="play.button.submit" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>