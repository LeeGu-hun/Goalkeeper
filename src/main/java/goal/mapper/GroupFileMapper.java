package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupFileVO;
import goal.vo.GroupVO;

@Mapper
public interface GroupFileMapper {
	void insertGroupFile(GroupFileVO groupFile);
	List<GroupFileVO> selectFileName();
	int removeGroupFile(int gno);
}
