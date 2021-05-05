package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;
import goal.vo.GroupVO;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardVO board);
	List<BoardVO> searchBoard(BoardVO vo);
	BoardVO recentBoard();
	List<BoardVO> selectBoardList(UserVO vo);
	List<BoardVO> getBoardList();
}