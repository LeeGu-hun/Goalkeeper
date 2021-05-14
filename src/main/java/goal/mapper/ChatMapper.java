package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.FriendVO;

@Mapper
public interface ChatMapper {
	void chatFriendList(FriendVO vo);
}