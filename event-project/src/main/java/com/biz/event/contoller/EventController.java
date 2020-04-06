package com.biz.event.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String mainList() {
		
		return "home";
		
	}
	
}
