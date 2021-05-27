package goal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import goal.vo.BoardVO;
import goal.vo.ReactVO;
import goal.vo.UserVO;

public interface CommonService {
	ModelAndView checkLoginUser(HttpServletRequest request, ModelAndView mv);
	UserVO getLoginUser(HttpServletRequest request);
	void fileCheck(BoardVO board, String fileCheck, List<MultipartFile> files);
	ModelAndView getChatFriend(UserVO user, ModelAndView mv);
}
