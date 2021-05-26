package goal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.common.UserCommonDownload;
import goal.service.ChatService;
import goal.service.CommonService;
import goal.service.FriendApplyService;
import goal.service.FriendService;
import goal.service.MyGoalService;
import goal.service.SearchFriendService;
import goal.service.UserBackFileService;
import goal.service.UserFileService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.FriendApplyVO;
import goal.vo.FriendVO;
import goal.vo.MyGoalVO;
import goal.vo.UserBackVO;
import goal.vo.UserFileVO;
import goal.vo.UserVO;

@Controller
public class OhterFriendsController {
	@Autowired
	public FriendService friendService;
	
	@Autowired
	public SearchFriendService searchFriendService;
	
	@Autowired
	public UserService userService;

	@Autowired
	public CommonService commonService;

	@Autowired
	public MyGoalService myGoalService;
	
	@Autowired
	public UserFileService userFileService;
	
	@Autowired
	public UserBackFileService userBackFileService;
	
	@Autowired
	public FriendApplyService friendApplyService;
	
	@Autowired
	private ChatService chatService;
	
	private CommonDownload commonDownload = new CommonDownload();
	private UserCommonDownload userCommon = new UserCommonDownload();
	
	MediaUtils mediaUtils = new MediaUtils();
    InputStream in = null;
    ResponseEntity<byte[]> entity = null;
    
	
	
	
}
