package goal.service;

import java.util.List;
import java.util.Map;

import goal.vo.FriendApplyVO;
import goal.vo.FriendVO;

public interface FriendApplyService {
	int apply(FriendApplyVO apply);
	List<FriendApplyVO> receiveList(int uno);
	List<FriendApplyVO> applyList(int uno);
	void acceptFriend(FriendApplyVO apply);
	void rejectFriend(Map<String, Object> map);
	void applyCancel(Map<String, Object> map);
	void applyFileCheck(int receiveUno);
	void applyBackCheck(int receiveUno);
	void receiveFileCheck(int applyUno);
	void receiveBackCheck(int applyUno);
	int applyCount(int applyUno);
	int receiveCount(int receiveCount);
}
