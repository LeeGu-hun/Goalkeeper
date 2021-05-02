package goal.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import goal.mapper.UserMapper;
import goal.vo.UserVO;

@Repository
public class UserRepository {
	
	@Autowired
	private UserMapper userMapper;
	
	private Map<String, UserVO> user = new HashMap<String, UserVO>();
	
	public UserVO save(UserVO user) {
		this.user.put(user.getU_id(), user);
		return user;
	}

	public UserVO findById(String u_id) {
		return user.get(u_id);
		
	}
}
