package goal.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import goal.vo.BoardFileVO;
import goal.vo.BoardVO;

public interface BoardFileService {
	List<BoardFileVO> selectFileName();
	boolean removeBoardFile(int bno);
	void insertBoardFile(@RequestParam("boardFile") List<BoardFileVO> boardFile);
}
