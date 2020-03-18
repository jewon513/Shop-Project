package com.biz.naver;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.naver.domain.ItemsVO;
import com.biz.naver.service.NaverService;

@Controller
public class NaverController {

	@Autowired
	NaverService naverService;
	
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<ItemsVO> naverSearch(String search, String cat) {
		
		List<ItemsVO> result = null;
		try {
			result = naverService.naverSearch(search, cat);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
		
		
	}
	
	
}
