package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.UserBackFileMapper;
import goal.vo.UserBackVO;

@Service
public class UserBackFileServiceImpl implements UserBackFileService{
	@Autowired
	private UserBackFileMapper mapper;

	@Override
	public void insertUserBackFile(UserBackVO vo) {
		mapper.insertUserBackFile(vo);
	}

	@Override
	public List<UserBackVO> selectBackFileName() {
		List<UserBackVO> backFile = mapper.selectBackFileName();
		return backFile;
	}

	@Override
	public int removeBackFile(int uno) {
		return mapper.removeUserBackFile(uno);
	}

	@Override
	public UserBackVO selectBackFile(int uno) {
		return mapper.selectBackFile(uno);
	}

	@Override
	public int checkUserBack(int uno) {
		return mapper.checkUserBack(uno);
	}
}
