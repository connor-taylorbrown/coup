function newGame() {
    var name = $("#name").val();
    $.post(
        "/game/create",
        name,
        function (data) {
            $("#games").append("<tr><td>" + data + "</td><td>" + name + "</td></tr>");
        }
    );
}

$(function() {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#new-game").click(function() { newGame(); });
});