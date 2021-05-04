package goal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import goal.repository.UserRepository;
import goal.vo.UserVO;
import lombok.AllArgsConstructor;


@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository user;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String u_id) throws UsernameNotFoundException {
		UserVO user = this.user.findById(u_id);
		if(user == null) {
			return null;
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		return new User(user.getUserId(), user.getUserPw(), authorities);
	}

	public UserVO save(UserVO vo) {
		vo.setUserPw(passwordEncoder.encode(vo.getUserPw()));
		return user.save(vo);
	}

}
 
