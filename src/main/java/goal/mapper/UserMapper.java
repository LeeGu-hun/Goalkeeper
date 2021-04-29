package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.PostVO;
import goal.vo.UserVO;

@Mapper
public interface UserMapper {
	public String read(UserVO vo);
	
	public UserVO readId(UserVO vo);
	
	public void register(UserVO vo);
	
	public List<UserVO> allUser(UserVO vo);
	
	public List<PostVO> getPost(PostVO vo);

}
