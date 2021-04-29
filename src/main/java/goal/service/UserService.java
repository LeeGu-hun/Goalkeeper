package goal.service;

import java.util.List;

import goal.vo.PostVO;
import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(UserVO vo);
	List<UserVO> allUserList(UserVO vo);
	List<PostVO> selectPost(PostVO vo);

}
