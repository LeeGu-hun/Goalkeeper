package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.SearchFriendMapper;
import goal.vo.UserVO;

@Service
public class SearchFriendServiceImpl implements SearchFriendService{

	@Autowired
	public SearchFriendMapper searchFriendMapper;
	
	@Override
	public List<UserVO> allUserList(UserVO vo) {
		return searchFriendMapper.allUser(vo);
	}

}
