package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendVO;

@Mapper
public interface FriendMapper {
	public List<FriendVO> listFriend(int uno);
	
	public int addFriend(FriendVO vo);
	
	public int deleteFriend(int fno);
}
