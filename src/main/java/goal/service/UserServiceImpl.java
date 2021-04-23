package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.UserMapper;
import goal.vo.UserVO;

@Service
@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserVO loginUser(UserVO vo) {
		mapper.read(vo);
		return null;
	}

	@Override
	public void insertUser(UserVO vo) {
		mapper.register(vo);
		
	}

	@Override
	public boolean checkLogin(UserVO vo) {
		// TODO Auto-generated 
		return false;
	}
	
}
