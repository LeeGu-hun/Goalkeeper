package goal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.common.UserCommonDownload;
import goal.service.ChatService;
import goal.service.CommonService;
import goal.service.FriendApplyService;
import goal.service.FriendService;
import goal.service.MyGoalService;
import goal.service.SearchFriendService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.ChatVO;
import goal.vo.FriendApplyVO;
import goal.vo.FriendVO;
import goal.vo.MyGoalVO;
import goal.vo.UserBackVO;
import goal.vo.UserFileVO;
import goal.vo.UserVO;

@RestController
public class FriendsController {
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
	
	private CommonDownload commonDownload = new CommonDownload();
	private UserCommonDownload userCommon = new UserCommonDownload();
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
    private String userId;
    private String referer = null;
    
    @GetMapping("/myFriends/{userId}")
	public ModelAndView getMyPageFriendsList(HttpServletRequest request,@PathVariable String userId, UserVO vo, FriendVO friend) {
		this.userId = userId;
		
		UserVO user = commonService.getLoginUser(request);
		UserVO myPageUser = userService.myPageUserInfo(userId);
		friend.setUno(myPageUser.getUno());
		referer = request.getHeader("REFERER");
		int countFriend = friendService.countFriends(myPageUser.getUno());
		int applyCount = friendApplyService.applyCount(myPageUser.getUno());
		int receiveCount = friendApplyService.receiveCount(myPageUser.getUno());
		int countPost = friendService.countPost(myPageUser.getUserId());
		
		ModelAndView mv = new ModelAndView("view/myPage/InMyPage_friends");
		mv = commonService.checkLoginUser(request, mv);
		
		List<FriendVO> list = friendService.getFriendsList(friend);
			
		mv.addObject("vo", myPageUser);
		mv.addObject("uno", myPageUser.getUno());
		mv.addObject("userId", myPageUser.getUserId());
		mv.addObject("userBirthdate", myPageUser.getUserBirthdate());
		mv.addObject("userFileCheck", myPageUser.getUserFileCheck());
		
		mv.addObject("profile", myPageUser.getUserFileCheck());
		mv.addObject("background", myPageUser.getUserBackCheck());
		
		if(user != null) {
			List<ChatVO> friendlist = chatService.findFriendList(user);
			mv.addObject("user", user);
			mv.addObject("friendlist", friendlist);
			mv.addObject("loginUserId", user.getUserId());
			mv.addObject("loginUserBirthdate", user.getUserBirthdate());
			mv.addObject("loginUserProfile", user.getUserFileCheck());
			mv.addObject("loginUserBackground", user.getUserBackCheck());
		}
		mv.addObject("countPost",countPost);
		mv.addObject("list", list);
		mv.addObject("count", countFriend);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		
		return mv;
	}
	
