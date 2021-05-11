package goal.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.GroupService;
import goal.service.UserService;
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
			BoardVO board, HttpServletRequest request, @RequestPart("files") List<MultipartFile> files)
			throws Exception {
		UserVO user = new UserVO();
		BoardFileVO boardFileVO = new BoardFileVO();
		user = getLoginUser(request);

		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
			
		boardService.insertBoard(board);
		
	
        for(MultipartFile file : files) {
        	String fileUrl = "C:/uploadfile";
        	String fileName = file.getOriginalFilename(); 
            String uuid = RandomStringUtils.randomAlphanumeric(32)+"."+"jpg";
            String filePath = fileUrl + "/" + uuid;
            File dest = new File(filePath);
            file.transferTo(dest);
            boardFileVO.setUuid(uuid);
            boardFileVO.setBno(board.getBno());
            boardFileVO.setFileName(fileName);
            boardFileVO.setFileUrl(filePath);
            boardFileService.fileInsert(boardFileVO);
	}
	return "redirect:/boardWrite";
	}

	@RequestMapping("/boardSearch")
	public ModelAndView searchBoard(BoardVO vo,BoardFileVO boardFileVO, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/board/board_search");
		UserVO user = getLoginUser(request);
		if (user != null) {
			mv.addObject("user", user);
			List<BoardVO> boardlist = boardService.searchBoard(vo);
			List<BoardFileVO> boardFile = boardFileService.searchFile(boardFileVO);
			boardFileVO.setBno(vo.getBno());
			mv.addObject("List", boardlist);
			mv.addObject("imgList",boardFile);
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
