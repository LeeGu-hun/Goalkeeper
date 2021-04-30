package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.BoardMapper;
import goal.vo.BoardVO;

@Service
@Component
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardMapper.insertBoard(vo);
		
	}
	@Override
	   public List<BoardVO> selectBoardList(BoardVO vo) {
	      return boardMapper.selectBoardList(vo);
	}
	@Override
	public BoardVO recentBoard() {
		return boardMapper.recentBoard();
	}
}
