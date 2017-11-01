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
        <div class="score-header">
            <div class="score-header-row">                
                <div  class="character">
                    <div class="character-name"><spring:message code="play.label.score" /></div>
                    <div class="character-value" id="scorePerRound">${scorePerRound}</div>
                </div>                
                <div class="character">
                    <div class="character-name"><spring:message code="play.label.time" /></div>
                    <div class="character-value" id="roundTimer"></div>
                </div>
                <div  class="character">
                    <div class="character-name"><spring:message code="play.label.letter" /></div>
                    <div class="character-value" id="character">${character}</div>
                </div>
            </div>
        </div>
        <div class="playContainer">
            <input id="input_username" type="hidden" value="${userDetails.username}"/>            
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
            <div class="playTable"></div>
            <div class="results">
                <div class="results-tabs">
                    <div class="result-tab"><div class="result-tab-button" tabindex="1" onClick="getResults(0)">1</div></div>
                    <div class="result-tab"><div class="result-tab-button" tabindex="2" onClick="getResults(1)">2</div></div>
                    <div class="result-tab"><div class="result-tab-button" tabindex="3" onClick="getResults(2)">3</div></div>
                    <div class="result-tab"><div class="result-tab-button" tabindex="4" onClick="getResults(3)">4</div></div>
                    <div class="result-tab"><div class="result-tab-button" tabindex="5" onClick="getResults(4)">5</div></div>
                    <div class="result-tab"><div class="result-tab-button" tabindex="6" onClick="getResults(5)">6</div></div>
                </div>
                <div
                <div class="results-row">                    
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/capitol.png" />">
                        </div>
                        <div class="result-col-value state-result"><spring:message code="play.label.state" /></div>
                    </div>
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/building.png" />">
                        </div>
                        <div class="result-col-value city-result"><spring:message code="play.label.city" /></div>
                    </div>
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/mountain.png" />">
                        </div>
                        <div class="result-col-value mountain-result"><spring:message code="play.label.mountain" /></div>
                    </div>
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/river.png" />">
                        </div>
                        <div class="result-col-value river-result"><spring:message code="play.label.river" /></div>
                    </div>
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/waves.png" />">
                        </div>
                        <div class="result-col-value lake-result"><spring:message code="play.label.lake" /></div>
                    </div>                    
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/animal-paw-print.png" />">
                        </div>
                        <div class="result-col-value animal-result"><spring:message code="play.label.animal" /></div>
                    </div>
                    <hr class="result-row-line"/>
                    <div class="results-rowdiv">
                        <div style="width: 20%">
                            <img
                                class="result-col-icon"
                                src="<c:url value="/resources/images/icons/leaf.png" />">
                        </div>
                        <div class="result-col-value plant-result"><spring:message code="play.label.plant" /></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-inline play-form">
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <!--<div class="label-icons state-label-icon"></div>-->
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/capitol.png" />">
                        <div class="label-name"><spring:message code="play.label.state" /></div>
                    </label>
                    <input id="input_state" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/building.png" />">
                        <div class="label-name"><spring:message code="play.label.city" /></div>
                    </label>
                    <input id="input_city" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/mountain.png" />">
                        <div class="label-name"><spring:message code="play.label.mountain" /></div>
                    </label>
                    <input id="input_mountain" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/river.png" />">
                        <div class="label-name"><spring:message code="play.label.river" /></div>
                    </label>
                    <input id="input_river" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/waves.png" />">
                        <div class="label-name"><spring:message code="play.label.lake" /></div>
                    </label>
                    <input id="input_lake" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/animal-paw-print.png" />">
                        <div class="label-name"><spring:message code="play.label.animal" /></div>
                    </label>
                    <input id="input_animal" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group">
                <div class="col-md-12 col-xs-12 answer-input">
                    <label for="input_state" class="form-control -fill">
                        <img
                            class="label-icons state-label-icon label-icons-intent"
                            src="<c:url value="/resources/images/icons/leaf.png" />">
                        <div class="label-name"><spring:message code="play.label.plant" /></div>
                    </label>
                    <input id="input_plant" type="text" class="form-control input-place" />
                </div>
            </div>
            <div class="form-group anwers-group play-submit">
                <div class="col-md-12 col-xs-12 play-submit-row">
                    <div class="btn btn-primary btn-block play-submit-button" onclick="sendNum(stompClient)">
                        <spring:message code="play.button.submit" />
                    </div>
                </div>
            </div>
        </div>
        <div class="answersSend"><spring:message code="play.label.answersSend" /></div>
    </div>
</body>