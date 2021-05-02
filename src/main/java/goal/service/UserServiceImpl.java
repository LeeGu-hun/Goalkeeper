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

import goal.mapper.UserMapper;
import goal.vo.PostVO;
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
	public String checkId(String u_id) {
		String id = mapper.readId(u_id);
		return id;
	}

	
	@Override
	public List<PostVO> selectPost(PostVO vo) {
		return mapper.getPost(vo);
		
	}
	@Override
	public List<ReplyVO> selectReply(ReplyVO vo) {
		return mapper.getReply(vo);
		
	}
}

