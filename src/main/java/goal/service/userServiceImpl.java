package goal.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.vo.userVO;

@Service
@Component
public class userServiceImpl implements userService{

	@Override
	public userVO selectUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(userVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkLogin(userVO vo) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
