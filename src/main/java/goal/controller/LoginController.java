package goal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import goal.service.UserService;
import goal.vo.BoardVO;
import goal.vo.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	private String referer = null;
	
	@RequestMapping(value="/login_check", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserVO> loginCheck(UserVO user){
		UserVO checkUser = userService.getUser(user);
		UserVO failUser = new UserVO();
		if(checkUser == null || checkUser.equals("")) {
			failUser.setUserMail("fail");
		}
		return new ResponseEntity<UserVO>(failUser,HttpStatus.OK);
	}
	@GetMapping("/login")
	public ModelAndView openLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/home/userLogin");
		referer = request.getHeader("REFERER");
		return mv;
	}

	@PostMapping("/login")
	public String checkLogin(UserVO user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(120*60);
		UserVO loginUser = userService.getUser(user);
		session.setAttribute("user", loginUser);
		if(referer==null) {
			return "redirect:/home";
		} else if (referer.contains("login")) {
			return "redirect:/home";
		} else {
			return "redirect:" + referer;
		}
	}

	@PostMapping("/register")
	public String insertUser(UserVO vo, Model model) {
		userService.insertUser(vo);
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		referer = request.getHeader("REFERER");
		return "redirect:" + referer;
	}

}
