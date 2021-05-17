package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupGoalVO;
import goal.vo.BoardVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Mapper
public interface ReplyMapper {
	List<ReplyVO> readMainReply();
	List<ReplyVO> readDetailMainReply(int bno);
	int replyCount() throws Exception;
    List<ReplyVO> replyList() throws Exception;
	int insertReply(ReplyVO replyVO);
}
