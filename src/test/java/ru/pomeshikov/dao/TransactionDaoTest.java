package ru.pomeshikov.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
		
		List<TransactionDO> findByDate = dao.findByDate("ukey", new Date());
		
		Assert.assertEquals(2, findByDate.size());
		Assert.assertEquals(BigDecimal.ONE, findByDate.get(0).getValue());
		Assert.assertEquals(BigDecimal.TEN, findByDate.get(1).getValue());
		
	}

}
