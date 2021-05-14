package goal.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import goal.vo.FriendVO;
import goal.vo.UserVO;

@Mapper
public interface ChatMapper {
	List<FriendVO> chatFriendList(UserVO vo);
}
