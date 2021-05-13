package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.InputMyDataVO;

@Mapper
public interface InputMyDataMapper {
	void inputData(InputMyDataVO vo);
}
