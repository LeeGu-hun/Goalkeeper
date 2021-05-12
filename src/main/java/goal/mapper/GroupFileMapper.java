package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupFileVO;

@Mapper
public interface GroupFileMapper {
	void insertGroupFile(GroupFileVO groupFile);
	List<GroupFileVO> selectFileName();
	int removeGroupFile(int gno);
	GroupFileVO selectFile(int gno);
}
