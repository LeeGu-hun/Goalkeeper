package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupUserVO;

@Mapper
public interface GroupUserMapper {
	void insertGroupUser(GroupUserVO groupUser);
	List<GroupUserVO> findUserbyGroup(int gno);
	int countUserbyGroup(int gno);
	int checkUserbyGroup(GroupUserVO groupUser);
	String findRolebyUser(GroupUserVO groupUser);
}
