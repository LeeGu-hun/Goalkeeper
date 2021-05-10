package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goal.common.GroupFileUtils;
import goal.mapper.BoardMapper;
import goal.mapper.GroupMapper;
import goal.mapper.GroupUserMapper;
import goal.vo.GroupGoalVO;
import goal.vo.GroupUserNameVO;
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
	
	@Override
	public void createGroup(GroupVO group, GroupUserVO groupUser) {
		groupMapper.createGroup(group);
		groupUser.setGno(group.getGno());
		groupUser.setG_role("ROLE_ADMIN");
		groupUserMapper.insertGroupUser(groupUser);
	}

	@Override
	public List<GroupVO> selectGroupList(UserVO user) {
		return groupMapper.selectGroupList(user);
	}

	@Override
	public List<GroupVO> allList() {
		return groupMapper.allList();
	}

	@Override
	public GroupVO recentGroup() {
		return groupMapper.recentGroup();
	}

	@Override
	public List<GroupVO> selectSearchList(String g_cate) {
		return groupMapper.selectSearchList(g_cate);
	}

	@Override
	public boolean removeGroup(int gno) {
		return groupMapper.removeGroup(gno)>0 ? true : false;
	}
	
	

	@Override
	public void insertGoal(GroupGoalVO groupGoal) {
		groupMapper.insertData(groupGoal);
	}

	@Override
	public List<GroupGoalVO> findGoalbyId(int gno) {
		return groupMapper.findGoalbyId(gno);
	}

	@Override
	public GroupVO selectGroup(int gno) {
		return groupMapper.selectGroup(gno);
	}

	@Override
	public GroupUserNameVO fineUserbyGroup(int gno) {
		return groupUserMapper.fineUserbyGroup(gno);
	}

	@Override
	public int countUserbyGroup(int gno) {
		return groupUserMapper.countUserbyGroup(gno);
	}

	@Override
	public int countGoalbyGroup(int gno) {
		return groupMapper.countGoalbyId(gno);
	}

	
}
