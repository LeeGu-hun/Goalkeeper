package goal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.service.CommonService;
import goal.service.FriendService;
import goal.service.MyGoalService;
import goal.service.SearchFriendService;
import goal.service.UserService;
import goal.vo.BoardVO;
import goal.vo.FriendVO;
import goal.vo.InputMyDataVO;
import goal.vo.MyGoalVO;
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
	public MyGoalService myGoalService;
	
	@Autowired
	public CommonService commonService;
	@GetMapping("/myPage")
	public ModelAndView openHome(HttpServletRequest request, BoardVO vo, FriendVO friend) {
		UserVO user = new UserVO();
		user = getLoginUser(request);
		friend.setUno(user.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		int countFriend = friendService.countFriends(user.getUno());
		List<FriendVO> list = friendService.getFriendsList(friend);
		
		if(vo != null) {
			mv.addObject("vo", user);
			mv.addObject("uno", user.getUno());
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
		
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject("list", list);
		mv.addObject("userId", vo.getUserId());
		mv.addObject("userBirthdate", vo.getUserBirthdate());
		mv.addObject("count", countFriend);
		
		return mv;
	}
	
	@PostMapping("/myFriends")
	public ModelAndView searchFriend(@RequestParam(value="friends_search") String word, UserVO vo, HttpServletRequest request) {
		vo = getLoginUser(request);
		int countFriend = friendService.countFriends(vo.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uno", vo.getUno());
		map.put("word", word);
		
		List<FriendVO> searchFriendList = friendService.findMyFriend(map);
		mv.addObject("list", searchFriendList);
		mv.addObject("count", countFriend);
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView userList(HttpServletRequest request, UserVO vo) {
		vo = getLoginUser(request);
		UserVO user = new UserVO();
		user.setUno(vo.getUno());
		int countFriend = friendService.countFriends(vo.getUno());
		
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		List<UserVO> list = searchFriendService.allUserList(user);
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
		mv.addObject("list", searchResult);
		mv.addObject("count", countFriend);
		
		return mv;
	}
	
	@PostMapping("/mySearchFriends/add")
	public ModelAndView addFriend(HttpServletRequest request, UserVO vo, @RequestParam int friendNo,
			@RequestParam String friendName, @RequestParam String friendNumber,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date friendBirthdate) {
		vo = getLoginUser(request);
		FriendVO friend = new FriendVO();
		friend.setUno(vo.getUno());
		friend.setFriendNo(friendNo);
		friend.setFriendName(friendName);
		friend.setFriendNumber(friendNumber);
		friend.setFriendBirthdate(friendBirthdate);
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
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    UserVO user = (UserVO) session.getAttribute("user");
	    return user;
	}
	
}
