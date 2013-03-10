package ru.pomeshikov.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.dao.TransactionDao;
import ru.pomeshikov.model.TransactionDO;

@Service
@RequestMapping("/transaction")
public class Transaction {
	
	@RequestMapping(value="getForDate", method=RequestMethod.POST)
	public @ResponseBody List<Transaction> getForDate(String ukey, Date date){
		return null;
	}
	
	
	@Autowired
	private TransactionDao transactionDao;
	
	@RequestMapping(value="ggg", method=RequestMethod.GET)
	@Transactional
	public @ResponseBody void ggg(){
		TransactionDO t = new TransactionDO();
		Date date = new Date();
		t.setDate(date);
		t.setName("dengi");
		t.setUkey("ukey");
		t.setValue(BigDecimal.ONE);
		
		transactionDao.save(t);
	}
}
