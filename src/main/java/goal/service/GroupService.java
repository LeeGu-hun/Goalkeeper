package goal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.vo.GroupVO;

public interface GroupService {
	void createGroup(GroupVO group, MultipartHttpServletRequest multi);
	List<GroupVO> selectGroupList();
}
