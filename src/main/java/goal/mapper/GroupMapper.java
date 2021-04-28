package goal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupFileVO;
import goal.vo.GroupListVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Mapper
public interface GroupMapper {
	void createGroup(GroupVO group);
	List<GroupFileVO> insertGroupFile(List<GroupFileVO> groupFile);
	List<GroupVO> selectGroupList(UserVO vo);
	List<GroupVO> allList();
	List<GroupVO> selectSearchList(String g_cate);
	int removeGroup(int gno);
}
