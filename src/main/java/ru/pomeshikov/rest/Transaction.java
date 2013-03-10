package ru.pomeshikov.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.TransactionController;
import ru.pomeshikov.model.TransactionDO;

@Service
@RequestMapping("/transaction")
public class Transaction {
	
	@Autowired
	private TransactionController controller;
	
	@RequestMapping(value="findByDate", method=RequestMethod.GET)
	public @ResponseBody List<TransactionDO> findByDate(String ukey, Date date){
		return controller.findByDate(ukey, date);
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody TransactionDO save(@RequestBody TransactionDO transaction){
		return controller.save(transaction);
	}

}
