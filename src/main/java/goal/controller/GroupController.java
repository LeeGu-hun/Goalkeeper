	package goal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.CommonService;
import goal.service.GroupFileService;
import goal.service.GroupService;
import goal.upload.GroupUpload;
import goal.util.MediaUtils;
import goal.vo.GroupFileVO;
import goal.vo.GroupGoalVO;
import goal.vo.GroupUserNameVO;
import goal.vo.GroupUserVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupFileService groupFileService;
	
	@Autowired
	private CommonService commonService;
	
	private GroupUpload groupUpload = new GroupUpload();
	
	@GetMapping("/myGroup")
	public ModelAndView openGroup(@ModelAttribute GroupVO group, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/group_myList");
		UserVO user = new UserVO();
		user = getLoginUser(request);
		if(user != null) {
			group.setUno(user.getUno());
			List<GroupVO> groupList = getGroupList(user);
			mv.addObject("list", groupList);
			mv.addObject("user", user);
		} else {
			mv.addObject("user", null);
		}
		return mv;
	}
	@PostMapping("/myGroup")
	public ModelAndView removeGroup(@RequestParam(value="gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/group_myList");
		UserVO user = new UserVO();
		user = getLoginUser(request);
		groupService.removeGroup(gno);
		groupFileService.removeGroupFile(gno);
		List<GroupVO> groupList = getGroupList(user);
		mv.addObject("List", groupList);
		return mv;
	}//1
	@GetMapping("/searchGroup")
	public ModelAndView openSearchGroup(HttpServletRequest request) {
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
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	
	@GetMapping("/openManage")
	public ModelAndView openManage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/group_manage");
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		if(user!=null) {
			List<GroupVO> groupList = getGroupList(user);
			mv.addObject("List", groupList);
		}
		return mv;
	}
	
	@GetMapping("/group_create")
	public ModelAndView openGroupCreate() {
		ModelAndView mv = new ModelAndView("view/group/group_create");
		return mv;
	}
	
	@PostMapping("/group_create")
	public String createGroup(GroupVO group, GroupUserVO groups, GroupGoalVO groupGoal, MultipartHttpServletRequest multi, HttpServletRequest request) throws Exception {	
		GroupFileVO groupFile = new GroupFileVO();
		UserVO user = new UserVO();
		user = getLoginUser(request);
		if(user == null) {
			return "redirect:/login";
		}
		groups.setUno(user.getUno());
		group.setUno(user.getUno());
		groupService.createGroup(group, groups, groupGoal);
		groupFile = groupUpload.requestSingleUpload(multi);
		groupFileService.insertGroupFile(group, groupFile);
		
		return "redirect:/myGroup";
	}
	@GetMapping("/group_detail")
	public ModelAndView openDetail(@RequestParam int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_main");
		mv = commonService.checkLoginUser(request, mv);
		mv = getGroupUser(gno, mv);
		return mv;
	}
	@GetMapping("/group_member")
	public ModelAndView openMember(@RequestParam int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_member");
		mv = commonService.checkLoginUser(request, mv);
		mv = getGroupUser(gno, mv);
		return mv;
	}
	
	@GetMapping("/group_mainGoal")
	public ModelAndView openMainGoal(@RequestParam int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_mainGoal");
		mv = commonService.checkLoginUser(request, mv);
		mv = getGroupUser(gno, mv);
		List<GroupGoalVO> groupGoal = groupService.findGoalbyId(gno);
		mv.addObject("goal", groupGoal);
		
		return mv;
	}
	@PostMapping("/group_mainGoal")
	public ModelAndView addMainGoal(GroupGoalVO groupGoal, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/group_mainGoal");
		mv = commonService.checkLoginUser(request, mv);
		groupGoal.setGoal_type("A");
		groupService.insertGoal(groupGoal);
		mv.addObject("gno", groupGoal.getGno());
		return mv;
	}
	
	@GetMapping("/group_singleGoal{gno}")
	public ModelAndView openSingleGoal(@RequestParam int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_singleGoal");
		mv = commonService.checkLoginUser(request, mv);
		mv = getGroupUser(gno, mv);
		return mv;
	}
	
	@GetMapping("/group_home")
	public String openHome() {
		return "redirect:/group_detail";
	}
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage() throws IOException{
		MediaUtils mediaUtils = new MediaUtils();
	      InputStream in = null;
	      ResponseEntity<byte[]> entity = null;
	      List<GroupFileVO> groupFileList = groupFileService.selectFileName();
	      for(GroupFileVO groupFile : groupFileList) {
	         try {
	            String fileName = groupFile.getG_filename();
	            String g_fid = groupFile.getG_fid();
	            String uploadPath = groupFile.getG_filepath();
	            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	            MediaType mType = mediaUtils.getMediaType(formatName);
	            HttpHeaders headers = new HttpHeaders();
	            in = new FileInputStream(uploadPath + "\\" + g_fid + "_" + fileName);
	            headers.setContentType(mType);
	            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	         } catch (IOException e) {
	            e.printStackTrace();
	            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	         } finally {
	            in.close();
	         }
	      }
	      return entity;
	}

	private List<GroupVO> getGroupList(UserVO user){
		List<GroupVO> groupList = groupService.selectGroupList(user);
		return groupList;
	}
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    UserVO user = (UserVO) session.getAttribute("user");
	    return user;
	}
	private ModelAndView getGroupUser(int gno, ModelAndView mv) {
		GroupUserNameVO groupUser = new GroupUserNameVO();
		groupUser = groupService.fineUserbyGroup(gno);
		int result = groupService.countUserbyGroup(gno);
		mv.addObject("count", result);
		mv.addObject("groupUser", groupUser);
		mv.addObject("gno", gno);
		return mv;
	}
}