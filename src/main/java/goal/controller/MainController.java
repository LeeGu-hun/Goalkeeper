package goal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.service.UserService;

import goal.vo.UserVO;

@RestController
public class MainController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public ModelAndView openHome(@ModelAttribute UserVO vo,HttpSession session) {
		ModelAndView mv = new ModelAndView("/view/main_home_login");
		return mv;
	}
	
	@PostMapping("/home")
	public ModelAndView loginCheck(@ModelAttribute UserVO vo,HttpSession session) {
		boolean result = userService.checkLogin(vo);
		ModelAndView mv = new ModelAndView("/view/main_home_login");
		UserVO user = userService.loginUser(vo);
		if(result == false) {
			mv.addObject("msg", "fail");
		} else {
			session.setAttribute("user", vo);
			mv.addObject("msg", "success");
		}
		return mv;
	}
	
}
