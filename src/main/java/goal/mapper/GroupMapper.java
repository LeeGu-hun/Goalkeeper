package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupFileVO;
import goal.vo.GroupVO;

@Mapper
public interface GroupMapper {
	void createGroup(GroupVO group);
	List<GroupFileVO> insertGroupFile(List<GroupFileVO> groupFile);
	List<GroupVO> selectGroupList();
}
