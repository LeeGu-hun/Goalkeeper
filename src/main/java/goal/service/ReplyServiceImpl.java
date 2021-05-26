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

import goal.mapper.ReplyMapper;
import goal.mapper.UserMapper;
import goal.vo.BoardVO;
import goal.vo.ReCommentVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Service
@Component
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper mapper;
	

	@Override
	public ReplyVO getMainReply() {
		return mapper.readMainReply();
	}

	@Override
	public List<ReplyVO> getDetailMainReply(int bno) {
		return mapper.readDetailMainReply(bno);
	}


	@Override
	public void insertReply(ReplyVO replyVO) {
		mapper.insertReply(replyVO);
	}


	@Override
	public void insertRecmt(ReCommentVO recmtVO) {
		mapper.insertRecmt(recmtVO);
	}

	
}

