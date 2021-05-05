  
package goal.service;

import java.util.List;

import goal.vo.BoardVO;
import goal.vo.UserVO;

public interface BoardService {
	void insertBoard(BoardVO board);
	List<BoardVO> searchBoard(BoardVO vo);
	List<BoardVO> getBoardList();
	List<BoardVO> selectBoardList(UserVO vo);
	BoardVO recentBoard();
}