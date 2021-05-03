package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;


import goal.vo.ReplyVO;
import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(String userId);

}
