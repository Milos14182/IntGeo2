var stompClient = createStompClient();
var answersAlreadySend = false;
var answersSend = false;
var collectAll = false;
var count = 60;
var counter = null;
var isEnded = false;
var roundResults = [];
//1000 will  run it every 1 second
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('calculationDiv').style.visibility = connected ? 'visible'
            : 'hidden';
    document.getElementById('calResponse').innerHTML = '';
}
function connect(stompClient) {
    if ($(document).width() <= 1000) {
        document.getElementById("myCarousel").style.display = 'none';
    }
    var pathnames = window.location.pathname.split('/');
    disableInputs();
    stompClient.connect({}, function () {
        counter = setInterval(loginPlayerTimer, 1000);
        waitPlayers(stompClient);
        disableInputs();
        stompClient.subscribe('/topic/play/initialization/' + pathnames[pathnames.length - 1], function (
                calResult) {
            console.log(calResult.body);
            if (calResult.body != "0") {
                count = calResult.body;
            } else {
                startRound();
            }
        });
    });
}
function startRound() {
    var pathnames = window.location.pathname.split('/');
    roundSync(stompClient);
    clearInterval(counter);
    if (!isEnded) {
        enableInputs();
        counter = setInterval(roundTimer, 1000); //1000 will  run it every 1 second
        roundTimer();
        stompClient.subscribe('/topic/play/roundSync/' + pathnames[pathnames.length - 1], function (
                data) {
            if (data.body !== "0") {
                answersSend = false;
            }
            resetTimer(data.body);
        });
        stompClient.subscribe('/topic/play/answers/' + pathnames[pathnames.length - 1], function (
                data) {
            answersSend = false;
            roundSync(stompClient);
            showAllResults(data);
        });
    }
}
function disconnect(stompClient) {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
    if ($(document).width() <= 1000) {
        document.getElementById("myCarousel").style.display = 'inherit';
    }
}
function sendNum(stompClient) {
    var pathnames = window.location.pathname.split('/');
    if (!answersSend) {
        stompClient.send("/play/answers/" + pathnames[pathnames.length - 1], {}, getInputValuesJSON());
    }
    answersSend = true;
    //deploy
//    stompClient.send("/play/answers/" + pathnames[pathnames.length - 1], {}, getInputValuesJSON());
}
function checkLockedGame(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/play/checkLocked/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/play/checkLocked/" + pathnames[pathnames.length - 1], {});
}

function waitPlayers(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/play/initialization/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/play/initialization/" + pathnames[pathnames.length - 1], {});
}
function roundSync(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/play/roundSync/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/play/roundSync/" + pathnames[pathnames.length - 1], {});
}
function createStompClient() {
    var socket = new SockJS('/greetingEndpoint');
    return Stomp.over(socket);
}

function getInputValuesJSON() {
    var character = $('#character').html();
    var score = $('#scorePerRound').html();
    var username = $('#input_username').val();
    var state = $('#input_state').val().toUpperCase();
    var city = $('#input_city').val().toUpperCase();
    var mountain = $('#input_mountain').val().toUpperCase();
    var lake = $('#input_lake').val().toUpperCase();
    var river = $('#input_river').val().toUpperCase();
    var plant = $('#input_plant').val().toUpperCase();
    var animal = $('#input_animal').val().toUpperCase();
    var url = window.location.href;
    var gameId = url.split('/')[4];
    return JSON.stringify({
        'username': username,
        'character': character,
        'score': score,
        'state': state,
        'city': city,
        'mountain': mountain,
        'lake': lake,
        'river': river,
        'plant': plant,
        'animal': animal,
        'gameId': gameId,
        'collectAll': collectAll
    });
}

