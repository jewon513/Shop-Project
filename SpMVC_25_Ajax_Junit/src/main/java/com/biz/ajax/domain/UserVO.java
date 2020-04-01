package com.biz.ajax.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String userId;
	private String password;
	private String userName;
	private String userRole;
	
	
	public UserVO SampleVO() {
		
		UserVO userVO = UserVO.builder().userId("admin").password("12345").userName("홍길동").userRole("admin").build();
		
		return userVO;
	
	}
	
	
}
