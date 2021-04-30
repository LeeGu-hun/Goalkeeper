package goal.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.GroupService;
import goal.upload.BoardUpload;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.GroupFileVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private GroupService groupService;
	
	private BoardUpload boardUpload = new BoardUpload();
	
	@RequestMapping(value="/boardWrite")
	public ModelAndView openBoardWrite(ModelAndView mv) {
		mv.setViewName("view/board/boardWrite");
		UserVO user = new UserVO();
		user.setUno(2);
		List<GroupVO> groupList = getGroupList(user);
		mv.addObject("List", groupList);
		return mv;
	}
	
	@PostMapping(value="/board/insert_board.do")
	public String insertBoard(BoardVO board, MultipartHttpServletRequest multi) throws Exception {
		List<BoardFileVO> boardFile = null;
		UserVO user = new UserVO();
		user.setUno(2);
		board.setUno(user.getUno());
		boardService.insertBoard(board);
		BoardVO recentBoard = boardService.recentBoard();
		
		for(BoardFileVO file : boardFile) {
			file.setBno(recentBoard.getBno());
		}
		boardFile = boardUpload.requestMultiUpload(multi);
		
		return "redirect:/boardWrite";
		//임시로 지정 
	}
	
	private List<GroupVO> getGroupList(UserVO user){
		user.setUno(2);
		List<GroupVO> groupList = groupService.selectGroupList(user);
		return groupList;
	}

}
