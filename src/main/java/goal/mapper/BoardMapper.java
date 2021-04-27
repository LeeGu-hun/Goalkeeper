package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.BoardVO;

@Mapper
public interface BoardMapper {
	void insertBoard(BoardVO board);
}
