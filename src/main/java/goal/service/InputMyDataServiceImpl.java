package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.InputMyDataMapper;
import goal.vo.InputMyDataVO;

@Service
public class InputMyDataServiceImpl implements InputMyDataService{

	@Autowired
	private InputMyDataMapper inputMyDataMapper;
	
	@Override
	public void inputData(InputMyDataVO vo) {
		inputMyDataMapper.inputData(vo);
	}

}
