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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.common.UserCommonDownload;
import goal.service.CommonService;
import goal.service.FriendService;
import goal.service.MyGoalService;
import goal.service.SearchFriendService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.BoardVO;
import goal.vo.FriendVO;
import goal.vo.MyGoalVO;
import goal.vo.UserBackVO;
import goal.vo.UserFileVO;
import goal.vo.UserVO;

@RestController
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
	
	private CommonDownload commonDownload = new CommonDownload();
	private UserCommonDownload userCommon = new UserCommonDownload();
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
    
    
	@GetMapping({"/myPage", "/myPage/{userId}"})
	public ModelAndView openHome(HttpServletRequest request, BoardVO vo, FriendVO friend) {
		UserVO user = new UserVO();
		user = getLoginUser(request);
		friend.setUno(user.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		mv = commonService.checkLoginUser(request, mv);
		int countFriend = friendService.countFriends(user.getUno());
		List<FriendVO> list = friendService.getFriendsList(friend);
		userFileService.selectFile(user.getUno());
		
		if(vo != null) {
			mv.addObject("vo", user);
			mv.addObject("uno", user.getUno());
			mv.addObject("profile", user.getUserFileCheck());
			mv.addObject("background", user.getUserBackCheck());
			mv.addObject("count", countFriend);
			mv.addObject("friendList", list);
		} else {
			mv.setViewName("view/error/denied");
		}
		
		return mv;	
	}
	
	@GetMapping("/myFriends")
	public ModelAndView getFriendsList(HttpServletRequest request, UserVO vo, FriendVO friend) {
		vo = getLoginUser(request);
		friend.setUno(vo.getUno());
		int countFriend = friendService.countFriends(vo.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		mv = commonService.checkLoginUser(request, mv);
		
		List<FriendVO> list = friendService.getFriendsList(friend);
		
		mv.addObject("uno", vo.getUno());
		mv.addObject("background", vo.getUserBackCheck());
		mv.addObject("profile", vo.getUserFileCheck());
		mv.addObject("list", list);
		mv.addObject("userId", vo.getUserId());
		mv.addObject("userBirthdate", vo.getUserBirthdate());
		mv.addObject("count", countFriend);
		
		return mv;
	}
	
	@PostMapping("/myFriends")
	public ModelAndView searchFriend(@RequestParam(value="friends_search") String word, UserVO vo, HttpServletRequest request) {
		vo = getLoginUser(request);
		UserVO user = new UserVO();
		int countFriend = friendService.countFriends(vo.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", vo.getUno());
		map.put("word", word);
		
		List<FriendVO> searchFriendList = friendService.findMyFriend(map);
		mv.addObject("list", searchFriendList);
		mv.addObject("user", user);
		mv.addObject("count", countFriend);
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView userList(HttpServletRequest request, UserVO vo) {
		vo = getLoginUser(request);
		int countFriend = friendService.countFriends(vo.getUno());
		
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
		
		return mv;
	}
	
	@PostMapping("/mySearchFriends")
	public ModelAndView searchUser(@RequestParam(value="friends_search") String word, UserVO vo, HttpServletRequest request) {
		vo = getLoginUser(request);
		int countFriend = friendService.countFriends(vo.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", vo.getUno());
		map.put("word", word);
		
		List<UserVO> searchResult = searchFriendService.searchUser(map);
		mv.addObject("uno", vo.getUno());
		mv.addObject("list", searchResult);
		mv.addObject("count", countFriend);
		
		return mv;
	}
	
	@PostMapping("/mySearchFriends/add")
	public ModelAndView addFriend(HttpServletRequest request, UserVO vo, int friendNo, String friendId, String friendNumber,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date friendBirthdate, String userFileCheck, String userBackCheck) {
		vo = getLoginUser(request);
		FriendVO friend = new FriendVO();
		friend.setUno(vo.getUno());
		friend.setFriendNo(friendNo);
		friend.setFriendId(friendId);
		friend.setFriendNumber(friendNumber);
		friend.setFriendBirthdate(friendBirthdate);		
		if(userFileCheck.equals("Y")) friend.setUserFileCheck(userFileCheck);
		else friend.setUserFileCheck("N");
		if(userBackCheck.equals("Y")) friend.setUserBackCheck(userBackCheck);
		else friend.setUserBackCheck("N");
		
		friendService.addFriend(friend);
		
		ModelAndView mv = new ModelAndView("redirect:/mySearchFriends");
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject(list);
		return mv;
	}
	
	@PostMapping("/myFriends/delete")
	public ModelAndView deleteFriend(@RequestParam(value="fno") int fno) {
		ModelAndView mv = new ModelAndView("redirect:/myFriends");
		friendService.remove(fno);
		
		FriendVO friend = new FriendVO();
		List<FriendVO> friendList = friendService.getFriendsList(friend);
		mv.addObject("friendList", friendList);
		return mv;
	}
	
	@GetMapping("/myGoal")
	public ModelAndView goalList(HttpServletRequest request, UserVO vo) {
		UserVO user = new UserVO();
		user = getLoginUser(request);
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
		vo = getLoginUser(request);
		goal.setUno(vo.getUno());
		myGoalService.createGoal(goal);
		ModelAndView mv = new ModelAndView("redirect:/myGoal");
		
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
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    UserVO user = (UserVO) session.getAttribute("user");
	    return user;
	}
	
}
