package com.biz.jena.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.jena.service.JenaService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	

	@Autowired
	JenaService jenaTest;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		String result = jenaTest.test();
		
		model.addAttribute("result", result);
		
		return "home";
	}
	
}
