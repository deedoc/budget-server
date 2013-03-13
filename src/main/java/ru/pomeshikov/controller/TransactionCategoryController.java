package ru.pomeshikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ru.pomeshikov.dao.TransactionCategoryDao;
import ru.pomeshikov.model.TransactionCategoryDO;

@Controller
public class TransactionCategoryController {
	
	@Autowired
	private TransactionCategoryDao dao;

	@Transactional
	public TransactionCategoryDO findById(String ukey, Long id) {
		return dao.findById(ukey, id);
	}
	
	@Transactional
	public TransactionCategoryDO save(TransactionCategoryDO transactionCategory){
		dao.save(transactionCategory);
		return transactionCategory;
	}
	
	@Transactional
	public void delete(String ukey, Long id){
		dao.delete(ukey, id);
	}
	
	
	
}
