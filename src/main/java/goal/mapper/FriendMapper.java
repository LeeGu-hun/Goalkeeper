package goal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendVO;

@Mapper
public interface FriendMapper {
	List<FriendVO> getlistFriend(FriendVO vo);
	
	int addFriend(FriendVO vo);
	
	int deleteFriend(int fno);
	
	List<FriendVO> findMyFriend(Map<String, Object> map);
	
}
