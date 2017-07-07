function newGame() {
    var name = $("#name").val();
    $.ajax({
        type: "POST",
        contentType: "text/plain",
        url: "/game/create",
        data: name,
        success: [function (data) {
            $("#games").append("<tr><td>" + data + "</td><td>" + name + "</td></tr>");
        }]
    });
}

$(function() {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#new-game").click(function() { newGame(); });
});