package goal.service;

import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	int checkLogin(UserVO vo);
}
