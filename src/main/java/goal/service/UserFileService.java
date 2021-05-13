package goal.service;

import java.util.List;

import goal.vo.UserFileVO;

public interface UserFileService {
	void insertUserFile(UserFileVO vo);
	List<UserFileVO> selectFileName();
	boolean removeUserFile(int uno);
	UserFileVO selectFile(int uno);
}
