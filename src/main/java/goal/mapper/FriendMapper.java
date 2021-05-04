package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendVO;
import goal.vo.UserVO;

@Mapper
public interface FriendMapper {
	List<FriendVO> getlistFriend(FriendVO vo);
	
	int addFriend(FriendVO vo);
	
	int deleteFriend(int fno);
}
