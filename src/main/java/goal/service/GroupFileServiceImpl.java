package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.GroupFileMapper;
import goal.vo.GroupFileVO;

@Service
public class GroupFileServiceImpl implements GroupFileService{

	@Autowired
	private GroupFileMapper groupFileMapper;
	
	@Override
	public void insertGroupFile(GroupFileVO groupFile) {
		groupFileMapper.insertGroupFile(groupFile);
	}

	@Override
	public List<GroupFileVO> selectFileName() {
		List<GroupFileVO> groupFile = groupFileMapper.selectFileName();
		return groupFile;
	}

	@Override
	public boolean removeGroupFile(int gno) {
		return groupFileMapper.removeGroupFile(gno)>0 ? true : false;
	}
	
}
