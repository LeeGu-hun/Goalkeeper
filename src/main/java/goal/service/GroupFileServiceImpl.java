package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.GroupFileMapper;
import goal.vo.GroupBgiVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupVO;

@Service
public class GroupFileServiceImpl implements GroupFileService{

	@Autowired
	private GroupFileMapper groupFileMapper;
	
	@Override
	public void insertGroupFile(GroupFileVO groupFile) {
		groupFileMapper.insertGroupFile(groupFile);
	}
	
	@Override
	public void insertGroupBgi(GroupBgiVO groupBgi) {
		groupFileMapper.insertGroupBgi(groupBgi);
	}
	
	@Override
	public void updateGroupFile(GroupFileVO groupFile) {
		groupFileMapper.updateGroupFile(groupFile);
	}

	@Override
	public void updateGroupBgi(GroupBgiVO groupBgi) {
		groupFileMapper.updateGroupBgi(groupBgi);
	}

	@Override
	public int checkFilebyGno(int gno) {
		return groupFileMapper.checkFilebyGno(gno);
	}
	
	@Override
	public int checkBgibyGno(int gno) {
		return groupFileMapper.checkBgibyGno(gno);
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

	@Override
	public GroupFileVO selectFile(int gno) {
		return groupFileMapper.selectFile(gno);
	}

	@Override
	public GroupBgiVO selectBgi(int gno) {
		return groupFileMapper.selectBgi(gno);
	}
	
}
