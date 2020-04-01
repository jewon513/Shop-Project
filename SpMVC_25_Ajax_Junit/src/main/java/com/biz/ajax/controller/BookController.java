package com.biz.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.ajax.domain.BookVO;

@RequestMapping(value = "/book")
@Controller
public class BookController {

	
	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(BookVO bookVO, Model model) {
		
		return null;
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(BookVO bookVO, Model model) {
		
		return "book_insert";
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BookVO bookVO) {
		
		return "home";
		
	}
	
}
