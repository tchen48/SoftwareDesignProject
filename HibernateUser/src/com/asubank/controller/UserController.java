package com.asubank.controller;

import com.asubank.model.User;
import com.asubank.model.TestUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("userController")
public class UserController {
	
	@RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {		
        return "login";
    }
	
	@RequestMapping("/home")
	public ModelAndView home(@ModelAttribute("user") User user, Model model){
		System.out.println("credential: " + user.getStrID() + " " + user.getPassword());
		user = TestUser.validate(user.getStrID(), user.getPassword());
		
		if(user == null){
			return new ModelAndView("login","ErrorMsg","Incorrect ID/Password");
		}
		
		return new ModelAndView("home","strID",user.getStrID());
	}
	
	@RequestMapping("/userinfo")
    public String userInfo(Model model) {
		User user = new User();
        model.addAttribute("user", user);
        return "userinfo";
    }
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("user") User user, Model model) {
        String message = "";    	
    	message = TestUser.createUser(user.getUserID(),user.getFirstname(),user.getLastname(),user.getDeptID(), user.getRoleID(),
    			user.getAccno(),user.getAmount(),user.getCardno(), user.getPinno(), user.getPassword());
        model.addAttribute("message", message);
        return "result";
    }
	
	@RequestMapping("/userlist")
    public ModelAndView userList(Model model) {
		System.out.print("b");
		List<User> list = TestUser.queryUser();
        model.addAttribute("message", list);
        return new ModelAndView("userlist", "list", list);
    }
}
