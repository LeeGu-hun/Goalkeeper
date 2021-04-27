package goal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import goal.mapper.BoardMapper;
import goal.mapper.GroupDataMapper;
import goal.vo.BoardVO;

@Service
@Component
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void insertBoard(BoardVO vo, MultipartHttpServletRequest multi) {
		boardMapper.insertBoard(vo);
		
	}

}
