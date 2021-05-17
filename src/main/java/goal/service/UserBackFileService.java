package goal.service;

import java.util.List;

import goal.vo.UserBackVO;

public interface UserBackFileService {
	void insertUserBackFile(UserBackVO vo);
	List<UserBackVO> selectBackFileName();
	int removeBackFile(int uno);
	UserBackVO selectBackFile(int uno);
	int checkUserBack(int uno);
}
