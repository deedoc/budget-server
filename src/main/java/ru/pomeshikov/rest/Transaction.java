package ru.pomeshikov.rest;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequestMapping("/transaction")
public class Transaction {
	
	@RequestMapping(value="getForDate", method=RequestMethod.POST)
	public @ResponseBody List<Transaction> getForDate(String ukey, Date date){
		return null;
	}
}
