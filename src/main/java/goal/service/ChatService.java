package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import goal.vo.ChatVO;
import goal.vo.FriendVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

public interface ChatService {
	
	List<ChatVO> findFriendList(UserVO vo);		
	
}
