package goal.service;

import java.util.List;

import goal.vo.BoardFileVO;

public interface BoardFileService {
	void insertBoardFile(BoardFileVO boardFile);
	List<BoardFileVO> selectFileName();
	boolean removeBoardFile(int bno);
}
