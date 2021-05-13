package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.MyGoalVO;

@Mapper
public interface MyGoalMapper {
	void createGoal(MyGoalVO vo);
}
