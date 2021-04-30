package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goal.common.GroupFileUtils;
import goal.mapper.GroupMapper;
import goal.vo.GroupDataVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Service
@Component
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;

	@Override
	public void createGroup(GroupVO group) {
		groupMapper.createGroup(group);
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
	public void insertData(GroupVO group,GroupDataVO groupData) {
		groupData.setGno(group.getGno());
		groupMapper.insertData(groupData);
		
	}
	
}
