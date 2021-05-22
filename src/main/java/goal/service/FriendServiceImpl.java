package goal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import goal.mapper.FriendMapper;
import goal.vo.FriendVO;
import goal.vo.UserFileVO;

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
	public boolean remove(Map<String, Object> map) {
		return mapper.deleteFriend(map) > 0 ? true : false;
	}

	@Override
	public List<FriendVO> findMyFriend(Map<String, Object> map) {
		return mapper.findMyFriend(map);
	}

	@Override
	public int countFriends(int uno) {
		return mapper.countFriend(uno);
	}

	@Override
	public void profileCheck(int uno) {
		mapper.profileCheck(uno);
	}

	@Override
	public void profileBackCheck(int uno) {
		mapper.profileBackCheck(uno);
	}
	
}
