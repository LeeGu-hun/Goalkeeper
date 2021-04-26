package goal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.dto.BoardDto;
import goal.service.BoardService;
import goal.vo.UserVO;

@Controller
public class BoardController {
	
	//@Autowired
//	private BoardService boardService;

	@RequestMapping("/boardWrite")
	public ModelAndView openBoardWrite(@ModelAttribute UserVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("/view/board/boardWrite");

		// BoardDto board = BoardService.selectBoardDetail
		return mv;
	}
	
	@RequestMapping("/board/insert_board.do")
	public String insertBoard(BoardDto board, 
			MultipartHttpServletRequest multi) throws Exception {
		boardService.insertBoard(board, multi);
		return "redirect:/board/openBoardList.do";
	}

}
