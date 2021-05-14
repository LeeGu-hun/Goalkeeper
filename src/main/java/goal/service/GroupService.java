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
	List<GroupVO> getGroupList(UserVO user);
	List<GroupVO> getAllList();
	GroupVO recentGroup();
	List<GroupVO> getSearchList(String g_cate);
	boolean removeGroup(int gno);
	void insertGoal(GroupGoalVO groupGoal);
	List<GroupGoalVO> getGoalbyId(int gno);
	GroupVO getGroup(int gno);
	int findGnobyName(String g_name);
	List<GroupUserNameVO> findUserbyGroup(int gno);
	int countUserbyGroup(int gno);
	int countGoalbyGroup(int gno);
	void insertGroupJoin(GroupJoinVO join);
	List<GroupJoinVO> getGroupJoin(int gno);
	int checkUserbyGroup(GroupUserVO groupUser);
	String getRolebyUser(GroupUserVO groupUser);
	void insertGroupUser(GroupUserVO groupUser);
	void removeGroupJoin(GroupJoinVO join);
}
