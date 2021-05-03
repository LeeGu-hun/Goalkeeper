package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goal.common.GroupFileUtils;
import goal.mapper.GroupMapper;
import goal.mapper.GroupSMapper;
import goal.vo.GroupGoalVO;
import goal.vo.GroupSVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Service
@Component
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;

	@Autowired
	private GroupSMapper groupsMapper;
	
	@Override
	public void createGroup(GroupVO group, GroupSVO groupUser, GroupGoalVO groupGoal) {
		groupMapper.createGroup(group);
		groupGoal.setGno(group.getGno());
		groupGoal.setGoal_type("A");
		groupUser.setGno(group.getGno());
		groupUser.setG_role("ROLE_ADMIN");
		groupMapper.insertData(groupGoal);
		groupsMapper.insertGroupUser(groupUser);
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
	public int findDatabyId(int gno) {
		return groupMapper.findDatabyId(gno);
	}

	@Override
	public int fineUserbyId(int gno) {
		return groupsMapper.fineUserbyId(gno);
	}
	
}
