package goal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.GroupService;
import goal.upload.BoardUpload;
import goal.vo.BoardVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private GroupService groupService;
	
	private BoardUpload boardUpload = new BoardUpload();
	
	@RequestMapping("/boardWrite")
	public ModelAndView openBoardWrite(ModelAndView mv) {
		mv.setViewName("view/board/board_write");
		UserVO user = new UserVO();
		BoardVO board = new BoardVO();
		board.setUno(2);
		
		user.setUserId("kjm");
		List<GroupVO> groupList = getGroupList(user);
		mv.addObject("List", groupList);
		return mv;
	}
	
	@PostMapping("/board/insert_board.do")
	public String insertBoard(BoardVO board, MultipartHttpServletRequest multi) throws Exception {
		
		UserVO user = new UserVO();
		user.setUserId("kjm");
		board.setUno(2);
		boardService.insertBoard(board);
		BoardVO recentBoard = boardService.recentBoard();
		
		/* 사진저장
		 * List<BoardFileVO> boardFile = null; for(BoardFileVO file : boardFile) {
		 * file.setBno(recentBoard.getBno()); } boardFile =
		 * boardUpload.requestMultiUpload(multi);
		 */
		return "redirect:/boardWrite";
		//임시로 지정 
	}
	
	@RequestMapping("/boardSearch")
	public ModelAndView searchBoard(ModelAndView mv) {
		mv.setViewName("view/board/board_search");
		UserVO user = new UserVO();
		BoardVO board = new BoardVO();
		board.setUno(2);
		
		
		user.setUserId("kjm");
		List<BoardVO> boardlist = getBoardList(user);
		mv.addObject("List", boardlist);
		return mv;
	}
	
	private List<GroupVO> getGroupList(UserVO user){
		user.setUserId("kjm");
		BoardVO board = new BoardVO();
		board.setUno(2);
		
		List<GroupVO> groupList = groupService.selectGroupList(user);
		return groupList;
	}
	
	private List<BoardVO> getBoardList(UserVO user){
		user.setUserId("kjm");
		BoardVO board = new BoardVO();
		board.setUno(2);
		
		List<BoardVO> boardList = null;
		return boardList;
	}

}
