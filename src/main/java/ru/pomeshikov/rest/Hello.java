package ru.pomeshikov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.dao.UserDao;
import ru.pomeshikov.model.User;

@Controller
@RequestMapping("/hello")
public class Hello {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="world", method=RequestMethod.GET)
	public @ResponseBody User world(){
		userDao.add(new User());
		return null;
	}
}
