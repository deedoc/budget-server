package ru.pomeshikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import ru.pomeshikov.dao.UserDao;
import ru.pomeshikov.model.User;

@Controller
public class AuthController {
	
	@Autowired
	private UserDao userDao;

	@Transactional
	public String register(User user) {
		user.setPassword(toMD5(user.getPassword()));
		user.setUkey(toMD5(user.getEmail() + ":" + user.getPassword()));
		userDao.add(user);
		return user.getUkey();
	}

	@Transactional
	public String login(User user) {
		return userDao.findByEmailAndPassword(user.getEmail(), toMD5(user.getPassword())).getUkey();
	}
	
	private String toMD5(String s){
		return DigestUtils.md5DigestAsHex(s.getBytes());
	}
	
}
