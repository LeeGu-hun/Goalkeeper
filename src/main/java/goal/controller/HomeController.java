package goal.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.GroupService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
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
	
	private String referer = null;
	
	@GetMapping("/home")
	   public ModelAndView openNewsFeed(HttpServletRequest request) {
		 ModelAndView mv = new ModelAndView("view/home/newsFeed");
		 mv = commonService.checkLoginUser(request, mv);
		 UserVO user = getLoginUser(request);
			if (user != null) {
				mv.addObject("user", user);
				List<GroupVO> groupList = groupService.selectGroupList(user);
				mv.addObject("List", groupList);
			} else {
				mv.setViewName("/view/home/userLogin");
			}
			return mv;
	}
	@PostMapping("/home/insert.do")
	public String insertBoard(
			BoardVO board, HttpServletRequest request, @RequestPart("files") List<MultipartFile> files)
			throws Exception {
		UserVO user = new UserVO();
		BoardFileVO boardFileVO = new BoardFileVO();
		user = getLoginUser(request);

		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
			
		boardService.insertBoard(board);
		
	
        for(MultipartFile file : files) {
        	String fileUrl = "C:/uploadfile";
        	String fileName = file.getOriginalFilename(); 
            String uuid = RandomStringUtils.randomAlphanumeric(32)+"."+"jpg";
            String filePath = fileUrl + "/" + uuid;
            File dest = new File(filePath);
            file.transferTo(dest);
            boardFileVO.setUuid(uuid);
            boardFileVO.setBno(board.getBno());
            boardFileVO.setFileName(fileName);
            boardFileVO.setFileUrl(filePath);
            boardFileService.fileInsert(boardFileVO);
	}
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
	public String checkLogin(HttpServletRequest request,UserVO vo, Model model) {
		UserVO user = userService.getUser(vo); //UserVO반환하는 서비스 추가해야함
		HttpSession session = request.getSession(true);
		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/home";
		}else {
			return "/view/home/userLogin";
		}
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
		return "/view/home/userLogin";
	}
	
	@GetMapping("/denied")
    public String deniedView() {
        return "view/error/denied";
    }
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		return user;
	}
}
