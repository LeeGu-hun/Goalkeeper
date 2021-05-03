package goal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import goal.service.BoardService;

import goal.service.UserService;
import goal.vo.BoardVO;

import goal.vo.ReplyVO;
import goal.vo.UserVO;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;

	/*
	 * @Autowired private UserDetailService userDetailService;
	 */
	@GetMapping("/home")
	   public ModelAndView openHome(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView("/view/home/login_home");
	      HttpSession session = request.getSession(true);
	      UserVO user = (UserVO) session.getAttribute("user");
	      if(user!=null) {
	         mv.addObject("login", "success");
	         mv.addObject("user", user);
	      } else {
	         mv.addObject("login", "fail");
	      }
	      List<BoardVO> boardList = boardService.getBoardList();   
	      mv.addObject("List", boardList);
	      return mv;
	   }
	
	@GetMapping("/login")
	public ModelAndView openLogin() {
		ModelAndView mv = new ModelAndView("/view/home/user_login");
		return mv;
	}
	
	@PostMapping("/login")
	public String checkLogin(HttpServletRequest request,UserVO vo) {
		UserVO user = userService.getUser(vo); //UserVO반환하는 서비스 추가해야함
		if(user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
		} 
		return "redirect:/home";
	   }
	@GetMapping("/register") 
	public ModelAndView openRegister() {
		ModelAndView mv = new ModelAndView("/view/home/user_register");
		return mv;
	}
	@PostMapping("/register")
	public ModelAndView insertUser(UserVO vo, Model model) {
		String idCheck = userService.checkId(vo.getUserId());
		ModelAndView mv = new ModelAndView();
		if(idCheck == null) {
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
	
	
}
