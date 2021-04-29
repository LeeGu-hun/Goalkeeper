package goal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public UserVO checkId(UserVO vo) {
		vo = mapper.readId(vo);
		return vo;
	}

	@Override
	public List<UserVO> allUserList(UserVO vo) {
		return mapper.allUser(vo);
		
	}
	@Override
	public List<PostVO> selectPost(PostVO vo) {
		return mapper.getPost(vo);
		
	}
	@Override
    public UserDetails loadUserByUsername(UserVO vo) throws UsernameNotFoundException {
        UserVO user = mapper.readId(vo);
        //findbyemail이 아닌 readId로 전체가져오기

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    
        return new User(user.getU_id(), user.getU_password(), authorities);
    }
}
}