	@PostMapping("/myFriends/{userId}")
	public ModelAndView searchFriend(@RequestParam(value="friends_search") String word, UserVO vo, HttpServletRequest request,@PathVariable String userId) {
		UserVO user = commonService.getLoginUser(request);
		UserVO myPageUser = userService.myPageUserInfo(userId);
	
		int countFriend = friendService.countFriends(myPageUser.getUno());
		int countPost = friendService.countPost(myPageUser.getUserId());
		int applyCount = friendApplyService.applyCount(myPageUser.getUno());
		int receiveCount = friendApplyService.receiveCount(myPageUser.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/InMyPage_friends");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", myPageUser.getUno());
		map.put("word", word);
		
		List<FriendVO> searchFriendList = friendService.findMyFriend(map);
		mv.addObject("vo", myPageUser);
		mv.addObject("uno", myPageUser.getUno());
		mv.addObject("userId", myPageUser.getUserId());
		mv.addObject("userBirthdate", myPageUser.getUserBirthdate());
		mv.addObject("userFileCheck", myPageUser.getUserFileCheck());
		
		mv.addObject("profile", myPageUser.getUserFileCheck());
		mv.addObject("background", myPageUser.getUserBackCheck());
		
		if(user != null) {
			mv.addObject("user", user);
			mv.addObject("loginUserId", user.getUserId());
			mv.addObject("loginUserBirthdate", user.getUserBirthdate());
			mv.addObject("loginUserProfile", user.getUserFileCheck());
			mv.addObject("loginUserBackground", user.getUserBackCheck());
		}
		mv.addObject("countPost",countPost);
		mv.addObject("list", searchFriendList);
		mv.addObject("count", countFriend);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView userList(HttpServletRequest request) {
		UserVO vo = commonService.getLoginUser(request);
		int countFriend = friendService.countFriends(vo.getUno());
		int applyCount = friendApplyService.applyCount(vo.getUno());
		int receiveCount = friendApplyService.receiveCount(vo.getUno());
		int countPost = friendService.countPost(vo.getUserId());
		
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		mv = commonService.checkLoginUser(request, mv);
		
		List<UserVO> list = searchFriendService.allUserList(vo);
		mv.addObject("uno", vo.getUno());
		mv.addObject("userId", vo.getUserId());
		mv.addObject("userBirthdate", vo.getUserBirthdate());
		mv.addObject("userFileCheck", vo.getUserFileCheck());
		mv.addObject("userBackCheck", vo.getUserBackCheck());
		mv.addObject("list", list);
		mv.addObject("count", countFriend);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		mv.addObject("countPost",countPost);
		
		return mv;
	}

	@PostMapping("/mySearchFriends")
	public ModelAndView searchUser(@RequestParam(value="friends_search") String word, HttpServletRequest request, UserVO vo) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		mv = commonService.checkLoginUser(request, mv);
		
		int countFriend = friendService.countFriends(vo.getUno());
		int countPost = friendService.countPost(vo.getUserId());
		int applyCount = friendApplyService.applyCount(vo.getUno());
		int receiveCount = friendApplyService.receiveCount(vo.getUno());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", vo.getUno());
		map.put("word", word);
		
		List<UserVO> searchResult = searchFriendService.searchUser(map);
		mv.addObject("user", vo);
		mv.addObject("uno", vo.getUno());
		mv.addObject("userId", vo.getUserId());
		mv.addObject("userBirthdate", vo.getUserBirthdate());
		mv.addObject("userFileCheck", vo.getUserFileCheck());
		mv.addObject("userBackCheck", vo.getUserBackCheck());
		mv.addObject("list", searchResult);
		mv.addObject("count", countFriend);
		mv.addObject("countPost",countPost);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		
		return mv;
	}
	
	@PostMapping("/mySearchFriends/apply")
	public ModelAndView apply(HttpServletRequest request, UserVO vo, int receiveUno, String applyId, String receiveId,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date applyBirthdate, @DateTimeFormat(pattern="yyyy-MM-dd") Date receiveBirthdate,
			String applyFileCheck, String receiveFileCheck, String applyBackCheck, String receiveBackCheck) {
		vo = commonService.getLoginUser(request);
		FriendApplyVO apply = new FriendApplyVO();
		FriendVO friend = new FriendVO();
		friend.setUno(vo.getUno());
		apply.setApplyUno(vo.getUno());
		apply.setReceiveUno(receiveUno);
		apply.setApplyId(applyId);
		apply.setReceiveId(receiveId);
		apply.setApplyBirthdate(applyBirthdate);
		apply.setReceiveBirthdate(receiveBirthdate);
		if(applyFileCheck.equals("Y")) apply.setApplyFileCheck(applyFileCheck);
		else apply.setApplyFileCheck("N");
		if(receiveFileCheck.equals("Y")) apply.setReceiveFileCheck(receiveFileCheck);
		else apply.setReceiveFileCheck("N");
		if(applyBackCheck.equals("Y")) apply.setApplyBackCheck(applyBackCheck);
		else apply.setApplyBackCheck("N");
		if(receiveBackCheck.equals("Y")) apply.setReceiveBackCheck(receiveBackCheck);
		else apply.setReceiveBackCheck("N");
		
		friendApplyService.apply(apply);
		
		ModelAndView mv = new ModelAndView("redirect:/mySearchFriends");
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject(list);
		return mv;
	}
	@PostMapping("/*/mySearchFriends/apply2")
	public ModelAndView applyOthersFriends(HttpServletRequest request, UserVO vo, int receiveUno, String applyId, String receiveId,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date applyBirthdate, @DateTimeFormat(pattern="yyyy-MM-dd") Date receiveBirthdate,
			String applyFileCheck, String receiveFileCheck, String applyBackCheck, String receiveBackCheck) {
		vo = commonService.getLoginUser(request);
		FriendApplyVO apply = new FriendApplyVO();
		FriendVO friend = new FriendVO();
		friend.setUno(vo.getUno());
		apply.setApplyUno(vo.getUno());
		apply.setReceiveUno(receiveUno);
		apply.setApplyId(applyId);
		apply.setReceiveId(receiveId);
		apply.setApplyBirthdate(applyBirthdate);
		apply.setReceiveBirthdate(receiveBirthdate);
		if(applyFileCheck.equals("Y")) apply.setApplyFileCheck(applyFileCheck);
		else apply.setApplyFileCheck("N");
		if(receiveFileCheck.equals("Y")) apply.setReceiveFileCheck(receiveFileCheck);
		else apply.setReceiveFileCheck("N");
		if(applyBackCheck.equals("Y")) apply.setApplyBackCheck(applyBackCheck);
		else apply.setApplyBackCheck("N");
		if(receiveBackCheck.equals("Y")) apply.setReceiveBackCheck(receiveBackCheck);
		else apply.setReceiveBackCheck("N");
		
		friendApplyService.apply(apply);
		
		ModelAndView mv = new ModelAndView("redirect:/myFriends/"+userId);
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject(list);
		return mv;
	}
	
