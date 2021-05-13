package goal.service;

import java.util.List;

import goal.vo.GroupGoalVO;
import goal.vo.GroupJoinVO;
import goal.vo.GroupUserNameVO;
import goal.vo.GroupUserVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

public interface GroupService {
	void createGroup(GroupVO group, GroupUserVO groupUser);
	List<GroupVO> selectGroupList(UserVO user);
	List<GroupVO> allList();
	GroupVO recentGroup();
	List<GroupVO> selectSearchList(String g_cate);
	boolean removeGroup(int gno);
	void insertGoal(GroupGoalVO groupGoal);
	List<GroupGoalVO> findGoalbyId(int gno);
	GroupVO selectGroup(int gno);
	
	GroupUserNameVO fineUserbyGroup(int gno);
	int countUserbyGroup(int gno);
	int countGoalbyGroup(int gno);
	void insertGroupJoin(GroupJoinVO join);
}
