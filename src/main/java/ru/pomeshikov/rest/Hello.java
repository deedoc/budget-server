package ru.pomeshikov.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.pomeshikov.model.User;
/**/
@Controller
@RequestMapping("/hello")
public class Hello {
	@RequestMapping(value="world", method=RequestMethod.GET)
	public @ResponseBody User world(){
		User result = new User();
		result.setName("Gena");
		return result;
	}
}
