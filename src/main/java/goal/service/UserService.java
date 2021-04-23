package goal.service;

import goal.vo.UserVO;

public interface UserService {
	UserVO loginUser(UserVO vo);
	void insertUser(UserVO vo);		
	boolean checkLogin(UserVO vo);
}
