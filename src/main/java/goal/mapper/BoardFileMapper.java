package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import goal.vo.BoardFileVO;

@Mapper
public interface BoardFileMapper {
	void insertBoardFile(@RequestParam("boardFile") List<BoardFileVO> boardFile);
	
	List<BoardFileVO> selectFileName();
	int removeBoardFile(int bno);
}
