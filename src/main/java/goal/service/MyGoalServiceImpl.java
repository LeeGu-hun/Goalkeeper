package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.MyGoalMapper;
import goal.vo.MyGoalVO;

@Service
public class MyGoalServiceImpl implements MyGoalService{

	@Autowired
	public MyGoalMapper myGoalMapper;
	
	@Override
	public void createGoal(MyGoalVO vo) {
		myGoalMapper.createGoal(vo);
	}
	
}
