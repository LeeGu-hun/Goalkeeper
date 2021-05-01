package goal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
//		mv.addObject("list", list);
		return mv;	
	}
	
	@GetMapping("/myFriends")
	public ModelAndView getFriendsList(HttpSession session) {
		vo.setUno(2);
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		
		List<FriendVO> list = friendService.getFriendsList(vo);
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
	public String addFriend(@ModelAttribute FriendVO friend, RedirectAttributes rttr) {
		vo.setUno(2);
		friendService.addFriend(friend);
		rttr.addFlashAttribute("add", friend.getUno());
		return "redirect:/view/mySearchFriends";
	}
	
	@PostMapping("myFriends")
	public ModelAndView deleteFriend(@RequestParam(value="uno") int uno) {
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		friendService.remove(uno);
		List<FriendVO> friendList = getFriendList(vo);
		mv.addObject("friendList", friendList);
		return mv;
	}
	
	private List<FriendVO> getFriendList(UserVO vo){
		List<FriendVO> friendList = friendService.getFriendsList(vo);
		return friendList;
	}
}
