package goal.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.GroupDataService;
import goal.service.GroupFileService;
import goal.service.GroupService;
import goal.upload.Upload;
import goal.vo.GroupDataVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupListVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupDataService groupDataService;
	
	@Autowired
	private GroupFileService groupFileService;

	@GetMapping("/myGroup")
	public ModelAndView openGroup(@ModelAttribute GroupVO group) {
		UserVO user = new UserVO();
		
		ModelAndView mv = addList("view/group/group_myList", user);
		
		return mv;
	}
	@PostMapping("/myGroup")
	public ModelAndView removeGroup(@RequestParam(value="gno") int gno) {
		groupService.removeGroup(gno);
		ModelAndView mv = new ModelAndView("view/group/group_myList");
		UserVO user = new UserVO();
		List<GroupVO> groupList = getGroupList(user);
		List<GroupFileVO> groupFileList = groupFileService.selectFileName();
		groupFileService.removeGroupFile(gno);
		mv.addObject("fileList", groupFileList);
		mv.addObject("List", groupList);
		return mv;
	}
	@GetMapping("/searchGroup")
	public ModelAndView openSearchGroup() {
		ModelAndView mv = new ModelAndView("view/group/group_searchList");
		List<GroupVO> allList = groupService.allList();
		List<GroupVO> studyList = groupService.selectSearchList("공부");
		List<GroupVO> exerciseList = groupService.selectSearchList("운동");
		List<GroupVO> picnicList = groupService.selectSearchList("야외활동");
		List<GroupVO> musicList = groupService.selectSearchList("음악");
		mv.addObject("allList", allList);
		mv.addObject("studyList", studyList);
		mv.addObject("exerciseList", exerciseList);
		mv.addObject("picnicList", picnicList);
		mv.addObject("musicList", musicList);
		return mv;
	}
	
	@GetMapping("/openManage")
	public ModelAndView openManage() {
		ModelAndView mv = new ModelAndView("view/group/group_manage");
		UserVO user = new UserVO();
		List<GroupVO> groupList = getGroupList(user);
		mv.addObject("List", groupList);
		return mv;
	}
	
	@GetMapping("/group_create")
	public ModelAndView openGroupCreate() {
		ModelAndView mv = new ModelAndView("view/group/group_create");
		return mv;
	}
	
	@PostMapping("/group_create")
	public ModelAndView createGroup(GroupVO group, GroupDataVO groupData, MultipartHttpServletRequest multi) throws Exception {	
		Upload upload = new Upload();
		UserVO user = new UserVO();
		GroupFileVO groupFile = new GroupFileVO();
		user.setUno(2);
		group.setUno(user.getUno());
		groupService.createGroup(group);
		GroupVO recentGroup = groupService.recentGroup();
		
		groupFile = upload.requestSingleUpload(multi);
		groupFile.setGno(recentGroup.getGno());
		groupFileService.insertGroupFile(groupFile);
		
		groupData.setGno(recentGroup.getGno());
		groupData.setUno(user.getUno());
		groupDataService.insertData(groupData);
		
		ModelAndView mv = addList("view/group/group_myList", user);
		return mv;
	}
	
	
	private List<GroupVO> getGroupList(UserVO user){
		user.setUno(2);
		List<GroupVO> groupList = groupService.selectGroupList(user);
		return groupList;
	}
	
	private ModelAndView addList(String url, UserVO user) {
		ModelAndView mv = new ModelAndView(url);
		List<GroupVO> groupList = getGroupList(user);
		List<GroupFileVO> groupFileList = groupFileService.selectFileName();
		mv.addObject("fileList", groupFileList);
		mv.addObject("List", groupList);
		return mv;
	}
}