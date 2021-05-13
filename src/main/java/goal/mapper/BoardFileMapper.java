package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import goal.vo.BoardFileVO;
import goal.vo.BoardVO;

@Mapper
public interface BoardFileMapper {
	int fileInsert(BoardFileVO file);
	List<BoardFileVO> selectFileName();
	int removeBoardFile(int bno);
	List<BoardFileVO> searchFile(BoardFileVO file);
	List<BoardFileVO> getBoardFileList();
	BoardFileVO selectFile(int bno);
	int countFile(int bno);
	int countFilebyGroup(BoardVO board);
	BoardFileVO selectFilebyUuid(String uuid);
}
