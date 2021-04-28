package goal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.GroupListVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

public interface GroupService {
	void createGroup(GroupVO group);
	List<GroupVO> selectGroupList(UserVO user);
	List<GroupVO> allList();
	GroupVO recentGroup();
	List<GroupVO> selectSearchList(String g_cate);
	boolean removeGroup(int gno);
}
