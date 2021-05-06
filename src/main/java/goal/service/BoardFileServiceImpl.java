package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import goal.mapper.BoardFileMapper;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;

@Service
public class BoardFileServiceImpl implements BoardFileService {

	@Autowired
	private BoardFileMapper boardFileMapper;
	
	@Transactional
	@Override
	public void insertBoardFile(@RequestParam("boardFile") List<BoardFileVO> boardFile) {
		boardFileMapper.insertBoardFile(boardFile);
	}

	@Override
	public List<BoardFileVO> selectFileName() {
		List<BoardFileVO> boardFile = boardFileMapper.selectFileName();
		return boardFile;
	}

	@Override
	public boolean removeBoardFile(int bno) {
		return boardFileMapper.removeBoardFile(bno)>0 ? true : false;
	}

	

	

}
