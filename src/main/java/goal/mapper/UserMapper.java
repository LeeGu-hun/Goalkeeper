package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public String read(UserVO vo);
	
	public String readId(UserVO vo);
	
	public void register(UserVO vo);
	
	public List<UserVO> allUser(UserVO vo);

}
