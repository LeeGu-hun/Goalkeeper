package goal.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.upload.BoardUpload;
import goal.vo.BoardVO;
import goal.vo.ReactVO;
import goal.vo.UserVO;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardUpload boardUpload;
	@Autowired
	private ReactService reactService;
	
	@Override
	public ModelAndView checkLoginUser(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		if(user != null) {
			mv.addObject("user", user);
		} else {
			mv.addObject("user", null);
		}
		return mv;
	}

	@Override
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (UserVO) session.getAttribute("user");
	}

	@Override
	public void fileCheck(BoardVO board, String fileCheck, List<MultipartFile> files) {
		int fileCnt;
		if(fileCheck.equals("false")) {
			board.setBo_fileCheck("Y");
			boardService.insertBoard(board);
			try {
				fileCnt = boardUpload.BoardUpload(board, files);
				board.setBo_fileCount(fileCnt);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			boardService.updateBoard(board);
		} else {
			board.setBo_fileCheck("N");
			boardService.insertBoard(board);
		}
	}

	@Override
	public ResponseEntity<BoardVO> react(ReactVO react) {
		ReactVO reactCheck = reactService.findReactbyUser(react);
		BoardVO preBoardList = boardService.findBoardbyBno(react.getBno());
		if(reactCheck == null) {
			reactService.insertReact(react);
		} else {
			reactService.deleteReact(react);
		} 
		BoardVO boardList = boardService.findBoardbyBno(react.getBno());
		if(boardList.getReactList() == null) {
			boardList.setReactCount(0);
		} 
		if(reactCheck!=null) {
			boardList.setReactType(boardList.getReactCount()-preBoardList.getReactCount());
		}
		return new ResponseEntity<BoardVO>(boardList,HttpStatus.OK);
	}	
	
}
