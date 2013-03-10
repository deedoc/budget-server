package ru.pomeshikov.dao;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.BaseTest;
import ru.pomeshikov.model.TransactionDO;

public class TransactionDaoTest extends BaseTest {
	
	@Autowired
	TransactionDao dao;

	@Test
	@Transactional
	@Rollback
	public void test() {
		TransactionDO t = new TransactionDO();
		Date date = new Date();
		t.setDate(date);
		t.setName("dengi");
		t.setUkey("ukey");
		t.setValue(BigDecimal.ONE);
		
		dao.save(t);
		
		t = new TransactionDO();
		t.setDate(date);
		t.setName("dengi2");
		t.setUkey("ukey");
		t.setValue(BigDecimal.TEN);
		
		dao.save(t);
		
		Assert.assertEquals(2, dao.findByDate("ukey", new Date()).size());
		
	}

}
