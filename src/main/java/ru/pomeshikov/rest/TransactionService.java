package ru.pomeshikov.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.TransactionController;
import ru.pomeshikov.model.TransactionDO;

@Service
@RequestMapping("/transaction")
public class TransactionService {
	
	@Autowired
	private TransactionController controller;
	
	@RequestMapping(value="findByDate", method=RequestMethod.POST)
	public @ResponseBody List<TransactionDO> findByDate(@CookieValue("ukey") String ukey, @RequestParam("date") Date date){
		return controller.findByDate(ukey, date);
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody TransactionDO save(@CookieValue("ukey") String ukey, @RequestBody TransactionDO transaction){
		transaction.setUkey(ukey);
		return controller.save(transaction);
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody void delete(@CookieValue("ukey") String ukey, @RequestParam("id") Long id){
		controller.delete(ukey, id);
	}

}
