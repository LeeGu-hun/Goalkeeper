package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendVO;
import goal.vo.UserVO;

@Mapper
public interface FriendMapper {
	public List<FriendVO> listFriend(UserVO vo);
	
	public int addFriend(FriendVO vo);
	
	public int deleteFriend(int uno);
}
