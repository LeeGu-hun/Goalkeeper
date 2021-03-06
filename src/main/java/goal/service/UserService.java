package goal.service;


import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(String userId);
	UserVO getUser(UserVO vo);
	void modify(UserVO vo);
	public int idCheck(UserVO vo) throws Exception;
	public void profileCheck(int uno);
	public void backgroundCheck(int uno);
	UserVO myPageUserInfo(String userId);
}
