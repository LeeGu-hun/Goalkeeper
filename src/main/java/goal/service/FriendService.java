package goal.service;

import java.util.List;

import goal.vo.FriendVO;
import goal.vo.UserVO;

public interface FriendService {
	List<FriendVO> getFriendsList(FriendVO vo);
	
	void addFriend(FriendVO vo);
	
	boolean remove(int uno);
}
