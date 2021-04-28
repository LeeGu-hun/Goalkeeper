package goal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import goal.service.BoardService;
import goal.service.GroupService;
import goal.vo.BoardVO;
import goal.vo.GroupVO;
import goal.vo.UserVO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/boardWrite")
	public ModelAndView openBoardWrite(ModelAndView mv) {
		mv.setViewName("view/board/boardWrite");
		UserVO user = new UserVO();
		user.setUno(2);
		List<GroupVO> groupList = getGroupList(user);
		mv.addObject("List", groupList);
		return mv;
	}
	
	@RequestMapping("/board/insert_board.do")
	public String insertBoard(BoardVO board) throws Exception {
		UserVO user = new UserVO();
		user.setUno(2);
		board.setUno(user.getUno());
		boardService.insertBoard(board);
		return "redirect:/boardWrite";
		//임시로 지정 
	}
	
	private List<GroupVO> getGroupList(UserVO user){
		user.setUno(2);
		List<GroupVO> groupList = groupService.selectGroupList(user);
		return groupList;
	}

}
