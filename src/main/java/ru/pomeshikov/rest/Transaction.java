package ru.pomeshikov.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.dao.TransactionDao;
import ru.pomeshikov.model.TransactionDO;

@Service
@RequestMapping("/transaction")
public class Transaction {
	
	@Autowired
	private TransactionDao transactionDao;
	
	@RequestMapping(value="getForDate", method=RequestMethod.POST)
	public @ResponseBody List<TransactionDO> findByDate(String ukey, Date date){
		return transactionDao.findByDate(ukey, date);
	}

}
