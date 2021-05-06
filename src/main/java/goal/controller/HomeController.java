package goal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private CommonService commonService;
	
	private String referer = null;
	 
	@GetMapping("/home")
	   public ModelAndView openHome(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView("view/home/user_home");
	      mv = commonService.checkLoginUser(request, mv);
	      List<BoardVO> boardList = boardService.getBoardList();
	      List<ReplyVO> ReplyList = replyService.getMainReply(); 
	      mv.addObject("List", boardList);
	      mv.addObject("reply", ReplyList);
	      return mv;
	   }
	
	@GetMapping("/login")
	public ModelAndView openLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/home/user_login");
		referer = request.getHeader("REFERER");
		return mv;
	}
	
	@PostMapping("/login")
	public String checkLogin(HttpServletRequest request,UserVO vo, Model model) {
		UserVO user = userService.getUser(vo); //UserVO반환하는 서비스 추가해야함
		HttpSession session = request.getSession(true);
		if(user != null) {
			session.setAttribute("user", user);
			if(referer == null) {
				return "redirect:/home";
			}
			return "redirect:" + referer;
		} 
		return "redirect:/login";
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
			mv.setViewName("view/home/user_home");
			return mv;
		} else {
			model.addAttribute("msg","중복된 아이디 입니다.");
			mv.setViewName("view/home/user_register");
			return mv;
			 
		}
	}
	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		referer = request.getHeader("REFERER");
		return "redirect:"+referer;
	}
	
	@GetMapping("/denied")
    public String deniedView() {
        return "view/error/denied";
    }
}
