package goal.service;

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
	
}
