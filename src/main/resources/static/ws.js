var client = null;
var his = [];
var hisIndex = 0;

function connect() {
	var sock = new SockJS("http://localhost:8080/mud-server");
    client = Stomp.over(sock);
    client.connect({}, function (frame) {
    	client.subscribe('/user/history', function (response) {
            showGreeting(response.body);
        });
    	
    	client.subscribe('/user/history/status', function (response) {
            setStatus(JSON.parse(response.body));
        });
    });
}

function sendName() {
	var value = $("#name").val();
	client.send("/app/command", {}, value);
	if (his[his.length - 1] != value) {
		his.push(value);
	}
	hisIndex = his.length - 1;
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
    $("#send").click(function() { sendName(); });
    $("#name").on('keydown', function() {
    	if (event.keyCode == 38) { //상
    		$("#name").val(his[hisIndex--]);
    		if (hisIndex < 0) {
    			hisIndex = 0;
    		}
    	} else if (event.keyCode == 40) { //하
    		$("#name").val(his[hisIndex++]);
    		if (hisIndex > his.length - 1) {
    			hisIndex = his.length - 1;
    		}
    	}
    });
    
});