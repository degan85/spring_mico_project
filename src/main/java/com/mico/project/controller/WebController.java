package com.mico.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mico.project.domain.User;
import com.mico.project.service.SecurityService;
import com.mico.project.service.UserService;

@Controller
public class WebController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value="/main")
	public String main(){
	  return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model model, String error, String logout, HttpServletRequest request ){
	  if (logout != null){
	    model.addAttribute("logout", "You have been logged out successfully.");
	  }
	  return "login";
	}
	
	@RequestMapping(value="/loginError")
	public String loginError(Model model, String username ){
	  model.addAttribute("error", "Your username and password is invalid.");
	  model.addAttribute("username",username);
	  return "login";
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String registration(Model model){
	  model.addAttribute("userForm", new User());
	  return "registration";
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, 
			  Model model ,String[] roles ){
	  String password = userForm.getPassword();
	  
	  logger.info("@@@ LOGGER : "+userForm.toString());
	  
	  boolean USERNAME_ALREADY_EXISTS = userService.findByUsername(userForm.getUsername()) !=null;
	  boolean EMAIL_ALREADY_EXISTS = userService.findByEmail(userForm.getEmail()) !=null;
	  
	  if(!USERNAME_ALREADY_EXISTS && !EMAIL_ALREADY_EXISTS) {
		  userService.saveUser(userForm,roles);
	  }else {
		  model.addAttribute("error","already exists");
		  return "registration";
	  }
	  
	  securityService.autologin(userForm.getUsername(),password);
	  return "redirect:/home";
	}
	
	@RequestMapping("/admin")
	public String admin(){
	  return "/admin/admin";
	}
	
	@RequestMapping("/user")
	public String user(){
	  return "/user/user";
	}
	
	@RequestMapping("/403")
	public String access(){
	  return "/access";
}
}
