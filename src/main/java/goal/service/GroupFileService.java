package goal.service;

import java.util.List;

import goal.vo.GroupBgiVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupVO;

public interface GroupFileService {
	void insertGroupFile(GroupFileVO groupFile);
	void insertGroupBgi(GroupBgiVO groupBgi);
	void updateGroupFile(GroupFileVO groupFile);
	void updateGroupBgi(GroupBgiVO groupBgi);
	List<GroupFileVO> selectFileName();
	boolean removeGroupFile(int gno);
	int checkFilebyGno(int gno);
	int checkBgibyGno(int gno);
	GroupFileVO selectFile(int gno);
	GroupBgiVO selectBgi(int gno);
}
