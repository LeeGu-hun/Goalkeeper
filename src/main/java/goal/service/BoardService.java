package goal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.BoardVO;

public interface BoardService {

	void insertBoard(BoardVO board);
	List<BoardVO> selectBoardList(BoardVO vo);
}
