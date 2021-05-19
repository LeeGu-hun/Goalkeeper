	package goal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goal.common.CommonDownload;
import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.GroupFileService;
import goal.service.GroupService;
import goal.service.ReactService;
import goal.upload.BoardUpload;
import goal.upload.GroupUpload;
import goal.util.MediaUtils;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.GroupBgiVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupGoalVO;
import goal.vo.GroupJoinVO;
import goal.vo.GroupUserVO;
import goal.vo.GroupVO;
import goal.vo.ReactVO;
import goal.vo.UserVO;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupFileService groupFileService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private ReactService reactService;
	
	private GroupUpload groupUpload = new GroupUpload();
	private CommonDownload commonDownload = new CommonDownload();
	private String referer;
	
	private int gno = 0;
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
	
	@GetMapping("/groups")
	public ModelAndView openGroup(@ModelAttribute GroupVO group, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/groups");
		UserVO user = new UserVO();
		user = commonService.getLoginUser(request);
		List<GroupVO> groupList = groupService.getAllList();
		mv.addObject("list", groupList);
		if(user != null) {		
			mv.addObject("user", user);
		} else {
			mv.addObject("user", null);
		}
		return mv;
	}
	@PostMapping("/groups")
	@ResponseBody
	public ModelAndView openSearchGroup(@RequestParam("groups_search") String word, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/groups");
		List<GroupVO> searchList = groupService.getSearchList(word);
		if(searchList == null) {
			mv.addObject("fail", "fail");
		} 
		mv.addObject("list", searchList);
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@PostMapping("/group_create")
	public String createGroup(@RequestParam String fileCheck, GroupVO group, GroupUserVO groups, MultipartHttpServletRequest multi, HttpServletRequest request) throws Exception {	
		GroupFileVO groupFile = new GroupFileVO();
		UserVO user = commonService.getLoginUser(request);
		groups.setUno(user.getUno());
		group.setUno(user.getUno());
		group.setBgi_check("N");
		if(fileCheck.equals("false")) {
			group.setFile_check("Y");
			groupService.createGroup(group, groups);
			groupFile = groupUpload.requestSingleUpload(multi, groupFile);
			groupFile.setGno(group.getGno());
			groupFileService.insertGroupFile(groupFile);
		} else {
			group.setFile_check("N");
			groupService.createGroup(group, groups);
		}
		return "redirect:/groups";
	}
	@PostMapping("/group_join")
	public String joinGroup(GroupJoinVO join, HttpServletRequest request) {
		String referer = request.getHeader("referer");
		UserVO user = commonService.getLoginUser(request);
		join.setUno(user.getUno());
		groupService.insertGroupJoin(join);
		return "redirect:" + referer;
	}
	
	@GetMapping("/group_detail/{gno}")
	public ModelAndView openDetail(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group-timeline");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.getGroup(gno);
		UserVO user = commonService.getLoginUser(request);
		this.gno = gno;
		List<BoardVO> boardList = boardService.getGroupBoardList(group.getG_name());
		List<BoardFileVO> groupFile = groupService.findFilebyGroup(group);
		if(user!=null) {
			GroupUserVO checkUser = new GroupUserVO();
			checkUser.setGno(gno);
			checkUser.setUno(user.getUno());
			int count = groupService.checkUserbyGroup(checkUser);
			if(count==1) {
				mv.addObject("result", "WriteSuccess");
			} 
		}	
		mv.addObject("fileList", groupFile);
		mv.addObject("group", group);
		mv.addObject("BoList", boardList);
		mv = getGroupUser(gno, mv);
		return mv;
	}
	@PostMapping("/group_detail")
	public ModelAndView writeGroup(@RequestParam String fileCheck, BoardVO board, GroupVO group, @RequestPart("files") List<MultipartFile> files, HttpServletRequest request) throws IllegalStateException, IOException {
		String gno = String.valueOf(group.getGno());
		ModelAndView mv = new ModelAndView("redirect:/group_detail/" + gno);
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		board.setBo_group(group.getG_name());
		board.setUserId(user.getUserId());
		board.setBo_cate("group");
		commonService.fileCheck(board, fileCheck, files);
		return mv;
	}
	@GetMapping("/group_join")
	public ModelAndView openJoin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_join");
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		if(user!=null) {
			GroupUserVO groupUser = new GroupUserVO();
			groupUser.setGno(this.gno);
			groupUser.setUno(user.getUno());
			int count = groupService.checkUserbyGroup(groupUser);
			if(count==1) {
				mv.addObject("result", "joinDenied");
			} else {
				mv.addObject("result", "joinSuccess");
			}
		}	
		GroupVO group = groupService.getGroup(this.gno);
		mv.addObject("group", group);
		return mv;
	}
	@PostMapping("/join_request")
	public String joinMember(GroupJoinVO groupJoin, HttpServletRequest request) {
		GroupUserVO groupUser = new GroupUserVO();
		groupUser.setGno(groupJoin.getGno());
		groupUser.setUno(groupJoin.getUno());
		groupUser.setG_role("ROLE_MEMBER");
		groupService.insertGroupUser(groupUser);
		groupService.removeGroupJoin(groupJoin);
		return "redirect:/group_mgJoin/"+groupJoin.getGno();
	}
	@GetMapping("/group_member/{gno}")
	public ModelAndView openMember(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_member");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.getGroup(gno);
		List<GroupUserVO> groupUser = groupService.findUserbyGroup(gno);
		int count = groupService.countUserbyGroup(gno);
		mv.addObject("group", group);
		mv.addObject("groupUser", groupUser);
		mv.addObject("userCount", count);
		return mv;
	}
	
	@GetMapping("/group_info/{gno}")
	public ModelAndView openInfo(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_info");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.getGroup(gno);
		mv.addObject("group", group);
		return mv;	
	}
	@GetMapping("/group_home")
	public String openHome() {
		return "redirect:/group_detail";
	}
	@GetMapping("/group_mgJoin/{gno}")
	public ModelAndView openManagementJoin(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_mgJoin");
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		referer = request.getHeader("REFERER");
		mv = getManageJoin(mv, user, gno);
		List<GroupJoinVO> groupJoin = groupService.getGroupJoin(gno);
		mv.addObject("joinList", groupJoin);
		return mv;
	}
	@GetMapping("/group_mgGoal/{gno}")
	public ModelAndView openManagementGoal(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_mgGoal");
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		List<GroupGoalVO> groupGoal = groupService.getGoalbyId(gno);
		mv.addObject("groupGoal", groupGoal);
		mv = getManageJoin(mv, user, gno);
		return mv;
	}
	@GetMapping("/group_mgSetting/{gno}")
	public ModelAndView openManageSetting(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_mgSetting");
		mv = commonService.checkLoginUser(request, mv);
		UserVO user = commonService.getLoginUser(request);
		mv = getManageJoin(mv, user, gno);
		return mv;
	}
	@PostMapping("/group_addGoal")
	public String addGoal(GroupGoalVO groupGoal) {
		groupService.insertGoal(groupGoal);
		return "redirect:/group_addGoal/" + groupGoal.getGno();
	}
	@PostMapping("/group_modifyProfile")
	public String modifyProfile(GroupFileVO groupFile, MultipartHttpServletRequest multi) {
		int count = groupFileService.checkFilebyGno(groupFile.getGno());
		if(count > 0) {
			groupFile = groupUpload.requestSingleUpload(multi, groupFile);
			groupFileService.updateGroupFile(groupFile);
		} else {
			groupFile = groupUpload.requestSingleUpload(multi, groupFile);
			groupFileService.insertGroupFile(groupFile);
		}
		return "redirect:/group_mgSetting/" + groupFile.getGno();
	}
	@PostMapping("/group_modifyBgi")
	public String modifyBgi(GroupBgiVO groupBgi, MultipartHttpServletRequest multi) {
		int count = groupFileService.checkBgibyGno(groupBgi.getGno());
		if(count > 0) {
			groupBgi = groupUpload.requestBackgroundUpload(multi, groupBgi);
			groupFileService.updateGroupBgi(groupBgi);	
		} else {
			groupBgi = groupUpload.requestBackgroundUpload(multi, groupBgi);
			groupService.updateBgiCheck("Y");
			groupFileService.insertGroupBgi(groupBgi);
		}
		return "redirect:/group_mgSetting/" + groupBgi.getGno();
	}
	@RequestMapping(value="/react", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BoardVO> react(@RequestBody ReactVO react) throws JSONException {
		return commonService.react(react);
	}
	@RequestMapping(value="/display/{gno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(@PathVariable int gno) throws IOException{
	    GroupFileVO groupFile = groupFileService.selectFile(gno);
	    entity = commonDownload.getImageEntity(entity, mediaUtils, in, groupFile.getG_filename(), groupFile.getG_fid(), groupFile.getG_filepath());	    	
	    return entity;
	}
	@RequestMapping(value="/background/{gno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> backgroundImage(@PathVariable int gno) throws IOException{
	    GroupBgiVO groupBgi = groupFileService.selectBgi(gno);
	    entity = commonDownload.getImageEntity(entity, mediaUtils, in, groupBgi.getFileName(), groupBgi.getUuid(), groupBgi.getFilePath());
	    return entity;
	}
	@RequestMapping(value="/boardImage/{uuid}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayBoardImage(@PathVariable String uuid) throws IOException{
	    BoardFileVO boardFile = boardFileService.selectFilebyUuid(uuid);
	    entity = commonDownload.getImageEntity(entity, mediaUtils, in, boardFile.getFileName(), boardFile.getUuid(), boardFile.getFileUrl());
	    return entity;
	}
	@GetMapping("/back")
	public String backSite(HttpServletRequest request) {
		return "redirect:/groups";
	}
	private ModelAndView getManageJoin(ModelAndView mv, UserVO user, int gno) {
		if(user!=null) {
			GroupUserVO groupUser = new GroupUserVO();
			groupUser.setGno(this.gno);
			groupUser.setUno(user.getUno());
			String role = groupService.getRolebyUser(groupUser);
			if(role!=null) {
				if(role.equals("ROLE_ADMIN")) {
					mv.addObject("result", "joinSuccess");
				}
			}else {
				mv.addObject("result", "joinDinied");
			}
		}
		mv.addObject("gno", gno);
		return mv;
	}
	private ModelAndView getGroupUser(int gno, ModelAndView mv) {
		List<GroupUserVO> groupUser = groupService.findUserbyGroup(gno);
		int userResult = groupService.countUserbyGroup(gno);
		int goalResult = groupService.countGoalbyGroup(gno);
		mv.addObject("goalCount", goalResult);
		mv.addObject("userCount", userResult);
		mv.addObject("groupUser", groupUser);
		mv.addObject("gno", gno);
		return mv;
	}
}