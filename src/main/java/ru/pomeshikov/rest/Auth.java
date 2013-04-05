package ru.pomeshikov.rest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
public class Auth {
	
	@Autowired
	AuthController authController;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody String register(@RequestParam String email, @RequestParam String password){
		return authController.register(email, password);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public void login(HttpServletRequest req, HttpServletResponse resp){
		String ukey = null;
		for(int i = 0; i < req.getCookies().length; i++){
			Cookie cookie = req.getCookies()[i];
			if(cookie.getName().equals("ukey")){
				ukey = cookie.getValue(); 
			}
		}
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
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
