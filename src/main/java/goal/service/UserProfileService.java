package goal.service;

import goal.vo.UserProfileVO;

public interface UserProfileService {
	byte[] getProfile(UserProfileVO vo);
}
