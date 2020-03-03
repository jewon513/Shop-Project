

package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.repository.BBsDao;


/*
 * 	다중 select 를 수행하는 emthod들이 있고 재귀호출에 의해서 계속되는 
 */

@Transactional
@Service("bbsV2")
public class BBsServiceImplV2 extends BBsServiceImpl {

	public BBsServiceImplV2(BBsDao bbsDao) {
		super(bbsDao);
		// TODO Auto-generated constructor stub
	}

	/*
	 * 	pagination을 수행할 때 원글 목록을 page 대상으로 할 것인지 원글 + 답글 포함한 목록 page 대상으로 할 것인지 결정
	 * 
	 */
	@Override
	public List<BBsVO> selectAll() {
		
		return bbsDao.selectLevel(); 
		
	}


}
