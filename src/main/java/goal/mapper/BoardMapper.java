package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.ChatVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;
import goal.vo.GroupVO;
import goal.vo.ReCommentVO;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardVO board);
	void modifyBoard(BoardVO board);
	List<BoardFileVO> insertBoardFile(List<BoardFileVO> boardFileVO);
	List<BoardVO> searchBoard(BoardVO vo);
	BoardVO findBoardbyBno(int bno);
	BoardVO recentBoard();
	List<BoardVO> selectBoardList(UserVO vo);
	List<BoardVO> getBoardList();
	List<BoardVO> getGroupBoardList(String bo_group);
	List<BoardVO> getMyPageBoardList(String userId);
	void updateBoard(BoardVO board);
	void deleteBoard(BoardVO board);
	int boardCount(String userId);
	int boardAllCount();
	int friendCount(int uno);
	int userCount();
	int mainGroupCount();
	int myGroupCount(int uno);
	int searchNullBoard(BoardVO vo);
	ReCommentVO readMainRecmt();

}