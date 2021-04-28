package goal.service;

import java.util.List;

import goal.vo.GroupFileVO;

public interface GroupFileService {
	void insertGroupFile(GroupFileVO groupFile);
	List<GroupFileVO> selectFileName();
	boolean removeGroupFile(int gno);
}
