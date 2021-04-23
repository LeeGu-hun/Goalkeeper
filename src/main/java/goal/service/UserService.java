package goal.service;

import goal.vo.UserVO;

public interface UserService {
	UserVO selectUser();
	void insertUser(UserVO vo);		
	boolean checkLogin(UserVO vo);
}
