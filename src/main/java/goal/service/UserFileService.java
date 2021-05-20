package goal.service;

import java.util.List;

import goal.vo.UserFileVO;

public interface UserFileService {
	void insertUserFile(UserFileVO vo);
	List<UserFileVO> selectFileName();
	int removeUserFile(int uno);
	UserFileVO selectFile(int uno);
	int checkProfile(int uno);
	UserFileVO selectFilebyId(String userId);
}
