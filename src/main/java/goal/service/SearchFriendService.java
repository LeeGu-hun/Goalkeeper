package goal.service;

import java.util.List;
import java.util.Map;

import goal.vo.UserVO;

public interface SearchFriendService {
	List<UserVO> allUserList(UserVO vo);
	List<UserVO> searchUser(Map<String, Object> map);
}
