package goal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendApplyVO;
import goal.vo.FriendVO;

@Mapper
public interface FriendApplyMapper {
	int apply(FriendApplyVO apply);
	List<FriendApplyVO> receiveList(int uno);
	List<FriendApplyVO> applyList(int uno);
	void acceptFriend(FriendApplyVO apply);
	void deleteFriend(Map<String, Object> map);
	void applyCancel(Map<String, Object> map);
	void applyFileCheck(int receiveUno);
	void applyBackCheck(int receiveUno);
	void receiveFileCheck(int applyUno);
	void receiveBackCheck(int applyUno);
	int applyCount(int applyUno);
	int receiveCount(int receiveCount);
}
