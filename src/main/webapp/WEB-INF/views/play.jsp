<%@ page language="java" contentType="text/html; charset=Windows-1250"
         pageEncoding="Windows-1250"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body onload="connect(stompClient)">
    <div class="body-content-play">
<!--        <audio autoplay loop>
            <source src="<c:url value="/resources/images/music.mp3" />">
        </audio>-->
        <div class="playContainer">
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
                    <div class="user-score score-${i}">
                        <div id="scorePerRound-${i}"></div>
                        <div>
                            <div id="stateResult-${i}"></div>
                            <div id="cityResult-${i}"></div>
                            <div id="mountainResult-${i}"></div>
                            <div id="riverResult-${i}"></div>
                            <div id="lakeResult-${i}"></div>
                            <div id="animalResult-${i}"></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="form-inline play-form">
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.state" /></label>
                    <input id="input_state" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.city" /></label>
                    <input id="input_city" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.mountain" /></label>
                    <input id="input_mountain" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.river" /></label>
                    <input id="input_river" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.lake" /></label>
                    <input id="input_lake" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.animal" /></label>
                    <input id="input_animal" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6 answer-input">
                    <label for="input_state" class="form-control -fill"><spring:message code="play.label.plant" /></label>
                    <input id="input_plant" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-6">
                    <div class="btn btn-primary btn-block" onclick="sendNum(stompClient)">
                        <spring:message code="play.button.submit" />
                    </div>
                </div>
            </div>
        </div>
        <div class="answersSend"><spring:message code="play.label.answersSend" /></div>
    </div>
</body>