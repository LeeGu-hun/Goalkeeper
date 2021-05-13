package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.UserProfileMapper;
import goal.vo.UserProfileVO;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	@Autowired
	private UserProfileMapper mapper;

	@Override
	public byte[] getProfile(UserProfileVO vo) {
		return mapper.getProfile(vo);
	}
	
}
