package goal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.ChatService;
import goal.service.CommonService;
import goal.service.GroupService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.upload.BoardUpload;
import goal.util.MediaUtils;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.FriendVO;
import goal.vo.GroupVO;
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
	private GroupService groupService;
	@Autowired
	private BoardFileService boardFileService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private BoardUpload boardUpload;
	@Autowired
	private ChatService chatService;

	private String referer = null;
	
	private int bno = 0;
	private int count = 0;

	@RequestMapping(value={"/home", "/"})
    public ModelAndView openNewsFeed(HttpServletRequest request) {
       ModelAndView mv = new ModelAndView("view/home/newsFeed");      
       mv = getHome(mv, request);
       HttpSession session = request.getSession(true);
       
       
        UserVO user = commonService.getLoginUser(request);
        if(user!=null) {
           List<FriendVO> friendlist = chatService.findFriendList(user);
           UserVO user1 = (UserVO) session.getAttribute("user");
           mv.addObject("user", user1);
           mv.addObject("friendlist", friendlist);          
        }
        
       return mv;
    }
 
 
 @PostMapping("/memberIdChk")
 @ResponseBody
 public String memberIdChkPOST(String memberId) throws Exception{
    
    int result = userService.idCheck(memberId);

    if(result != 0) {
       return "fail";   // 중복 아이디가 존재   
    } else {
       return "success";   // 중복 아이디 x
    }
 }


	@PostMapping("/count_file")
	public String getbno(@RequestParam int bno, RedirectAttributes rttr, HttpServletRequest request) {
		int filecnt = boardFileService.countFile(bno);
		rttr.addFlashAttribute("filecnt",filecnt);
		rttr.addFlashAttribute("count", count);
		count++;
	
		return "redirect:/home";
	}



	@GetMapping("/user")
	public ModelAndView openHome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/home/Old_userLogin");
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

	@GetMapping("/login")
	public ModelAndView openLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/home/userLogin");
		referer = request.getHeader("REFERER");
		return mv;
	}

	@PostMapping("/login")
	public String checkLogin(HttpServletRequest request, UserVO vo, Model model) {
		UserVO user = userService.getUser(vo); // UserVO반환하는 서비스 추가해야함
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(120*60);
		if (user != null) {
			session.setAttribute("user", user);
			if(referer.contains("Login")) {
				return "redirect:/home";
			}
			return "redirect:" + referer;
		} else {
			return "/view/home/userLogin";
		}
	}

	@PostMapping("/register")
	public String insertUser(UserVO vo, Model model) {
		String idCheck = userService.checkId(vo.getUserId());
		if (idCheck == null) {
			userService.insertUser(vo);
			return "redirect:/home";
		} else {
			model.addAttribute("msg", "중복된 아이디 입니다.");
			return "redirect:/register";

		}
	}

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		referer = request.getHeader("REFERER");
		return "redirect:" + referer;
	}

	@GetMapping("/denied")
	public String deniedView() {
		return "view/error/denied";
	}
	
	private ModelAndView getHome(ModelAndView mv, HttpServletRequest request) {
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		List<BoardVO> boardList = boardService.getGroupBoardList("noGroup");
		mv.addObject("BoList", boardList);	
		if (user != null) {
			List<GroupVO> groupList = groupService.getGroupList(user);
			List<ReplyVO> ReplyList = replyService.getMainReply();
			
			mv.addObject("GrList", groupList);
			mv.addObject("reply", ReplyList);
		}
		return mv;
	}
	
}