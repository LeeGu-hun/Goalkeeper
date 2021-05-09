package goal.service;

import java.util.List;

import goal.vo.UserVO;

public interface SearchFriendService {
	List<UserVO> allUserList(UserVO vo);
	List<UserVO> searchUser(UserVO vo);
}
