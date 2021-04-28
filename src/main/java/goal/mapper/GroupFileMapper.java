package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupFileVO;

@Mapper
public interface GroupFileMapper {
	void insertGroupFile(GroupFileVO groupFile);
}
