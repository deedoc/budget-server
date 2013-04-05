package ru.pomeshikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import ru.pomeshikov.dao.UserDao;
import ru.pomeshikov.model.UserDO;

@Controller
public class AuthController {
	
	@Autowired
	private UserDao userDao;

	@Transactional
	public String register(String email, String password) {
		UserDO user = new UserDO();
		user.setEmail(email);
		user.setPassword(toMD5(password));
		user.setUkey(toMD5(email + ":" + password));
		userDao.save(user);
		return user.getUkey();
	}

	@Transactional
	public String login(String email, String password) {
		return userDao.findByEmailAndPassword(email, toMD5(password)).getUkey();
	}
	
	@Transactional
	public String login(String ukey){
		return userDao.findByUkey(ukey).getUkey();
	}
	
	private String toMD5(String s){
		return DigestUtils.md5DigestAsHex(s.getBytes());
	}
	
}
