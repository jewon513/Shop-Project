package com.biz.test.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AddrService {

	public Map<String, String> getAddr() {
		
		Map<String,String> addr = new HashMap<String,String>();
		addr.put("name","홍길동");
		addr.put("addr","서울특별시");
		addr.put("tel","010-4195-2067");
		
		
		return addr;
	}
	
	
	
	public String getName(String name) {
		return "내이름은" +name +"입니다.";
	}
	
}

