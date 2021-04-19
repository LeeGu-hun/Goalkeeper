package goal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.vo.UserVO;

@RestController
public class MainController {
	
//	@RequestMapping("/home")
//	public ModelAndView openHome() {
//		ModelAndView mv = new ModelAndView("/view/main_header");
//		return mv;
//	}

	@RequestMapping("/home")
	public ModelAndView openHome(@ModelAttribute UserVO vo,HttpSession session) {
		boolean result = false;
		if(result == false) {
			
		} else {
			
		}
		return null;
	}
}
