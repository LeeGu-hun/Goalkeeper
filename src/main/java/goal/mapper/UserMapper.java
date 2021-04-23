package goal.mapper;

import goal.vo.UserVO;

public interface UserMapper {
	public UserVO read(String u_id, String u_password);
	
	public void register(UserVO vo);
}
