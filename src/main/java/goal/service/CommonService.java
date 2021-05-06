package goal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface CommonService {
	ModelAndView checkLoginUser(HttpServletRequest request, ModelAndView mv);
}
