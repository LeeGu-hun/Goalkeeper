package goal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.service.UserService;
import goal.vo.UserVO;

@RestController
public class MainController {
	
//	@RequestMapping("/home")
//	public ModelAndView openHome() {
//		ModelAndView mv = new ModelAndView("/view/main_header");
//		return mv;
//	}
	
	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public ModelAndView openHome(@ModelAttribute UserVO vo,HttpSession session) {
		boolean result = userService.checkLogin(vo);
		if(result == true) {
			
		} else {
			
		}
		return null;
	}
}
