package com.biz.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.biz.security.domain.MemberVO;
import com.biz.security.persistence.MemberDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Slf4j
public class MemberMapperTest {


	private final MemberDao memberDao;
	
	@Test
	public void testRead() {
		
		MemberVO vo = memberDao.read("jewon513");
		
		log.info("read한 vo" + vo.toString());
		
		vo.getAuthList().forEach(authVO -> log.info(authVO.toString()));
		
	}
	
}
