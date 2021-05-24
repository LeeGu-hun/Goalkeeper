package goal.controller;


import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.common.UserCommonDownload;
import goal.service.BoardService;
import goal.service.ChatService;
import goal.service.CommonService;
import goal.service.FriendApplyService;
import goal.service.FriendService;
import goal.service.GroupService;
import goal.service.MyGoalService;
import goal.service.SearchFriendService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.BoardVO;
import goal.vo.ChatVO;
import goal.vo.FriendVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MyPageController {
	
	@Autowired
	public FriendService friendService;
	
	@Autowired
	public SearchFriendService searchFriendService;
	
	@Autowired
	public UserService userService;

	@Autowired
	public CommonService commonService;

	@Autowired
	public MyGoalService myGoalService;
	
	@Autowired
	public UserFileService userFileService;
	
	@Autowired
	public UserBackFileService userBackFileService;
	
	@Autowired
	public FriendApplyService friendApplyService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private GroupService groupService;
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
    private String userId;
    
    @RequestMapping(value = {"/myPage/{userId}"})
	public ModelAndView openHome(HttpServletRequest request, BoardVO vo, FriendVO friend, @PathVariable String userId) {
		UserVO user = commonService.getLoginUser(request);
		UserVO myPageUser = userService.myPageUserInfo(userId);
		friend.setUno(myPageUser.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		mv = commonService.checkLoginUser(request, mv);
		int countFriend = friendService.countFriends(myPageUser.getUno());
		List<FriendVO> list = friendService.getFriendsList(friend);
		userFileService.selectFile(myPageUser.getUno());
		List<BoardVO> boardList = boardService.getMyPageBoardList(userId);
		mv.addObject("BoList", boardList);
		this.userId = userId;
		List<ChatVO> friendlist = chatService.findFriendList(myPageUser);
	      
		if(vo != null) {
			mv.addObject("friendlist", friendlist);
			mv.addObject("vo", myPageUser);
			mv.addObject("user", user);
			mv.addObject("uno", myPageUser.getUno());
			mv.addObject("profile", myPageUser.getUserFileCheck());
			mv.addObject("background", myPageUser.getUserBackCheck());
			mv.addObject("count", countFriend);
			mv.addObject("list", list);
		} else {
			mv.setViewName("view/error/denied");
		}
		
		return mv;	
	}
    
    @GetMapping("myPage/groups/{userId}")
    public ModelAndView openGroup(@ModelAttribute GroupVO group, HttpServletRequest request, @PathVariable String userId) {
    	UserVO user = commonService.getLoginUser(request);
    	UserVO myPageUser = userService.myPageUserInfo(userId);
    	ModelAndView mv = new ModelAndView("view/myPage/myPage_group");
    	mv = commonService.checkLoginUser(request, mv);
    	
    	List<GroupVO> groupList = groupService.getAllList();
    	
    	mv.addObject("vo", myPageUser);
    	mv.addObject("list", groupList);
    	
		mv.addObject("uno", myPageUser.getUno());
		mv.addObject("userId", myPageUser.getUserId());
		mv.addObject("userBirthdate", myPageUser.getUserBirthdate());
		mv.addObject("userFileCheck", myPageUser.getUserFileCheck());
		mv.addObject("userBackCheck", myPageUser.getUserBackCheck());
		mv.addObject("profile", myPageUser.getUserFileCheck());
		mv.addObject("background", myPageUser.getUserBackCheck());
		
    	if(user != null) {		
    		mv.addObject("user", user);
    	} else {
    		mv.addObject("user", null);
    	}
    	return mv;
    }
	
	
	@PostMapping("/myPage/insert.do")
	public String insertBoard(@RequestParam String fileCheck,
			BoardVO board, HttpServletRequest request, @RequestPart("files") List<MultipartFile> files)
			throws Exception {
		log.info("df");
		UserVO user = new UserVO();
		user = getLoginUser(request);

		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		
		if(!board.getBo_cate().equals("group")) {
			board.setBo_group("noGroup");
		}
		commonService.fileCheck(board, fileCheck, files);
		
		return "redirect:/myPage/" + userId;
	
	}
	@PostMapping("/myPage/modify.do")
	public String modifyBoard(@RequestParam String placeCheck, BoardVO board, HttpServletRequest request) {
		UserVO user = commonService.getLoginUser(request);
		
		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		board.setBno(board.getBno());
		boardService.updateBoard(board);
		if(placeCheck.equals("board")) {
		return "redirect:/myPage/" + userId;
		} else {  
			int gno = groupService.findGnobyName(board.getBo_group());
			return "redirect:/group_detail/" + gno;
		}
	}
	
	@PostMapping("/myPage/delete.do")
	public String deleteBoard(BoardVO board, HttpServletRequest request) {
		UserVO user = commonService.getLoginUser(request);
		
		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		board.setBno(board.getBno());
		boardService.deleteBoard(board);
		
		return "redirect:/myPage/" + userId;
	}
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		return user;
	}
}
