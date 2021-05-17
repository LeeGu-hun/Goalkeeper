package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserBackVO;
import goal.vo.UserFileVO;

@Mapper
public interface UserFileMapper {
	void insertUserFile(UserFileVO vo);
	List<UserFileVO> selectFileName();
	int removeUserFile(int uno);
	UserFileVO selectFile(int uno);
	int checkProfile(int uno);
}
