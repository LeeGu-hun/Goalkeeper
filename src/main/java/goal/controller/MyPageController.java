package goal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goal.service.FriendService;
import goal.vo.FriendVO;
import goal.vo.UserVO;

@RestController
public class MyPageController {
	
	@Autowired
	public FriendService friendService;
	
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
	

	
	@PostMapping("/mySearchFriends")
	public String addFriend(FriendVO vo, RedirectAttributes rttr) {
		friendService.addFriend(vo);
		rttr.addFlashAttribute("add", vo.getFriend_uno());
		return "redirect:/view/mySearchFriends";
	}
	
}
