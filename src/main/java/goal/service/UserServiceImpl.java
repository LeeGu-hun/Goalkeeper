package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.UserMapper;
import goal.vo.PostVO;
import goal.vo.UserVO;

@Service
@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	

	@Override
	public void insertUser(UserVO vo) {
		mapper.register(vo);
		
	}
	
	@Override
	public String checkLogin(UserVO vo) {
		String check = mapper.read(vo);
		return check;
	}
	@Override
	public String checkId(UserVO vo) {
		String check = mapper.readId(vo);
		return check;
	}

	@Override
	public List<UserVO> allUserList(UserVO vo) {
		return mapper.allUser(vo);
		
	}
	@Override
	public List<PostVO> selectPost(PostVO vo) {
		return mapper.getPost(vo);
		
	}

}
