package goal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goal.service.FriendService;
import goal.service.SearchFriendService;
import goal.vo.FriendVO;
import goal.vo.UserVO;

@RestController
public class MyPageController {
	
	@Autowired
	public FriendService friendService;
	
	@Autowired
	public SearchFriendService searchFriendService;
	
	@GetMapping("/myPage")
	public ModelAndView openHome(@ModelAttribute UserVO vo,HttpSession session) {
		ModelAndView mv = new ModelAndView("view/myPage/myPage_home");
		return mv;	
	}
	
	@GetMapping("/myFriends")
	public ModelAndView openFriendsList(@ModelAttribute UserVO vo,HttpSession session) {
		ModelAndView mv = new ModelAndView("view/myPage/myPage_friends");
		return mv;
	}
	
	@GetMapping("/mySearchFriends")
	public ModelAndView UserList(@ModelAttribute UserVO vo,HttpSession session) {
		ModelAndView mv = new ModelAndView("view/myPage/myPage_search_friends");
      
		List<UserVO> list = searchFriendService.allUserList(vo);
		mv.addObject("list", list);
		return mv;
	}
	
	@PostMapping("/mySearchFriends")
	public String addFriend(FriendVO vo, RedirectAttributes rttr) {
		friendService.addFriend(vo);
		rttr.addFlashAttribute("add", vo.getUno());
		return "redirect:/view/mySearchFriends";
	}
	
}
