package goal.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import goal.service.CommonService;
import goal.service.UserService;
import goal.vo.UserVO;

@Controller
public class DropBoxController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/profileInfo")
	public ModelAndView openProfileInfo(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-info");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@PostMapping("/modifyMyInfo")
	public String modifyMyInfo(UserVO vo) {
		userService.modify(vo);
		return "redirect:/profileInfo";
	}
	
	@GetMapping("/profileModify")
	public ModelAndView openProfileModify(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-account-password");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@GetMapping("/notice")
	public ModelAndView openNotice(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-notifications");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@GetMapping("/massage")
	public ModelAndView openMassage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-messages");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@GetMapping("/generalSetting")
	public ModelAndView openGeneralSetting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-account-settings");
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	
}
