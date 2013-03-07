package ru.pomeshikov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.AuthController;
import ru.pomeshikov.model.UserDO;

@Service
@RequestMapping("/auth")
public class Auth {
	
	@Autowired
	AuthController authController;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody String register(@RequestBody UserDO user){
		return authController.register(user);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody UserDO user){
		return authController.login(user);
	}
}
