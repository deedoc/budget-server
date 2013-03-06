package ru.pomeshikov.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.BaseTest;
import ru.pomeshikov.model.User;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	public void test() {
		userDao.add(new User());
	}

}