	@PostMapping("/*/myFriends/delete")
	public ModelAndView deleteFriend(@RequestParam int friendNo, HttpServletRequest request, UserVO vo) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("redirect:/myFriends/" + userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", vo.getUno());
		map.put("friendNo", friendNo);
		
		friendService.remove(map);
		
		FriendVO friend = new FriendVO();
		List<FriendVO> friendList = friendService.getFriendsList(friend);
		mv.addObject("friendList", friendList);
		return mv;
	}
	
	@GetMapping("/myGoal")
	public ModelAndView goalList(HttpServletRequest request, UserVO vo) {
		UserVO user = new UserVO();
		user = commonService.getLoginUser(request);
		int countFriend = friendService.countFriends(user.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_data");
		mv = commonService.checkLoginUser(request, mv);
		
		mv.addObject("vo", user);
		mv.addObject("uno", user.getUno());
		mv.addObject("count", countFriend);

		return mv;	
	}
	
	@PostMapping("/makeGoal")
	public ModelAndView makePrivatGoal(HttpServletRequest request, UserVO vo, MyGoalVO goal) {
		vo = commonService.getLoginUser(request);
		goal.setUno(vo.getUno());
		myGoalService.createGoal(goal);
		ModelAndView mv = new ModelAndView("redirect:/myGoal");
		
		return mv;
	}
	
	@GetMapping("/applyList")
	public ModelAndView applyList(HttpServletRequest request, UserVO user) {
		user = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_applyList");
		mv = commonService.checkLoginUser(request, mv);
		
		List<FriendApplyVO> applyList = friendApplyService.applyList(user.getUno()); 
		int countFriend = friendService.countFriends(user.getUno());
		int applyCount = friendApplyService.applyCount(user.getUno());
		int receiveCount = friendApplyService.receiveCount(user.getUno());
		int countPost = friendService.countPost(user.getUserId());
		
		mv.addObject("list", applyList);
		mv.addObject("uno", user.getUno());
		mv.addObject("userId", user.getUserId());
		mv.addObject("userBirthdate", user.getUserBirthdate());
		mv.addObject("userFileCheck", user.getUserFileCheck());
		mv.addObject("userBackCheck", user.getUserBackCheck());
		mv.addObject("count", countFriend);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		mv.addObject("countPost",countPost);
		
		return mv;
	}
	
	@PostMapping("/applyList/cancel")
	public ModelAndView applyCancel(HttpServletRequest request, UserVO vo, int receiveUno) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("redirect:/applyList");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyUno", vo.getUno());
		map.put("receiveUno", receiveUno);
		
		friendApplyService.applyCancel(map);
		List<FriendApplyVO> applyList = friendApplyService.applyList(vo.getUno());
		
		mv.addObject("list", applyList);
		return mv;
	}
	
	@GetMapping("/receiveList")
	public ModelAndView receiveList(HttpServletRequest request, UserVO vo) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_receiveList");
		mv = commonService.checkLoginUser(request, mv);
		
