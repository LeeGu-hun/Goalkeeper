package goal.service;

import java.util.List;

import goal.vo.GroupGoalVO;
import goal.vo.GroupSVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

public interface GroupService {
	void createGroup(GroupVO group, GroupSVO groupUser, GroupGoalVO groupGoal);
	List<GroupVO> selectGroupList(UserVO user);
	List<GroupVO> allList();
	GroupVO recentGroup();
	List<GroupVO> selectSearchList(String g_cate);
	boolean removeGroup(int gno);
	void insertGoal(GroupGoalVO groupGoal);
	int findDatabyId(int gno);
	int fineUserbyId(int gno);
}
