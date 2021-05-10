package goal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Controller
public class DropBoxController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profileInfo")
	public ModelAndView openProfileInfo() {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-info");
		return mv;
	}
	@GetMapping("/profileModify")
	public ModelAndView openProfileModify() {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-account-password");
		return mv;
	}
	@GetMapping("/notice")
	public ModelAndView openNotice() {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-notifications");
		return mv;
	}
	@GetMapping("/massage")
	public ModelAndView openMassage() {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-messages");
		return mv;
	}
	@GetMapping("/generalSetting")
	public ModelAndView openGeneralSetting() {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-account-settings");
		return mv;
	}
	
}
