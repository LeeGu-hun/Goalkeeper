package goal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import goal.mapper.ReactMapper;
import goal.vo.ReactVO;

@Service
public class ReactServiceImpl implements ReactService{
	@Autowired
	private ReactMapper reactMapper;
	
	@Override
	public void insertReact(ReactVO react) {
		reactMapper.insertReact(react);
	}
	

	@Override
	public void updateReact(ReactVO react) {
		reactMapper.updateReact(react);
	}


	@Override
	public void deleteReact(ReactVO react) {
		reactMapper.deleteReact(react);
	}


	@Override
	public ReactVO findReactbyUser(ReactVO react) {
		return reactMapper.findReactbyUser(react);
	}

	@Override
	public List<ReactVO> findReactbyBno(ReactVO react) {
		return reactMapper.findReactbyBno(react);
	}

}
