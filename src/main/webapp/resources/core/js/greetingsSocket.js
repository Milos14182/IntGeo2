var stompClient = createStompClient();
var answersAlreadySend = false;
var collectAll = false;
var count = 60;
var counter = null;
//1000 will  run it every 1 second
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('calculationDiv').style.visibility = connected ? 'visible'
            : 'hidden';
    document.getElementById('calResponse').innerHTML = '';
}
function connect(stompClient) {
    var pathnames = window.location.pathname.split('/');
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
    enableInputs();
    counter = setInterval(roundTimer, 1000); //1000 will  run it every 1 second
    roundTimer();
    stompClient.subscribe('/topic/play/roundSync/' + pathnames[pathnames.length - 1], function (
            data) {
        resetTimer(data.body);
    });
    stompClient.subscribe('/topic/play/answers/' + pathnames[pathnames.length - 1], function (
            data) {
        roundSync(stompClient);
        showAllResults(data);
    });
}
function disconnect(stompClient) {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}
function sendNum(stompClient) {
    var pathnames = window.location.pathname.split('/');

    stompClient.send("/intgeo/play/answers/" + pathnames[pathnames.length - 1], {}, getInputValuesJSON());
    //deploy
//    stompClient.send("/play/answers/" + pathnames[pathnames.length - 1], {}, getInputValuesJSON());
}
function checkLockedGame(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/intgeo/play/checkLocked/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/play/checkLocked/" + pathnames[pathnames.length - 1], {});
}

function waitPlayers(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/intgeo/play/initialization/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/play/initialization/" + pathnames[pathnames.length - 1], {});
}
function roundSync(stompClient) {
    var pathnames = window.location.pathname.split('/');
    stompClient.send("/intgeo/play/roundSync/" + pathnames[pathnames.length - 1], {});
//    stompClient.send("/intgeo/play/roundSync/" + pathnames[pathnames.length - 1], {});
}
function createStompClient() {
    //razvojni
    var socket = new SockJS('/intgeo/greetingEndpoint');
    //deploy
//    var socket = new SockJS('/greetingEndpoint');
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
    var gameId = url.split('/')[5];
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
    if (answers == false) {
        answersAlreadySend = true;
        $(".answersSend").show();
    } else {
        for (var i in answers) {
            answersAlreadySend = false;
            $(".answersSend").hide();
            if (answers[i].username == $('#input_username').val()) {
                setMyAnswers(answers[i]);
            } else {
                setIUserAnswers(answers[i], i)
            }
        }
        resetTimer();
    }
}
function setMyAnswers(answers) {
    $('#character').html(answers.character);
    $('#scorePerRound').html(answers.score);

    $('#stateResult-' + answers.username).html(answers.state.toUpperCase());
    $('#cityResult-' + answers.username).html(answers.city.toUpperCase());
    $('#mountainResult-' + answers.username).html(answers.mountain.toUpperCase());
    $('#lakeResult-' + answers.username).html(answers.lake.toUpperCase());
    $('#plantResult-' + answers.username).html(answers.plant.toUpperCase());
    $('#animalResult-' + answers.username).html(answers.animal.toUpperCase());
    $('#riverResult-' + answers.username).html(answers.river.toUpperCase());

    $('#input_state').val('');
    $('#input_city').val('');
    $('#input_mountain').val('');
    $('#input_lake').val('');
    $('#input_river').val('');
    $('#input_plant').val('');
    $('#input_animal').val('');
}
function setIUserAnswers(answers, int) {
    $('#stateResult-' + int).html(answers.state.toUpperCase());
    $('#cityResult-' + int).html(answers.city.toUpperCase());
    $('#mountainResult-' + int).html(answers.mountain.toUpperCase());
    $('#lakeResult-' + int).html(answers.lake.toUpperCase());
    $('#plantResult-' + int).html(answers.plant.toUpperCase());
    $('#animalResult-' + int).html(answers.animal.toUpperCase());
    $('#riverResult-' + int).html(answers.river.toUpperCase());
}

function roundTimer() {
    $('#roundTimer').html(count);
    count = count - 1;
    if (count <= 0)
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
    count = time;
    clearInterval(counter);
    counter = setInterval(roundTimer, 1000);
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
