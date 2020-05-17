var client = null;

function connect() {
	var sock = new SockJS("http://localhost:8080/mud-server");
    client = Stomp.over(sock);
    client.connect({}, function (frame) {
    	client.subscribe('/user/history', function (response) {
            showGreeting(response.body); // JSON.parse(response.body).content
        });
    	
    	client.subscribe('/user/history/status', function (response) {
            setStatus(JSON.parse(response.body));
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

function setStatus(status) {
	$('#status').text(`HP : ${status.hp}/ ${status.maxHp}`);
}

$(function () {
	connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
});