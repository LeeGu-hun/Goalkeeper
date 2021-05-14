package goal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.UserFileMapper;
import goal.vo.GroupFileVO;
import goal.vo.UserFileVO;

@Service
public class UserFileServiceImpl implements UserFileService{
	@Autowired
	private UserFileMapper mapper;

	@Override
	public void insertUserFile(UserFileVO vo) {
		mapper.insertUserFile(vo);
	}
	@Override
	public List<UserFileVO> selectFileName() {
		List<UserFileVO> userFile = mapper.selectFileName();
		return userFile;
	}

	@Override
	public int removeUserFile(int uno) {
		return mapper.removeUserFile(uno);
	}
	
	@Override
	public UserFileVO selectFile(int uno) {
		return mapper.selectFile(uno);
	}
	@Override
	public int checkProfile(int uno) {
		return mapper.checkProfile(uno);
	}

}
