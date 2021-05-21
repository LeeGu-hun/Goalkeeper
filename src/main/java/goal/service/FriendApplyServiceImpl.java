package goal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.FriendApplyMapper;
import goal.vo.FriendApplyVO;
import goal.vo.FriendVO;

@Service
public class FriendApplyServiceImpl implements FriendApplyService{

	@Autowired
	private FriendApplyMapper mapper;
	
	@Override
	public int apply(FriendApplyVO apply) {
		return mapper.apply(apply);
	}
	
	@Override
	public List<FriendApplyVO> receiveList(int uno) {
		return mapper.receiveList(uno);
	}
	
	@Override
	public List<FriendApplyVO> applyList(int uno) {
		return mapper.applyList(uno);
	}

	@Override
	public void acceptFriend(FriendVO friend) {
		mapper.acceptFriend(friend);
	}

	@Override
	public void rejectFriend(Map<String, Object> map) {
		mapper.rejectFriend(map);
	}

	@Override
	public void applyCancel(Map<String, Object> map) {
		mapper.applyCancel(map);
	}

	@Override
	public void applyFileCheck(int receiveUno) {
		mapper.applyFileCheck(receiveUno);
	}

	@Override
	public void applyBackCheck(int receiveUno) {
		mapper.applyBackCheck(receiveUno);
	}

	@Override
	public void receiveFileCheck(int applyUno) {
		mapper.receiveFileCheck(applyUno);
	}

	@Override
	public void receiveBackCheck(int applyUno) {
		mapper.receiveBackCheck(applyUno);
	}

}
