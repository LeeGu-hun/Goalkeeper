package goal.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private UserVO vo = new UserVO();
	
	@GetMapping("/myPage")
	public ModelAndView openHome(HttpSession session) {
		vo.setUno(2);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		mv.addObject("uno", vo.getUno());
		return mv;	
	}
	
	@GetMapping("/myFriends")
	public ModelAndView getFriendsList(FriendVO friend, HttpSession session) {
		friend.setUno(2);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject("list", list);
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView UserList(HttpSession session) {
		vo.setUno(2);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");

		List<UserVO> list = searchFriendService.allUserList(vo);
		mv.addObject("list", list);
		return mv;
	}
	
	@PostMapping("/mySearchFriends")
	public ModelAndView addFriend(@RequestParam(value="uno") int uno, @RequestParam(value="fno") int fno, 
			@RequestParam(value="friendName") int friendName, @RequestParam(value="friendNumber") String friendNumber, 
			@RequestParam(value="friendBirthdate") Date friendBirthdate) {
		vo.setUno(uno);
		FriendVO friend = new FriendVO();
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
		friendService.addFriend(friend);
		List<FriendVO> list = friendService.getFriendsList(friend);
		mv.addObject(list);
		return mv;
	}
	
	@PostMapping("myFriends")
	public ModelAndView deleteFriend(@RequestParam(value="fno") int fno) {
		vo.setUno(2);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		friendService.remove(fno);
		
		FriendVO friend = new FriendVO();
		List<FriendVO> friendList = friendService.getFriendsList(friend);
		mv.addObject("friendList", friendList);
		return mv;
	}
	
}
