package goal.service;

import goal.vo.UserVO;

public interface userService {
	UserVO selectUser();
	void insertUser(UserVO vo);		
	boolean checkLogin(UserVO vo);
}
