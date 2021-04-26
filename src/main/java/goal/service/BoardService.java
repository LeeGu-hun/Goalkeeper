package goal.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.BoardVO;

public interface BoardService {

	void insertBoard(BoardVO board, MultipartHttpServletRequest multi);

}
