package goal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import goal.domain.Chat;
import goal.domain.Greeting;
import goal.domain.HelloMessage;
import goal.service.CommonService;

@Controller

public class GreetingController {
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/chathome")
	public ModelAndView openNewsFeed(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/home/index");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(100); // delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Chat chat(Chat chat) throws Exception {
		return new Chat(chat.getName(), chat.getMessage());
	}
}