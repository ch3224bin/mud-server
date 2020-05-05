var client = null;

function connect() {
	var sock = new SockJS("http://localhost:8080/mud-server");
    client = Stomp.over(sock);
    client.connect({}, function (frame) {
    	client.subscribe('/history', function (response) {
            showGreeting(response.body); // JSON.parse(response.body).content
        });
    });
}

function sendName() {
	client.send("/app/command", {}, $("#name").val()); // JSON.stringify({'name': $("#name").val()})
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
	connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});