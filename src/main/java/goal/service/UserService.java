package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import goal.vo.PostVO;
import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(String u_id);
	public UserVO checkUser(String u_id);
	List<UserVO> allUserList(UserVO vo);
	List<PostVO> selectPost(PostVO vo);
	UserDetails loadUserByUsername(String u_id);
}
