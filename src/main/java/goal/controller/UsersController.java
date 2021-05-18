package goal.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import goal.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UsersController {
	
	@GetMapping("/chathome")
	public ModelAndView openNewsFeed() {
		log.info("@@@@@@@@@@@@@@@@@@@@");
		ModelAndView mv = new ModelAndView("view/home/index");
		
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
