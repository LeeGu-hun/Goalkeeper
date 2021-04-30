package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardFileVO;

@Mapper
public interface BoardFileMapper {
	void insertBoardFile(BoardFileVO boardFile);
	List<BoardFileVO> selectFileName();
	int removeBoardFile(int bno);
}
