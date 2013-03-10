package ru.pomeshikov.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.BaseTest;
import ru.pomeshikov.model.UserDO;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback
	public void testCriteria(){
		UserDO u = new UserDO();
		u.setUkey("key123321yek");
		u.setEmail("t-email");
		u.setPassword("t-password");
		userDao.save(u);
		
		UserDO foundUser = userDao.findByUkey("key123321yek");
		Assert.assertEquals("key123321yek", foundUser.getUkey());
		Assert.assertEquals("t-email", foundUser.getEmail());
		Assert.assertEquals("t-password", foundUser.getPassword());
	}

	@Test
	@Transactional
	@Rollback
	public void test() {
		UserDO user = new UserDO();
		user.setEmail("test");
		user.setPassword("testPwd");
		user.setUkey("kkk");
		userDao.save(user);
	}

}
