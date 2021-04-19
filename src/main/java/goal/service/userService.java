package goal.service;

import goal.vo.userVO;

public interface userService {
	userVO selectUser();
	void insertUser(userVO vo);		
	boolean checkLogin(userVO vo);
}
