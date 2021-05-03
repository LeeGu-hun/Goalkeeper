package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.FriendMapper;
import goal.vo.FriendVO;
import goal.vo.UserVO;

@Service
@Component
public class FriendServiceImpl implements FriendService{

	@Autowired
	private FriendMapper mapper;
	
	@Override
	public List<FriendVO> getFriendsList(FriendVO vo) {
		return mapper.getlistFriend(vo);
	}


	@Override
	public boolean addFriend(FriendVO vo) {
		return mapper.addFriend(vo) > 0 ? true : false;
	}

	@Override
	public boolean remove(int uno, int fno) {
		return mapper.deleteFriend(uno, fno) > 0 ? true : false;
	}
	
}
