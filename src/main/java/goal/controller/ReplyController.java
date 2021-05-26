package goal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import goal.service.BoardService;
import goal.service.CommonService;
import goal.service.ReactService;
import goal.service.ReplyService;
import goal.vo.BoardVO;
import goal.vo.ReCommentVO;
import goal.vo.ReactVO;
import goal.vo.ReplyVO;
import goal.vo.UserVO;

@Controller
public class ReplyController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReactService reactService;
	@RequestMapping(value="/react", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BoardVO> react(@RequestBody ReactVO react){
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
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ReplyVO> reply(ReplyVO reply, HttpServletRequest request){
		UserVO user = commonService.getLoginUser(request);
		reply.setReplyWriter(user.getUserId());
		replyService.insertReply(reply);
		ReplyVO recentReply = replyService.getMainReply();
		return new ResponseEntity<ReplyVO>(recentReply,HttpStatus.OK);
	}
	@RequestMapping(value="/recmt", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ReCommentVO> recmt(ReCommentVO recmtVO, HttpServletRequest request){
		UserVO user = commonService.getLoginUser(request);
		recmtVO.setRecmtWriter(user.getUserId());
		replyService.insertRecmt(recmtVO);
		ReCommentVO recentRecmt = boardService.getMainRecmt();
		return new ResponseEntity<ReCommentVO>(recentRecmt,HttpStatus.OK);
	}
}
