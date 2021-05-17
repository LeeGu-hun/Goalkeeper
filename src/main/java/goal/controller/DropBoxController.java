package goal.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import goal.service.CommonService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.UserBackVO;
import goal.vo.UserFileVO;
import goal.vo.UserVO;
import lombok.extern.log4j.Log4j;

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
	
	private UserCommonDownload commonDownload = new UserCommonDownload();
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
	
	@GetMapping("/profileInfo")
	public ModelAndView openProfileInfo(HttpServletRequest request, UserVO vo) {
		vo = getLoginUser(request);
		UserFileVO file = new UserFileVO();
		UserBackVO back = new UserBackVO();
		file.setUno(vo.getUno());
		back.setUno(vo.getUno());
		
		ModelAndView mv = new ModelAndView("view/ProfileDropBox/hub-profile-info");
		mv = commonService.checkLoginUser(request, mv);
		mv.addObject("uno", back.getUno());
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

	@PostMapping("/profileInfo/profile")
	public String insertProfile(UserVO user, HttpServletRequest request, @RequestPart("files") MultipartFile files)
			throws Exception {
		UserFileVO vo = new UserFileVO();
		user = getLoginUser(request);
		vo.setUno(user.getUno());
		int check = userBackFileService.checkUserBack(vo.getUno());
		
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
		user = getLoginUser(request);
		vo.setUno(user.getUno());
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
        
		if(check != 0) {
			userBackFileService.removeBackFile(vo.getUno());
			userBackFileService.insertUserBackFile(vo);
		} else {
			userBackFileService.insertUserBackFile(vo);
		}
		
        return "redirect:/profileInfo";
	}
	
	@RequestMapping(value="/profile/{uno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(@PathVariable int uno) throws IOException{
	    UserFileVO userFile = userFileService.selectFile(uno);
	    entity = commonDownload.getImageEntity(entity, mediaUtils, in, userFile.getUserFileName(), userFile.getUserFileId(), userFile.getUserFilePath());
	    return entity;
	}
	
	@RequestMapping(value="/background/{uno}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayBackground(@PathVariable int uno) throws IOException{
	    UserBackVO backFile = userBackFileService.selectBackFile(uno);
	    entity = commonDownload.getImageEntity(entity, mediaUtils, in, backFile.getBackName(), backFile.getBackId(), backFile.getBackPath());
	    return entity;
	}
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    UserVO user = (UserVO) session.getAttribute("user");
	    return user;
	}
	
}
