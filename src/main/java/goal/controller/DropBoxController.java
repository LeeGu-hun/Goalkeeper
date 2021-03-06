package goal.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.common.UserCommonDownload;
import goal.service.ChatService;
import goal.service.CommonService;
import goal.service.FriendApplyService;
import goal.service.FriendService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.ChatVO;
import goal.vo.UserBackVO;
import goal.vo.UserFileVO;
import goal.vo.UserVO;

@Controller
public class DropBoxController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFileService userFileService;
	
	@Autowired
	private UserBackFileService userBackFileService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private FriendApplyService friendApplyService;
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
	
	@GetMapping("/profileInfo")
	public ModelAndView openProfileInfo(HttpServletRequest request) {
		UserVO user = commonService.getLoginUser(request);

		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-info");
		mv = commonService.checkLoginUser(request, mv);
		
		List<ChatVO> friendlist = chatService.findFriendList(user);
		mv.addObject("user", user);
		mv.addObject("friendlist", friendlist);
		mv.addObject("uno", user.getUno());
		mv.addObject("userFileCheck", user.getUserFileCheck());
		mv.addObject("userBackCheck", user.getUserBackCheck());
		return mv;
	}

	@PostMapping("/modifyMyInfo")
	public String modifyMyInfo(UserVO vo) {
		userService.modify(vo);
		return "redirect:/profileInfo";
	}
	
	@GetMapping("/profileModify")
	public ModelAndView openProfileModify(HttpServletRequest request, UserVO vo) {
		vo = commonService.getLoginUser(request);
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

	@PostMapping("/profileInfo/profile")
	public String insertProfile(UserVO user, HttpServletRequest request, @RequestPart("files") MultipartFile files)
			throws Exception {
		UserFileVO vo = new UserFileVO();
		user = commonService.getLoginUser(request);
		vo.setUno(user.getUno());
		user.setUserFileCheck("Y");
		int check = userFileService.checkProfile(vo.getUno());
		
		String fileUrl = "C:/profile";
		File uploadPath = new File(fileUrl);
		
	    if (uploadPath.exists() == false) {
        	uploadPath.mkdirs();
        }
		   
    	String fileName = files.getOriginalFilename(); 
        String uuid = RandomStringUtils.randomAlphanumeric(32)+"."+"jpg";
        String filePath = fileUrl + "/" + uuid;
        
        File dest = new File(filePath);
        files.transferTo(dest);
        
        vo.setUserFileId(uuid);
        vo.setUserFileName(fileName);
        vo.setUserFilePath(filePath);
        userService.profileCheck(user.getUno());
        friendService.profileCheck(user.getUno());
        friendApplyService.applyFileCheck(user.getUno());
        friendApplyService.receiveFileCheck(user.getUno());
        
		if(check != 0) {
			userFileService.removeUserFile(vo.getUno());
			userFileService.insertUserFile(vo);
		} else {
	        userFileService.insertUserFile(vo);
		}
		
        return "redirect:/profileInfo";
	}
	
	@PostMapping("/profileInfo/background")
	public String insertBackground(UserVO user, HttpServletRequest request, @RequestPart("backFiles") MultipartFile backFiles)
			throws Exception {
		UserBackVO vo = new UserBackVO();
		user = commonService.getLoginUser(request);
		vo.setUno(user.getUno());
		user.setUserBackCheck("Y");
		int check = userBackFileService.checkUserBack(vo.getUno());
		
		String fileUrl = "C:/userbackground";
		File uploadPath = new File(fileUrl);
		
	    if (uploadPath.exists() == false) {
        	uploadPath.mkdirs();
        }
		   
    	String fileName = backFiles.getOriginalFilename(); 
        String uuid = RandomStringUtils.randomAlphanumeric(32)+"."+"jpg";
        String filePath = fileUrl + "/" + uuid;
        
        File dest = new File(filePath);
        backFiles.transferTo(dest);
           
        vo.setBackId(uuid);
        vo.setBackName(fileName);
        vo.setBackPath(filePath);
        userService.backgroundCheck(user.getUno());
        friendService.profileBackCheck(user.getUno());
        friendApplyService.applyBackCheck(user.getUno());
        friendApplyService.receiveBackCheck(user.getUno());
        
		if(check != 0) {
			userBackFileService.removeBackFile(vo.getUno());
			userBackFileService.insertUserBackFile(vo);
		} else {
			userBackFileService.insertUserBackFile(vo);
		}
		
        return "redirect:/profileInfo";
	}
	
}
