package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.BoardFileMapper;
import goal.mapper.BoardMapper;
import goal.mapper.GroupMapper;
import goal.mapper.GroupUserMapper;
import goal.vo.BoardFileVO;
import goal.vo.GroupDataVO;
import goal.vo.GroupGoalVO;
import goal.vo.GroupJoinVO;
import goal.vo.GroupUserGoalVO;
import goal.vo.GroupUserVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Service
@Component
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;

	@Autowired
	private GroupUserMapper groupUserMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardFileMapper boardFileMapper;
	
	@Override
	public void createGroup(GroupVO group, GroupUserVO groupUser) {
		groupMapper.createGroup(group);
		groupUser.setGno(group.getGno());
		groupUser.setG_role("ROLE_ADMIN");
		groupUserMapper.insertGroupUser(groupUser);
	}

	@Override
	public List<GroupVO> getGroupList(UserVO user) {
		return groupMapper.selectGroupList(user);
	}

	@Override
	public List<GroupVO> getAllList() {
		return groupMapper.allList();
	}

	@Override
	public GroupVO recentGroup() {
		return groupMapper.recentGroup();
	}

	@Override
	public List<GroupVO> getSearchList(String g_cate) {
		return groupMapper.selectSearchList(g_cate);
	}

	@Override
	public boolean removeGroup(int gno) {
		return groupMapper.removeGroup(gno)>0 ? true : false;
	}
	
	

	@Override
	public int findGnobyName(String g_name) {
		return groupMapper.findGnobyName(g_name);
	}

	@Override
	public void insertGoal(GroupGoalVO groupGoal) {
		groupMapper.insertData(groupGoal);
	}

	@Override
	public List<GroupGoalVO> getGoalbyId(int gno) {
		return groupMapper.findGoalbyId(gno);
	}

	@Override
	public GroupVO getGroup(int gno) {
		return groupMapper.selectGroup(gno);
	}

	@Override
	public List<GroupUserVO> findUserbyGroup(int gno) {
		return groupUserMapper.findUserbyGroup(gno);
	}

	@Override
	public int countUserbyGroup(int gno) {
		return groupUserMapper.countUserbyGroup(gno);
	}

	@Override
	public int countGoalbyGroup(int gno) {
		return groupMapper.countGoalbyId(gno);
	}

	@Override
	public void insertGroupJoin(GroupJoinVO join) {
		groupMapper.insertGroupJoin(join);
	}
	
	@Override
	public void updateBgiCheck(int gno) {
		groupMapper.updateBgiCheck(gno);
	}

	@Override
	public List<GroupJoinVO> getGroupJoin(int gno) {
		return groupMapper.selectGroupJoin(gno);
	}
	
	

	@Override
	public GroupJoinVO selectGroupJoinUno(int uno) {
		return groupMapper.selectGroupJoinUno(uno);
	}

	@Override
	public int checkUserbyGroup(GroupUserVO groupUser) {
		return groupUserMapper.checkUserbyGroup(groupUser);
	}

	@Override
	public String getRolebyUser(GroupUserVO groupUser) {
		return groupUserMapper.findRolebyUser(groupUser);
	}

	@Override
	public void insertGroupUser(GroupUserVO groupUser) {
		groupUserMapper.insertGroupUser(groupUser);
	}

	@Override
	public void removeGroupJoin(GroupJoinVO join) {
		groupMapper.removeGroupJoin(join);
	}

	@Override
	public List<BoardFileVO> findFilebyGroup(GroupVO group) {
		return boardFileMapper.selectFilebyGroup(group);
	}

	@Override
	public void insertGroupData(GroupDataVO data) {
		groupMapper.insertGroupData(data);
	}

	@Override
	public GroupDataVO countDatabyUno(GroupDataVO data) {
		return groupMapper.countDatabyUno(data);
	}

	@Override
	public int countDatabyGno(int gno) {
		return groupMapper.countDatabyGno(gno);
	}

	@Override
	public List<GroupVO> findGroupbyUno(int uno) {
		return groupMapper.findGroupbyUno(uno);
	}

	@Override
	public List<GroupUserGoalVO> findDatabyDno(GroupDataVO data) {
		return groupMapper.findDatabyDno(data);
	}
	
}
