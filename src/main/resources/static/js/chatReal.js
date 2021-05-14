var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $(".simplebar-content-wrapper").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/chat', function (chat) {
        	showChat(JSON.parse(chat.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $(".chatUserId").val()}));
}

function sendChat() {
	stompClient.send("/app/chat", {}, JSON.stringify({'name': $(".chatUserId").val(), 'message': $(".chat-widget-message-text").val()}));
	document.getElementById("chatMessage").value = "";
}

function showGreeting(message) {
    $(".simplebar-content-wrapper").append("<tr><td>" + message + "</td></tr>");
}
function showChat(chat) {
    $(".simplebar-content-wrapper").append("<tr><td>" + chat.name + " : " + chat.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( ".chat-widget-message" ).click(function() { console.log("작동됨");connect(); alert("클릭 작동");});
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( ".chat-widget-message-text" ).keypress(function(event){
    	console.log(event.keyCode);
    	if(event.keyCode == 13){
     		sendChat();
     	 }
     });
});