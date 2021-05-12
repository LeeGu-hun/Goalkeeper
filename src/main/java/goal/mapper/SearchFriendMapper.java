package goal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserVO;

@Mapper
public interface SearchFriendMapper {
	List<UserVO> allUser(UserVO vo);
	List<UserVO> searchUser(Map<String, Object> map);
}
