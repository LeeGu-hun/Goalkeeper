package goal.service;

import java.util.List;

import goal.vo.GroupFileVO;
import goal.vo.GroupVO;

public interface GroupFileService {
	void insertGroupFile(GroupVO group, GroupFileVO groupFile);
	List<GroupFileVO> selectFileName();
	boolean removeGroupFile(int gno);
	GroupFileVO selectFile(int gno);
}
