package goal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.ReactVO;

@Mapper
public interface ReactMapper {
	void insertReact(ReactVO react);
	ReactVO findReactbyUser(ReactVO react);
	List<ReactVO> findReactbyBno(ReactVO react);
	void updateReact(ReactVO react);
	void deleteReact(ReactVO react);
}