function showAllResults(calResult) {
    var answers = JSON.parse(calResult.body);
    var username = $('#input_username').val();
    if (answers["submitted"] == true && answers[username] == true) {
        answersAlreadySend = true;
        $(".answersSend").show();
        disableInputs();
    } else {
        for (var i in answers) {
            answersAlreadySend = false;
            $(".answersSend").hide();
            setIUserAnswers(answers[i], i);
            if (answers[i].username == username) {
                setMyAnswers(answers[i]);
            }
        }
        $(".user-score").css("display", "flex");
        resetTimer();
        setTimeout(function () {
            $(".user-score").css("display", "none");
        }, 10000);
    }
}
function setMyAnswers(answers) {
    $('#character').html(answers.character);
    $('#scorePerRound').html(answers.score);

    $('#input_state').val('');
    $('#input_city').val('');
    $('#input_mountain').val('');
    $('#input_lake').val('');
    $('#input_river').val('');
    $('#input_plant').val('');
    $('#input_animal').val('');
    if (!answers.isEnded) {
        enableInputs();
    } else {
        isEnded = true;
        alert("Game ended.");
        window.location = "/game";
    }
}
function setIUserAnswers(answers, int) {
    answers.state = answers.state != null ? answers.state.toUpperCase() : null;
    answers.city = answers.city != null ? answers.city.toUpperCase() : null;
    answers.mountain = answers.mountain != null ? answers.mountain.toUpperCase() : null;
    answers.lake = answers.lake != null ? answers.lake.toUpperCase() : null;
    answers.plant = answers.plant != null ? answers.plant.toUpperCase() : null;
    answers.animal = answers.animal != null ? answers.animal.toUpperCase() : null;
    answers.river = answers.river != null ? answers.river.toUpperCase() : null;
    roundResults[int] = answers;
}

function roundTimer() {
    $('#roundTimer').html(count);
    count = count - 1;
    if (count <= 0 && !answersSend)
    {
        clearInterval(counter);
        if (!answersAlreadySend) {
            collectAll = true;
            sendNum(stompClient);
        }
        collectAll = false;
        return true;
    }
    return false;
}

function loginPlayerTimer() {
    $('#roundTimer').html(count);
    count = count - 1;
    if (count <= 0)
    {
        clearInterval(counter);
        if (!answersAlreadySend) {
            collectAll = true;
            startRound();
        }
        collectAll = false;
        return true;
    }
    return false;
}
function resetTimer(time) {
    if (time <= 0) {
        time = 60;
    }
    count = time;
    clearInterval(counter);
    if (!isEnded) {
        counter = setInterval(roundTimer, 1000);
    }
}
function disableInputs() {
    $('#character').css("visibility", "hidden");
    $('#input_state').attr("disabled", "disabled");
    $('#input_city').attr("disabled", "disabled");
    $('#input_mountain').attr("disabled", "disabled");
    $('#input_lake').attr("disabled", "disabled");
    $('#input_river').attr("disabled", "disabled");
    $('#input_plant').attr("disabled", "disabled");
    $('#input_animal').attr("disabled", "disabled");
}
function enableInputs() {
    $("#character").css("visibility", "visible");
    $('#input_state').removeAttr("disabled");
    $('#input_city').removeAttr("disabled");
    $('#input_mountain').removeAttr("disabled");
    $('#input_lake').removeAttr("disabled");
    $('#input_river').removeAttr("disabled");
    $('#input_plant').removeAttr("disabled");
    $('#input_animal').removeAttr("disabled");
}
function getResults(i) {
    $('.state-result').html(roundResults[i] != null ? roundResults[i].state : "");
    $('.city-result').html(roundResults[i] != null ? roundResults[i].city : "");
    $('.mountain-result').html(roundResults[i] != null ? roundResults[i].mountain : "");
    $('.lake-result').html(roundResults[i] != null ? roundResults[i].lake : "");
    $('.plant-result').html(roundResults[i] != null ? roundResults[i].plant : "");
    $('.animal-result').html(roundResults[i] != null ? roundResults[i].animal : "");
    $('.river-result').html(roundResults[i] != null ? roundResults[i].river : "");
}
