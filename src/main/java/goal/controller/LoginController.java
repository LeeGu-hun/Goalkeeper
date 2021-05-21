package goal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import goal.service.UserService;
import goal.vo.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login_check", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserVO> loginCheck(@RequestBody UserVO user){
		UserVO checkUser = userService.getUser(user);
		return new ResponseEntity<UserVO>(checkUser,HttpStatus.OK);
	}
	
}
