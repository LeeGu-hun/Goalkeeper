package goal.service;

import java.util.List;
import java.util.Map;

import goal.vo.FriendVO;

public interface FriendService {
	List<FriendVO> getFriendsList(FriendVO vo);
	
	boolean addFriend(FriendVO vo);
	
	boolean remove(int fno);
	
	int countFriends(int uno);
	
	List<FriendVO> findMyFriend(Map<String, Object> map);
}
