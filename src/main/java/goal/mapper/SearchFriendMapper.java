package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserVO;

@Mapper
public interface SearchFriendMapper {
	public List<UserVO> allUser(UserVO vo);
}
