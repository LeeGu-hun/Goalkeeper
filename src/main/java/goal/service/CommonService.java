package goal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import goal.vo.UserVO;

public interface CommonService {
	ModelAndView checkLoginUser(HttpServletRequest request, ModelAndView mv);
	UserVO getLoginUser(HttpServletRequest request);
}
