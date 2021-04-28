package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardVO;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardVO board);
	
	List<BoardVO> selectBoardList(BoardVO vo);
}
