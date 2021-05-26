package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.BoardMapper;
import goal.vo.BoardVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

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
	public BoardVO findBoardbyBno(int bno) {
		return boardMapper.findBoardbyBno(bno);
	}

	public List<BoardVO> selectBoardList(UserVO uservo) {
	      return boardMapper.selectBoardList(uservo);
	}
	@Override
	public BoardVO recentBoard() {
		return boardMapper.recentBoard();
	}

	@Override
	public List<BoardVO> getBoardList() {
		return boardMapper.getBoardList();
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO vo) {
		return boardMapper.searchBoard(vo);	
	}

	@Override
	public List<BoardVO> getGroupBoardList(String bo_group) {
		return boardMapper.getGroupBoardList(bo_group);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void modifyBoard(BoardVO board) {
		boardMapper.modifyBoard(board);
		
	}

	@Override
	public void deleteBoard(BoardVO board) {
		boardMapper.deleteBoard(board);
		
	}
	@Override
	   public int boardCount(String userId) {
	      return boardMapper.boardCount(userId);
	   }

	@Override
	public int boardAllCount() {
		return boardMapper.boardAllCount();
	}

	@Override
	public int friendCount(int uno) {
		return boardMapper.friendCount(uno);
	}

	@Override
	public int userCount() {
		return boardMapper.userCount();
	}

	@Override
	public int mainGroupCount() {
		return boardMapper.mainGroupCount();
	}

	@Override
	public int myGroupCount(int uno) {
		return boardMapper.myGroupCount(uno);
	}

	@Override
	public List<BoardVO> getMyPageBoardList(String userId) {
		return boardMapper.getMyPageBoardList(userId);
	}

	@Override
	public String recGroup() {
		return boardMapper.recGroup();
	}



	
}