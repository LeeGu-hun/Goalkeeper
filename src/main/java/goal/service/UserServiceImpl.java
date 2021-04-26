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
	public void insertUser(UserVO vo) {
		mapper.register(vo);
		
	}
	
	@Override
	public int checkLogin(UserVO vo) {
		int check = mapper.read(vo);
		return check;
	}
	
}
