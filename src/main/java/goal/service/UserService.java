package goal.service;

import goal.vo.UserVO;

public interface UserService {
	UserVO selectUser(String id, String pw);
	void insertUser(UserVO vo);		
	boolean checkLogin(UserVO vo);
}
