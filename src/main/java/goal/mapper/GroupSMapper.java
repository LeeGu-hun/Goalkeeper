package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupSVO;

@Mapper
public interface GroupSMapper {
	void insertGroupUser(GroupSVO groupUser);
	int fineUserbyId(int gno);
}
