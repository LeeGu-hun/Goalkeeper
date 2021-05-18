package goal.service;

import java.util.List;
import java.util.Map;

import goal.vo.ReactVO;

public interface ReactService {
	void insertReact(ReactVO react);
	void updateReact(ReactVO react);
	void deleteReact(ReactVO react);
	ReactVO findReactbyUser(ReactVO react);
	List<ReactVO> findReactbyBno(ReactVO react);
}
