package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupBgiVO;
import goal.vo.GroupFileVO;

@Mapper
public interface GroupFileMapper {
	void insertGroupFile(GroupFileVO groupFile);
	void insertGroupBgi(GroupBgiVO groupBgi);
	void updateGroupFile(GroupFileVO groupFile);
	void updateGroupBgi(GroupBgiVO groupBgi);
	List<GroupFileVO> selectFileName();
	int removeGroupFile(int gno);
	int checkFilebyGno(int gno);
	int checkBgibyGno(int gno);
	GroupFileVO selectFile(int gno);
	GroupBgiVO selectBgi(int gno);
}
