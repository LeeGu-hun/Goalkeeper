package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.UserProfileVO;

@Mapper
public interface UserProfileMapper {
	byte[] getProfile(UserProfileVO vo);
}
