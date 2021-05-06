package goal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.GroupService;
import goal.service.UserService;
import goal.upload.BoardUpload;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private BoardFileService boardFileService;

	private BoardUpload boardUpload = new BoardUpload();
	private String referer = null;

	@RequestMapping("/boardWrite")
	public ModelAndView openBoardWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/board/board_write");
		UserVO user = getLoginUser(request);
		if (user != null) {
			mv.addObject("user", user);
			List<GroupVO> groupList = groupService.selectGroupList(user);
			mv.addObject("List", groupList);
		} else {
			referer = request.getHeader("REFERER");
			mv.setViewName("view/home/user_login");
		}
		return mv;
	}

	@PostMapping("/board/insert_board.do")
	public String insertBoard(
			BoardVO board, HttpServletRequest request, MultipartHttpServletRequest multi)
			throws Exception {
		UserVO user = new UserVO();
		List<BoardFileVO> boardFile = new ArrayList();
		user = getLoginUser(request);

		board.setUserId(user.getUserId());
		board.setUno(user.getUno());

		boardService.insertBoard(board);
		BoardVO recentBoard = boardService.recentBoard();
	
		/*
		 * boardFile = boardUpload.requestMultiUpload(multi);
		 * boardFileService.insertBoardFile(boardFile);
		 */
	return "redirect:/home";
		// 임시로 지정
	}

	@RequestMapping("/boardSearch")
	public ModelAndView searchBoard(BoardVO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/board/board_search");
		UserVO user = getLoginUser(request);
		if (user != null) {
			mv.addObject("user", user);
			List<BoardVO> boardlist = boardService.searchBoard(vo);
			mv.addObject("List", boardlist);
		} else {
			mv.setViewName("view/error/denied");
		}
		return mv;
	}

	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		return user;
	}

}
