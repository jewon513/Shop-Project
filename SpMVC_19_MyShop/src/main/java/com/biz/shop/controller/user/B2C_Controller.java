package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {
	
	private final ProductService productService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		
		List<ProductVO> list = productService.selectAll();
		
		
		model.addAttribute("product_list", list);
		
		return "users/user_main";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(Model model, long product_id) {
		
		ProductVO productVO = productService.findById(product_id);
		
		model.addAttribute("pVO", productVO);
		model.addAttribute("BODY", "DETAIL");
		
		return "users/user_main";
	}
	
	
	
	
	
	

}
