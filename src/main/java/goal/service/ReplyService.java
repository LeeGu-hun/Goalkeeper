package goal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

public interface ReplyService {
	
	ReplyVO getMainReply();
	List<ReplyVO> getDetailMainReply(int bno);
	int insertReply(ReplyVO replyVO);
}
