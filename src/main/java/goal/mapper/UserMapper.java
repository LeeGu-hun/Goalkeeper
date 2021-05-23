package goal.mapper;


import org.apache.ibatis.annotations.Mapper;
import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public String read(UserVO vo);
	
	public String readId(String u_id);
	
	public UserVO readUser(UserVO user);
	
	public void register(UserVO vo);
	
	public void modifyUserInfo(UserVO vo);
	
	public int idCheck(UserVO vo);

	public void profileCheck(int uno);
	
	public void backgroundCheck(int uno);
	
	public UserVO myPageUserInfo(String userId);
	
}
