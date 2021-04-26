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
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public ModelAndView openHome(@ModelAttribute UserVO vo,HttpSession session) {
		ModelAndView mv = new ModelAndView("/view/home/logout_home");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView openLogin() {
		ModelAndView mv = new ModelAndView("/view/home/login_simple");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView checkLogin(UserVO vo) {
		int check = userService.checkLogin(vo);
		
		if(check == 1) {
			ModelAndView mv = new ModelAndView("/view/home/login_home");
			mv.addObject("user", vo);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/view/home/login_simple");
			mv.addObject("user", vo);
			return mv;
		}
	}
	@GetMapping("/register")
	public ModelAndView openRegister() {
		ModelAndView mv = new ModelAndView("/view/home/register_simple");
		return mv;
	}
	@PostMapping("/register")
	public String insertUser(UserVO vo) {
		userService.insertUser(vo);
		return "redirect:/view/home/login_simple";
	}
	
	/*
	 * @PostMapping("/home") public ModelAndView loginCheck(@ModelAttribute UserVO
	 * vo,HttpSession session) { boolean result = userService.checkLogin(vo);
	 * ModelAndView mv = new ModelAndView("/view/main_home_login"); UserVO user =
	 * userService.loginUser(vo); if(result == false) { mv.addObject("msg", "fail");
	 * } else { session.setAttribute("user", vo); mv.addObject("msg", "success"); }
	 * return mv; }
	 */
	
}
