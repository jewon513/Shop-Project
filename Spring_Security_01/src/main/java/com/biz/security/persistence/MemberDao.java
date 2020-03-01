package com.biz.security.persistence;

import com.biz.security.domain.MemberVO;

public interface MemberDao {

	public MemberVO read(String userid);
	
	public int insert(MemberVO memberVO);

	public int update(String userid);
	
}
