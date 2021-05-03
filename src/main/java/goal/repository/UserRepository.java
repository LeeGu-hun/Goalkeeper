package goal.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import goal.vo.UserVO;

@Repository
public class UserRepository {
	private Map<String, UserVO> users = new HashMap<String, UserVO>();
	
	public UserVO save(UserVO user) {
		users.put(user.getUserId(), user);
		return user;
	}
	public UserVO findById(String userId) {
		return users.get(userId);
	}
}
