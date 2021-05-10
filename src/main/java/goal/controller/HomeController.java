package goal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 
	@GetMapping("/user")
	   public ModelAndView openHome(HttpServletRequest request) {
	      ModelAndView mv = new ModelAndView("view/home/Old_user_home");
	      mv = commonService.checkLoginUser(request, mv);
	      HttpSession session = request.getSession(true);
	      UserVO user = (UserVO) session.getAttribute("user");
	      
	      List<BoardVO> boardList = boardService.getBoardList();
	      List<ReplyVO> ReplyList = replyService.getMainReply(); 
	      
	      mv.addObject("userInfo", user);
	      mv.addObject("List", boardList);
	      mv.addObject("reply", ReplyList);
	      return mv;
	 }

	@PostMapping("/insertReply")
		public String insertReply(ReplyVO vo) {
			replyService.insertReply(vo);
			return "redirect:/home";
	}
	
	@GetMapping("/home")
	public ModelAndView openLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/home/user_home");
		referer = request.getHeader("REFERER");
		return mv;
	}
	
	@PostMapping("/home")
	public String checkLogin(HttpServletRequest request,UserVO vo, Model model) {
		UserVO user = userService.getUser(vo); //UserVO반환하는 서비스 추가해야함
		HttpSession session = request.getSession(true);
		if(user != null) {
			session.setAttribute("user", user);
			if(referer == null) {
				return "redirect:/user";
			}
			return "redirect:" + referer;
		} 
		return "redirect:/home";
	   }
	
	
	@PostMapping("/register")
	public String insertUser(UserVO vo, Model model) {
		String idCheck = userService.checkId(vo.getUserId());
		if(idCheck == null) {
			userService.insertUser(vo);
			return "redirect:/home";
		} else {
			model.addAttribute("msg","중복된 아이디 입니다.");
			return "redirect:/register";
			 
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
