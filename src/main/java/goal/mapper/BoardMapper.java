package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;
import goal.vo.GroupVO;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardVO board);
	List<BoardFileVO> insertBoardFile(List<BoardFileVO> boardFileVO);
	List<BoardVO> searchBoard(BoardVO vo);
	BoardVO recentBoard();
	List<BoardVO> selectBoardList(UserVO vo);
	List<BoardVO> getBoardList();
	List<BoardVO> getGroupBoardList(String bo_group);
}