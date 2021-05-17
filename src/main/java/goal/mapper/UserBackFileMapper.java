package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserBackVO;
import goal.vo.UserFileVO;

@Mapper
public interface UserBackFileMapper {
	void insertUserBackFile(UserBackVO vo);
	List<UserBackVO> selectBackFileName();
	int removeUserBackFile(int uno);
	UserBackVO selectBackFile(int uno);
	int checkUserBack(int uno);
}
