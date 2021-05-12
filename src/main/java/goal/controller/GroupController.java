	package goal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.GroupFileService;
import goal.service.GroupService;
import goal.upload.BoardUpload;
import goal.upload.GroupUpload;
import goal.util.MediaUtils;
import goal.vo.BoardVO;
import goal.vo.GroupFileVO;
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
	
	@Autowired
	private BoardService boardService;
	
	private GroupUpload groupUpload = new GroupUpload();
	
	@Autowired
	private BoardUpload boardUpload;
	
	private int gno = 0;
	
	@GetMapping("/groups")
	public ModelAndView openGroup(@ModelAttribute GroupVO group, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/group/groups");
		UserVO user = new UserVO();
		user = commonService.getLoginUser(request);
		List<GroupVO> groupList = groupService.allList();
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
		List<GroupVO> searchList = groupService.selectSearchList(word);
		if(searchList == null) {
			mv.addObject("fail", "fail");
		} 
		mv.addObject("list", searchList);
		mv = commonService.checkLoginUser(request, mv);
		return mv;
	}
	@PostMapping("/group_create")
	public String createGroup(GroupVO group, GroupUserVO groups, MultipartHttpServletRequest multi, HttpServletRequest request) throws Exception {	
		GroupFileVO groupFile = new GroupFileVO();
		UserVO user = commonService.getLoginUser(request);
		groups.setUno(user.getUno());
		group.setUno(user.getUno());
		groupService.createGroup(group, groups);
		groupFile = groupUpload.requestSingleUpload(multi);
		groupFileService.insertGroupFile(group, groupFile);
		
		return "redirect:/groups";
	}
	@GetMapping("/group_detail/{gno}")
	public ModelAndView openDetail(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group-timeline");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.selectGroup(gno);
		this.gno = gno;
		List<BoardVO> boardList = boardService.getGroupBoardList(group.getG_name());
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
		boardService.insertBoard(board);
		if(fileCheck.equals("false")) {
			boardUpload.BoardUpload(board, files);
		}
		return mv;
	}
	
	@GetMapping("/group_join")
	public ModelAndView openJoin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_join");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.selectGroup(this.gno);
		mv.addObject("group", group);
		return mv;
	}
	
	@GetMapping("/group_member/{gno}")
	public ModelAndView openMember(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_member");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.selectGroup(gno);
		mv.addObject("group", group);
		return mv;
	}
	
	@GetMapping("/group_info/{gno}")
	public ModelAndView openInfo(@PathVariable("gno") int gno, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/view/group/group_info");
		mv = commonService.checkLoginUser(request, mv);
		GroupVO group = groupService.selectGroup(gno);
		mv.addObject("group", group);
		return mv;	
	}
	@GetMapping("/group_home")
	public String openHome() {
		return "redirect:/group_detail";
	}
	
	@RequestMapping(value="/display/{gno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(@PathVariable int gno) throws IOException{
		MediaUtils mediaUtils = new MediaUtils();
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    GroupFileVO groupFile = groupFileService.selectFile(gno);
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
      return entity;
	}
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getImage() throws IOException{
		MediaUtils mediaUtils = new MediaUtils();
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    GroupFileVO groupFile = groupFileService.selectFile(gno);
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
	     return entity;
	}

	private ModelAndView getGroupUser(int gno, ModelAndView mv) {
		GroupUserNameVO groupUser = new GroupUserNameVO();
		groupUser = groupService.fineUserbyGroup(gno);
		int userResult = groupService.countUserbyGroup(gno);
		int goalResult = groupService.countGoalbyGroup(gno);
		mv.addObject("goalCount", goalResult);
		mv.addObject("userCount", userResult);
		mv.addObject("groupUser", groupUser);
		mv.addObject("gno", gno);
		return mv;
	}
}