package goal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.common.CommonDownload;
import goal.service.BoardFileService;
import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.GroupService;
import goal.service.ReplyService;
import goal.service.UserService;
import goal.util.MediaUtils;
import goal.vo.BoardFileVO;
import goal.vo.BoardVO;
import goal.vo.GroupVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Controller
public class BoardController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private BoardFileService boardFileService;
	@Autowired
	private ReplyService replyService;

	private String referer = null;
	private CommonDownload CommonDownload = new CommonDownload();

	@PostMapping("/home/insert.do")
	public String insertBoard(@RequestParam String fileCheck,
			BoardVO board, HttpServletRequest request, @RequestPart("files") List<MultipartFile> files)
			throws Exception {
		UserVO user = new UserVO();
		user = getLoginUser(request);

		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		
		if(!board.getBo_cate().equals("group")) {
			board.setBo_group("noGroup");
		}
		commonService.fileCheck(board, fileCheck, files);
	
	return "redirect:/home";
	}
	
	
	@PostMapping("/board/modify.do")
	public String modifyBoard(@RequestParam String placeCheck, BoardVO board, HttpServletRequest request) {
		UserVO user = commonService.getLoginUser(request);
		
		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		board.setBno(board.getBno());
		boardService.updateBoard(board);
		if(placeCheck.equals("board")) {
		return "redirect:/home";
		} else {
			int gno = groupService.findGnobyName(board.getBo_group());
			return "redirect:/group_detail/" + gno;
		}
	}
	
	@PostMapping("/board/delete.do")
	public String deleteBoard(BoardVO board, HttpServletRequest request) {
		UserVO user = commonService.getLoginUser(request);
		
		board.setUserId(user.getUserId());
		board.setUno(user.getUno());
		board.setBno(board.getBno());
		boardService.deleteBoard(board);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/replylist") //댓글 리스트
    @ResponseBody
    private List<ReplyVO> replyList(Model model) throws Exception{
        return replyService.getMainReply();
    }
	
	@RequestMapping("/replyinsert") //댓글 작성 
    @ResponseBody
    private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content, HttpServletRequest request) throws Exception{
		UserVO user = commonService.getLoginUser(request);
		
        ReplyVO reply = new ReplyVO();
        reply.setBno(bno);
        reply.setReplyContent(content);
        reply.setReplyWriter(user.getUserId());  
        
        return replyService.insertReply(reply);
    }
	
	
	@RequestMapping(value = "/boardDisplay/{bno}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayImage(@PathVariable int bno) throws IOException {
		MediaUtils mediaUtils = new MediaUtils();
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		BoardFileVO boardFile = boardFileService.selectFile(bno);
		entity=CommonDownload.getImageEntity(entity, mediaUtils, in, boardFile.getFileName(), boardFile.getUuid(), boardFile.getFileUrl());
		return entity;
	}
	
	
	public UserVO getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserVO user = (UserVO) session.getAttribute("user");
		return user;
	}

}
