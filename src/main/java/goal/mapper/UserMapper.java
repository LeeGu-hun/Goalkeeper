package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public int read(UserVO vo);
	
	public void register(UserVO vo);
}
