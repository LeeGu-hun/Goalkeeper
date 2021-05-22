package goal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goal.mapper.UserMapper;

import goal.vo.ReplyVO;
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
	public String checkId(String userId) {
		String id = mapper.readId(userId);
		return id;
	}

	@Override
	public UserVO getUser(UserVO vo) {
		return mapper.readUser(vo);
	}
	
	@Override
	public void modify(UserVO vo) {
		mapper.modifyUserInfo(vo);
	}
	@Override
	   public int idCheck(UserVO vo) throws Exception {
	      
	      return mapper.idCheck(vo);
	   }

	@Override
	public void profileCheck(int uno) {
		mapper.profileCheck(uno);
	}

	@Override
	public void backgroundCheck(int uno) {
		mapper.backgroundCheck(uno);
	}

	@Override
	public UserVO myPageUserInfo(String userId) {
		return mapper.myPageUserInfo(userId);
	}

}

