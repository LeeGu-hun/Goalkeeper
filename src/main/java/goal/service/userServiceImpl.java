package goal.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.vo.UserVO;

@Service
@Component
public class userServiceImpl implements userService{

	@Override
	public UserVO selectUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(UserVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkLogin(UserVO vo) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
