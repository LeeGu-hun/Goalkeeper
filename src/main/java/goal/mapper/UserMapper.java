package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public String read(UserVO vo);
	
	public String readId(String u_id);
	
	public UserVO readUser(UserVO user);
	
	public void register(UserVO vo);
	
	public void modifyUserInfo(UserVO vo);
	
	public int idCheck(UserVO vo);

	
}
