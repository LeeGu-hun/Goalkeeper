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

	@Override
	public List<BoardFileVO> selectFileName() {
		List<BoardFileVO> boardFile = boardFileMapper.selectFileName();
		return boardFile;
	}

	@Override
	public boolean removeBoardFile(int bno) {
		return boardFileMapper.removeBoardFile(bno)>0 ? true : false;
	}

	@Override
	public int fileInsert(BoardFileVO file) {
		return boardFileMapper.fileInsert(file);
	}

	@Override
	public List<BoardFileVO> searchFile(BoardFileVO file) {
		return boardFileMapper.searchFile(file);
	}

	@Override
	public List<BoardFileVO> getBoardFileList() {
		return boardFileMapper.getBoardFileList();
	}

	@Override
	public BoardFileVO selectFile(int bno) {
		return boardFileMapper.selectFile(bno);
	}

	@Override
	public int countFile(int bno) {
		return boardFileMapper.countFile(bno);
	}

	@Override
	public int countFilebyGroup(BoardVO board) {
		return boardFileMapper.countFilebyGroup(board);
	}

	@Override
	public BoardFileVO selectFilebyUuid(String uuid) {
		return boardFileMapper.selectFilebyUuid(uuid);
	}
	
}
