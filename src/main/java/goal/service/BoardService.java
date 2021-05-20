  
package goal.service;

import java.util.List;

import goal.vo.BoardVO;
import goal.vo.UserVO;

public interface BoardService {
	void insertBoard(BoardVO board);
	void modifyBoard(BoardVO board);
	List<BoardVO> searchBoard(BoardVO vo);
	List<BoardVO> getBoardList();
	List<BoardVO> selectBoardList(UserVO vo);
	BoardVO findBoardbyBno(int bno);
	BoardVO recentBoard();
	List<BoardVO> getGroupBoardList(String bo_group);
	void updateBoard(BoardVO board);
	void deleteBoard(BoardVO board);
	int boardCount(String userId);
	int boardAllCount();
}