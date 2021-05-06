package goal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import goal.vo.UserVO;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public ModelAndView checkLoginUser(HttpServletRequest request, ModelAndView mv) {
		UserVO user = new UserVO();
		HttpSession session = request.getSession(true);
	    user = (UserVO) session.getAttribute("user");
		if(user != null) {
			mv.addObject("user", user);
		} else {
			mv.addObject("user", null);
		}
		return mv;
	}
	
}
