
		let $chatHistory;
		let $button;
		let $chatHistoryList;

		function init() {
			cacheDOM();
			bindEvents();
			
		}
		
		function bindEvents() {
			$button.on('click', addMessage.bind(this));
			$textarea.on('keyup', addMessageEnter.bind(this));
		}
		
		function cacheDOM() {
			$chatHistory = $('.chat-history');
			$button = $('#sendBtn');
			$chatHistoryList = $chatHistory.find('ul');
			$textarea = $('#chatSendTextBox');
		}

		function render(message, userName) {
			scrollToBottom();
			// responses
			var templateResponse = Handlebars.compile($(
					"#message-response-template").html());
			var contextResponse = {
				response : message,
				time : getCurrentTime(),
				userName : userName
			};

			setTimeout(function() {
				$chatHistoryList.append(templateResponse(contextResponse));
				scrollToBottom();
			}.bind(this), 1500);
		}

		function sendMessage(message) {
			let username = $('#chatUserId').val();
			console.log(username)
			console.log(message)
			sendMsg(username, message);
			scrollToBottom();
			if (message.trim() !== '') {
				var template = Handlebars.compile($("#message-template").html());
				var context = {
					messageOutput : message,
					time : getCurrentTime(),
					toUserName : selectedUser
				};
				$chatHistoryList.append(template(context));
				scrollToBottom();
				$('.chatSendTextBox').val('');
			}
		}

		function scrollToBottom() {
			$chatHistory.scrollTop($chatHistory[0].scrollHeight);
		}

		function getCurrentTime() {
			return new Date().toLocaleTimeString().replace(
					/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
		}
		function addMessage() {
			sendMessage($textarea.val());
		}
		function addMessageEnter(event) {
			// enter was pressed
			if (event.keyCode === 13) {
				addMessage();
			}
		}
		
	init();