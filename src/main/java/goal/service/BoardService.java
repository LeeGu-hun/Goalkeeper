package goal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.BoardVO;
import goal.vo.GroupVO;

public interface BoardService {

	void insertBoard(BoardVO board);
	List<BoardVO> getBoardList();
	List<BoardVO> selectBoardList(BoardVO vo);
	BoardVO recentBoard();
}
