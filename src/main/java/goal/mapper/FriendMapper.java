package goal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.FriendVO;
import goal.vo.UserFileVO;

@Mapper
public interface FriendMapper {
	List<FriendVO> getlistFriend(FriendVO vo);
	
	int addFriend(FriendVO vo);
	
	int deleteFriend(int fno);
	
	int countFriend(int uno);
	
	List<FriendVO> findMyFriend(Map<String, Object> map);
	
	void profileCheck(int uno);
	
	void profileBackCheck(int uno);
	
}
