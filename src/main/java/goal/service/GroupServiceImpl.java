package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.common.GroupFileUtils;
import goal.mapper.GroupMapper;
import goal.vo.GroupFileVO;
import goal.vo.GroupListVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Service
@Component
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private GroupFileUtils fileUtils;
	
	@Override
	public void createGroup(GroupVO group, MultipartHttpServletRequest multi) {
		groupMapper.createGroup(group);
		
		try {
			List<GroupFileVO> groupFile = fileUtils.parseFileInfo(group.getGno(), multi);
			groupMapper.insertGroupFile(groupFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public List<GroupVO> selectSearchList(String g_cate) {
		return groupMapper.selectSearchList(g_cate);
	}

	@Override
	public boolean removeGroup(GroupVO group) {
		return groupMapper.removeGroup(group)>0 ? true : false;
	}
	
}
