package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import goal.vo.PostVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

public interface UserService {
	
	void insertUser(UserVO vo);		
	String checkLogin(UserVO vo);
	String checkId(String u_id);
	public UserVO checkUser(String u_id);
	List<PostVO> selectPost(PostVO vo);
	List<ReplyVO> selectReply(ReplyVO vo);

}
