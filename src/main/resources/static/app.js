var stompClient = null;
var players = [];

function newGame() {
    var name = $("#name").val();
    $.ajax({
        type: "POST",
        contentType: "text/plain",
        url: "/game/create",
        data: name,
        success: function (gameID) {
            $("#new-game-id").text(gameID);
            connect(gameID);
        }
    });
}

function getPlayers(gameID) {
    $.get("/game/" + gameID + "/players", function(currentPlayers) {
        console.log(currentPlayers);
        players = currentPlayers;
    });
}

function connect(gameID) {
    var socket = new SockJS("/coup-websocket");
    stompClient = Stomp.over(socket);
    // Connect, continue with callback when connected
    stompClient.connect({}, function(frame) {
        console.log("Connected: " + frame);
        // Subscribe to player channel and update player list when notified
        stompClient.subscribe("/topic/players/" + gameID, function(player) {
            if(players.length < 1) getPlayers(gameID);
            players.push(player.body);
            console.log(players);
        });
        join(gameID);
    });
}

function join(gameID) {
    stompClient.send("/coup/game/" + gameID + "/join", {}, $("#name").val());
}

$(function() {
    $("form").on("submit", function (e) { e.preventDefault(); });
    $("#new-game").click(function() { newGame(); });
    $("#join-game").click(function() { connect($("#join-game-id").val()); });
});