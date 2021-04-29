package goal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.service.UserService;

import goal.vo.UserVO;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public ModelAndView openHome(@ModelAttribute UserVO user,HttpSession session) {
		ModelAndView mv = new ModelAndView("/view/home/logout_home");
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView openLogin() {
		ModelAndView mv = new ModelAndView("/view/home/user_login");
		return mv;
	}
	
	@PostMapping("/login")
	public ModelAndView checkLogin(UserVO vo) {
		String check = userService.checkLogin(vo);
		
		if(check == null) {
			ModelAndView mv = new ModelAndView("/view/home/logout_home");
			mv.addObject("guest", "guest");
			return mv;
			
		} else {
			ModelAndView mv = new ModelAndView("/view/home/login_home");
			mv.addObject("user", vo);
			return mv;
		}
	}
	@GetMapping("/register")
	public ModelAndView openRegister() {
		ModelAndView mv = new ModelAndView("/view/home/user_register");
		return mv;
	}
	@PostMapping("/register")
	public ModelAndView insertUser(UserVO vo, Model model) {
		String check = userService.checkId(vo);
		ModelAndView mv = new ModelAndView();
		if(check == null) {
			userService.insertUser(vo);
			mv.setViewName("/view/home/user_login");
			return mv;
		} else {
			model.addAttribute("msg","중복된 아이디 입니다.");
			mv.setViewName("/view/home/user_register");
			return mv;
			 
		}
	}
	@GetMapping("/denied")
    public String deniedView() {
        return "view/error/denied";
    }
	
	@GetMapping("/mySearchFriends")
	   public ModelAndView UserList(@ModelAttribute UserVO vo,HttpSession session) {
	      ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
	      
	      List<UserVO> list = friendService.allUserList(vo);
	      mv.addObject("list", list);
	      return mv;
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
