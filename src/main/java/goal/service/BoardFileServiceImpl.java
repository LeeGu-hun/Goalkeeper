package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.BoardFileMapper;
import goal.vo.BoardFileVO;

@Service
public class BoardFileServiceImpl implements BoardFileService {

	@Autowired
	private BoardFileMapper boardFileMapper;
	
	@Override
	public void insertBoardFile(BoardFileVO boardFile) {
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
