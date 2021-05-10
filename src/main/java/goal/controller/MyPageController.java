package goal.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goal.service.FriendService;
import goal.service.SearchFriendService;
import goal.service.UserService;
import goal.vo.BoardVO;
import goal.vo.FriendVO;
import goal.vo.UserVO;

@RestController
public class MyPageController {
	
	@Autowired
	public FriendService friendService;
	
	@Autowired
	public SearchFriendService searchFriendService;
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/myPage")
	public ModelAndView openHome(HttpServletRequest request, BoardVO vo) {
		UserVO user = new UserVO();
		user = getLoginUser(request);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		if(vo != null) {
			mv.addObject("vo", user);
			mv.addObject("uno", user.getUno());
		} else {
			mv.setViewName("view/error/denied");
		}
		
		return mv;	
	}
	
	@GetMapping("/myFriends")
	public ModelAndView getFriendsList(HttpServletRequest request, UserVO vo, FriendVO friend) {
		vo = getLoginUser(request);
		friend.setUno(vo.getUno());
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject("list", list);
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView userList(HttpServletRequest request, UserVO vo) {
		vo = getLoginUser(request);
		UserVO user = new UserVO();
		user.setUno(vo.getUno());
		
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		List<UserVO> list = searchFriendService.allUserList(user);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@PostMapping("/mySearchFriends")
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
	
	@PostMapping("myFriends")
	public ModelAndView deleteFriend(@RequestParam(value="fno") int fno) {
		ModelAndView mv = new ModelAndView("redirect:/myFriends");
		friendService.remove(fno);
		
		FriendVO friend = new FriendVO();
		List<FriendVO> friendList = friendService.getFriendsList(friend);
		mv.addObject("friendList", friendList);
		return mv;
	}
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    UserVO user = (UserVO) session.getAttribute("user");
	    return user;
	}
	
}
