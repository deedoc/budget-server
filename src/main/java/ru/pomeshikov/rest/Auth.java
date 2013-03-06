package ru.pomeshikov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.dao.UserDao;
import ru.pomeshikov.model.User;

@Controller
@RequestMapping("/auth")
public class Auth {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * ¬крутить контроллер авторизации
	 * @param user
	 */
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody void register(@RequestParam("user") User user){
		userDao.add(user);
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody String world(@RequestParam("email") String email, @RequestParam("password") String password){
		
		return null;
	}
}
