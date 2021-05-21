
		const url = 'http://localhost:8080';
		let stompClient;
		let selectedUser;
		let newMessages = new Map();

		function connectToChat(userName ) {
			console.log("connecting to chat...")
			let socket = new SockJS(url + '/chat');
			console.log("소켓")
			stompClient = Stomp.over(socket);
			stompClient.connect({},
					function(frame) {console.log("connected to: " + frame);
					stompClient.subscribe("/topic/messages/" + userName,
					function(response) {
						let data = JSON.parse(response.body);
					if (
						selectedUser === data.fromLogin) {
						render(data.message,data.fromLogin);
					} else {
						newMessages.set(data.fromLogin,data.message);
						$('#userNameAppender_'+ data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
					}
				});
			});
		}

		function sendMsg(from, text) {
			console.log("sendMsg selecteduser: " + selectedUser);
			console.log("sendMsg from: " + from);
			console.log("sendMsg text: " + text);
			stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
				fromLogin : from,
				message : text
			}));
		}

		function connect() {
			console.log("connect try success");
			let userName = document.getElementById("chatUserId").value;
			$.get(url + "/registration/" + userName, function(response) {
				
				connectToChat(userName);
			}).fail(function(error) {
				connectToChat(userName);
				if (error.status === 400) {
					
				}
			})
		}
		
		function selectUser(userName) {
			console.log("selecting users: " + userName);
			selectedUser = userName;
			console.log("selectedUser users2: " + selectedUser);
			document.getElementById('chatOtherId').innerHTML = selectedUser;
			let isNew = document.getElementById("newMessage_" + userName) !== null;
			if (isNew) {
				render(newMessages.get(userName), userName);
			}
			
		}
		function selectFriendNo(selectFriendNo){
			console.log("selectFriendNo: " + selectFriendNo);
			
			var Fn = selectFriendNo;
			
			
			
		}
		
		connect();