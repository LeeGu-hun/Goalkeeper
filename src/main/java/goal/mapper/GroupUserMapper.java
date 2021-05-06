package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupUserNameVO;
import goal.vo.GroupUserVO;

@Mapper
public interface GroupUserMapper {
	void insertGroupUser(GroupUserVO groupUser);
	GroupUserNameVO fineUserbyGroup(int gno);
	int countUserbyGroup(int gno);
}
