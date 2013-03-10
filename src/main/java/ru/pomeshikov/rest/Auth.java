package ru.pomeshikov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.AuthController;

@Service
@RequestMapping("/auth")
public class Auth {
	
	@Autowired
	AuthController authController;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody String register(@RequestParam String email, @RequestParam String password){
		return authController.register(email, password);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String email, @RequestParam String password){
		return authController.login(email, password);
	}
}
