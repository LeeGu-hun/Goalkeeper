package goal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping("/view/main_header")
	public String openHome() {
		return "/view/main_header";
	}
}
