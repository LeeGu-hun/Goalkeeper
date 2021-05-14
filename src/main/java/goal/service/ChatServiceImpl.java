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

import goal.mapper.ChatMapper;
import goal.mapper.UserMapper;
import goal.vo.FriendVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Service
@Component
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatMapper mapper;
	
	@Override
	public List<FriendVO> findFriendList(UserVO vo) {
		
		return mapper.chatFriendList(vo);
		
	}

}

