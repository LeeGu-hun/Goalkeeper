package goal.service;

import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(UserVO vo);
}
