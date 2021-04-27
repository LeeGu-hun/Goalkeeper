package goal.service;

import java.util.List;

import goal.vo.FriendVO;



public interface FriendService {
	List<FriendVO> getFriendsList(int uno);
	
	void addFriend(FriendVO vo);
	
	boolean remove(int fno);
}