		List<FriendApplyVO> receiveList = friendApplyService.receiveList(vo.getUno());
		int countFriend = friendService.countFriends(vo.getUno());
		int applyCount = friendApplyService.applyCount(vo.getUno());
		int receiveCount = friendApplyService.receiveCount(vo.getUno());
		int countPost = friendService.countPost(vo.getUserId());
		
		mv.addObject("list", receiveList);
		mv.addObject("uno", vo.getUno());
		mv.addObject("userId", vo.getUserId());
		mv.addObject("userBirthdate", vo.getUserBirthdate());
		mv.addObject("userFileCheck", vo.getUserFileCheck());
		mv.addObject("userBackCheck", vo.getUserBackCheck());
		mv.addObject("count", countFriend);
		mv.addObject("applyCount", applyCount);
		mv.addObject("receiveCount", receiveCount);
		mv.addObject("countPost",countPost);
		
		return mv;
	}
	
	@PostMapping("/receiveList/accept")
	public ModelAndView applyAccept(HttpServletRequest request, UserVO vo, int applyUno, String receiveId, String applyId,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date applyBirthdate, @DateTimeFormat(pattern="yyyy-MM-dd") Date receiveBirthdate,
			String applyFileCheck, String receiveFileCheck, String applyBackCheck, String receiveBackCheck) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("redirect:/receiveList");
		FriendApplyVO apply = new FriendApplyVO();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyUno", applyUno);
		map.put("uno", vo.getUno());
		
		apply.setApplyUno(applyUno);
		apply.setReceiveUno(vo.getUno());
		apply.setApplyId(applyId);
		apply.setReceiveId(receiveId);
		apply.setApplyBirthdate(applyBirthdate);
		apply.setReceiveBirthdate(receiveBirthdate);
		if(applyFileCheck.equals("Y")) apply.setApplyFileCheck(applyFileCheck);
		else apply.setApplyFileCheck("N");
		if(receiveFileCheck.equals("Y")) apply.setReceiveFileCheck(receiveFileCheck);
		else apply.setReceiveFileCheck("N");
		if(applyBackCheck.equals("Y")) apply.setApplyBackCheck(applyBackCheck);
		else apply.setApplyBackCheck("N");
		if(receiveBackCheck.equals("Y")) apply.setReceiveBackCheck(receiveBackCheck);
		else apply.setReceiveBackCheck("N");
		
		friendApplyService.acceptFriend(apply);
		friendApplyService.deleteFriend(map);
		
		List<FriendApplyVO> list = friendApplyService.applyList(vo.getUno());
		mv.addObject(list);
		return mv;
	}
	
	@PostMapping("/receiveList/reject")
	public ModelAndView rejectAccept(HttpServletRequest request, UserVO vo, int applyUno) {
		vo = commonService.getLoginUser(request);
		ModelAndView mv = new ModelAndView("redirect:/receiveList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyUno", applyUno);
		map.put("uno", vo.getUno());
		
		friendApplyService.deleteFriend(map);
		
		return mv;
	}
	
	@RequestMapping(value="/user/profile/{uno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImagebyUno(@PathVariable int uno) throws IOException{
	    UserFileVO userFile = userFileService.selectFile(uno);
	    entity = userCommon.getImageEntity(entity, mediaUtils, in, userFile.getUserFileName(), userFile.getUserFileId(), userFile.getUserFilePath());
	    return entity;
	}
	
	@RequestMapping(value="/user/profileId/{replyWriter}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImagebyId(@PathVariable String replyWriter) throws IOException{
		UserFileVO userFile = userFileService.selectFilebyId(replyWriter);
		entity = commonDownload.getImageEntity(entity, mediaUtils, in, userFile.getUserFileName(), userFile.getUserFileId(), userFile.getUserFilePath());
		return entity;
	}
	
	@RequestMapping(value="/user/background/{uno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayBackground(@PathVariable int uno) throws IOException{
	    UserBackVO backFile = userBackFileService.selectBackFile(uno);
	    entity = userCommon.getImageEntity(entity, mediaUtils, in, backFile.getBackName(), backFile.getBackId(), backFile.getBackPath());
	    return entity;
	}
	
}
