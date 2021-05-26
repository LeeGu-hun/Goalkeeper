  
package goal.service;

import java.util.List;

import goal.vo.BoardVO;
import goal.vo.ChatVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

public interface BoardService {
	void insertBoard(BoardVO board);
	void modifyBoard(BoardVO board);
	List<BoardVO> searchBoard(BoardVO vo);
	List<BoardVO> getBoardList();
	List<BoardVO> selectBoardList(UserVO vo);
	List<BoardVO> getMyPageBoardList(String userId);
	BoardVO findBoardbyBno(int bno);
	BoardVO recentBoard();
	List<BoardVO> getGroupBoardList(String bo_group);
	void updateBoard(BoardVO board);
	void deleteBoard(BoardVO board);
	int boardCount(String userId);
	int boardAllCount();
	int friendCount(int uno);
	int userCount();
	int mainGroupCount();
	int myGroupCount(int uno);
	int searchNullBoard(BoardVO vo);
}