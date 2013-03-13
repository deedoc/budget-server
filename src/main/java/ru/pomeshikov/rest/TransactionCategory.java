package ru.pomeshikov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.controller.TransactionCategoryController;
import ru.pomeshikov.model.TransactionCategoryDO;

@Service
@RequestMapping("/transactionCategory")
public class TransactionCategory {
	
	@Autowired
	private TransactionCategoryController controller;
	
	@RequestMapping(value="findById", method=RequestMethod.GET)
	public @ResponseBody TransactionCategoryDO findById(String ukey, Long id){
		return controller.findById(ukey, id);
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody TransactionCategoryDO save(@RequestBody TransactionCategoryDO transactionCategory){
		return controller.save(transactionCategory);
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam String ukey, @RequestParam Long id){
		controller.delete(ukey, id);
	}

}
