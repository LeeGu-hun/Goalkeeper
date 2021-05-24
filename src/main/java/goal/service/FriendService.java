package goal.service;

import java.util.List;
import java.util.Map;

import goal.vo.FriendVO;
import goal.vo.UserFileVO;

public interface FriendService {
	List<FriendVO> getFriendsList(FriendVO vo);

	boolean remove(Map<String, Object> map);
	
	int countFriends(int uno);
	
	List<FriendVO> findMyFriend(Map<String, Object> map);
	
	void profileCheck(int uno);
	
	void profileBackCheck(int uno);
	
	int countPost(String userId);
	
}
