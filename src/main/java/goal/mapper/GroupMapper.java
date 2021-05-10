package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupGoalVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Mapper
public interface GroupMapper {
	void createGroup(GroupVO group);
	List<GroupFileVO> insertGroupFile(List<GroupFileVO> groupFile);
	List<GroupVO> selectGroupList(UserVO vo);
	List<GroupVO> allList();
	GroupVO recentGroup();
	List<GroupVO> selectSearchList(String word);
	int removeGroup(int gno);
	void insertData(GroupGoalVO groupGoal);
	List<GroupGoalVO> findGoalbyId(int gno);
	GroupVO selectGroup(int gno);
	int countGoalbyId(int gno);
}
