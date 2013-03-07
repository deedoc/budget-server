package ru.pomeshikov.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.BaseTest;
import ru.pomeshikov.model.TransactionDO;
import ru.pomeshikov.model.UserDO;

public class UserDaoTest extends BaseTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback
	public void testOneToMany(){
		UserDO u = new UserDO();
		u.setUkey("key123321yek");
		u.setEmail("t-email");
		u.setPassword("t-password");
		
		List<TransactionDO> transactions = new ArrayList<TransactionDO>();
		
		TransactionDO t = new TransactionDO();
		t.setDate(new Date());
		t.setName("dengi");
		t.setValue(BigDecimal.TEN);
		transactions.add(t);
		
		t = new TransactionDO();
		t.setDate(new Date());
		t.setName("dengi2");
		t.setValue(BigDecimal.ONE);
		transactions.add(t);
		
		u.setTransactions(transactions);
		
		userDao.add(u);
		
		UserDO u2 = userDao.findByUkey(u.getUkey());
		
		Assert.assertEquals(2, u2.getTransactions().size());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCriteria(){
		UserDO u = new UserDO();
		u.setUkey("key123321yek");
		u.setEmail("t-email");
		u.setPassword("t-password");
		userDao.add(u);
		
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
		userDao.add(user);
	}

}
