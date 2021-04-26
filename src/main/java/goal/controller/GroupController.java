package goal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.GroupDataService;
import goal.service.GroupService;
import goal.vo.GroupDataVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupDataService groupDataService;
	
	@RequestMapping("/group")
	public ModelAndView openGroup(@ModelAttribute GroupVO group) {
		ModelAndView mv = new ModelAndView("view/group/group_list");
		
		List<GroupVO> groupList = groupService.selectGroupList();
		mv.addObject("List", groupList);
		
		return mv;
	}
	
	@GetMapping("/group_create")
	public ModelAndView openGroupCreate() {
		ModelAndView mv = new ModelAndView("view/group/group_create");
		return mv;
	}
	
	@PostMapping("/group_create")
	public String createGroup(GroupVO group, GroupDataVO groupData, MultipartHttpServletRequest multi) throws Exception {	
		UserVO user = new UserVO();
		user.setUno(2);
		group.setUno(user.getUno());
		groupService.createGroup(group, multi);
		groupData.setGno(group.getGno());
		groupData.setUno(user.getUno());
		groupDataService.insertData(groupData);

		return "redirect:/group";
	}
}
