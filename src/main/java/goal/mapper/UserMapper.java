package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public UserVO read(String u_id, String u_password);
	
	public void register(UserVO vo);
}
