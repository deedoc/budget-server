package ru.pomeshikov.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.BaseTest;
import ru.pomeshikov.model.User;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	@Rollback
	public void test() {
		User user = new User();
		user.setEmail("test");
		user.setPassword("testPwd");
		userDao.add(user);
	}

}
