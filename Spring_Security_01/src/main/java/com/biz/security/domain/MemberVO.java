package com.biz.security.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

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
@Alias("memberVO")

public class MemberVO {

	private String userid;
	private String userpw;
	private String username;
	private String enabled;
	
	private String regdate;
	private String updatedate;
	
	private List<AuthVO> authList;
	
	
}
