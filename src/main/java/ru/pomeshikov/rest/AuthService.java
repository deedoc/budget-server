package ru.pomeshikov.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.AuthController;

@Service
@RequestMapping("/auth")
public class AuthService {
	
	@Autowired
	AuthController authController;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody String register(@RequestParam String email, @RequestParam String password){
		return authController.register(email, password);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public void login(HttpServletResponse resp, @CookieValue(value="ukey", required=false) String ukey, @RequestParam(required=false) String email, @RequestParam(required=false) String password){		
		if(email != null && password != null){
			ukey = authController.login(email, password);
		}
		if(ukey != null){
			Cookie cookie = new Cookie("ukey", ukey);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else {
			throw new RuntimeException("Not authorized");
		}
	}
}
