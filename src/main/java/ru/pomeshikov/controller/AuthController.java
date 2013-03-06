package ru.pomeshikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import ru.pomeshikov.dao.UserDao;
import ru.pomeshikov.model.User;

@Controller
public class AuthController {
	
	@Autowired
	private UserDao userDao;

	public String register(User user) {
		user.setPassword(toMD5(user.getPassword()));
		user.setUkey(toMD5(user.getEmail() + ":" + user.getPassword()));
		userDao.add(user);
		return user.getUkey();
	}

	public String login(User user) {
		/**
		 * Тут зафигачить поиск по имейлу и хэшу пароля
		 * и вернуть юкей
		 */
		return null;
	}
	
	private String toMD5(String s){
		return DigestUtils.md5DigestAsHex(s.getBytes());
	}
	
}
