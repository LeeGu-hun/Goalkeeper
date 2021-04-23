package goal.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import goal.vo.GroupVO;

@Controller
public class GroupController {
	@RequestMapping("/group")
	public ModelAndView openGroup(@ModelAttribute GroupVO group) {
		ModelAndView mv = new ModelAndView("/view/group/group_list");
		
		ArrayList<GroupVO> groupList = new ArrayList<GroupVO>();
//		groupList.add(new GroupVO[] {1, "")
		mv.addObject("list", groupList);
		return mv;
	}
}
