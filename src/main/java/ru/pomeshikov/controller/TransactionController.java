package ru.pomeshikov.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.dao.TransactionDao;
import ru.pomeshikov.model.TransactionDO;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionDao dao;

	@Transactional
	public List<TransactionDO> findByDate(String ukey, Date date) {
		return dao.findByDate(ukey, date);
	}
	
	@Transactional
	public TransactionDO save(TransactionDO transaction){
		dao.save(transaction);
		return transaction;
	}
	
	@Transactional
	public void delete(String ukey, Long id){
		dao.delete(ukey, id);
	}
	
	
	
}
