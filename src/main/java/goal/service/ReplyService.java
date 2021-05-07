package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

public interface ReplyService {
	
	List<ReplyVO> getMainReply();
	List<ReplyVO> getDetailMainReply(int bno);
	void insertReply(ReplyVO vo);
}
