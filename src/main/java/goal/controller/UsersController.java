package goal.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import goal.service.ChatService;
import goal.service.CommonService;
import goal.storage.UserStorage;
import goal.vo.ChatVO;
import goal.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsersController {

	@Autowired
	private CommonService commonService;
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/chathome")
	public ModelAndView openChatHome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("view/home/index");
		 UserVO user = commonService.getLoginUser(request);
		 List<ChatVO> friendlist = chatService.findFriendList(user);
		 mv.addObject("user", user);
		 mv.addObject("friendlist", friendlist);    
		return mv;
	}
	
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
    	log.info(userName);
        System.out.println("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public @ResponseBody Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
