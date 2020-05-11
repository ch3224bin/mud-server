var client = null;

function connect() {
	var sock = new SockJS("http://localhost:8080/mud-server");
    client = Stomp.over(sock);
    client.connect({}, function (frame) {
    	client.subscribe('/user/history', function (response) {
            showGreeting(response.body); // JSON.parse(response.body).content
        });
    });
}

function sendName() {
	client.send("/app/command", {}, $("#name").val()); // JSON.stringify({'name': $("#name").val()})
	$("#name").val('');
}

function showGreeting(message) {
    $("#greetings").append(message);
    $('#greetings').scrollTop($('#greetings')[0].scrollHeight);
}

$(function () {
	connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});