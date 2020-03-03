package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.repository.BBsDao;


/*
 * 	다중 select 를 수행하는 emthod들이 있고 재귀호출에 의해서 계속되는 
 */

@Transactional
@Service
public class BBsServiceImpl implements BBsService {

	protected final BBsDao bbsDao;

	public BBsServiceImpl(BBsDao bbsDao) {
		this.bbsDao = bbsDao;
	}

	
	/*
	 * 	pagination을 수행할 때 원글 목록을 page 대상으로 할 것인지 원글 + 답글 포함한 목록 page 대상으로 할 것인지 결정
	 * 
	 */
	@Override
	public List<BBsVO> selectAll() {
		
		List<BBsVO> mainList = bbsDao.selectAll();
		List<BBsVO> retList = new ArrayList<BBsVO>();
		
		for (BBsVO vo : mainList) {
			retList.addAll(this.selectRepl(vo,0));
		}
		
		return retList;
	}
	
	private List<BBsVO> selectRepl(BBsVO bbsVO, int depth){
		
		List<BBsVO> retList = new ArrayList<BBsVO>();
		
		
		if(depth > 0) {
			
			String b_subject = "";
			for (int i = 0; i < depth; i++) {
				b_subject += "&nbsp;&nbsp;";
			}
			b_subject += "<i class='fas fa-hand-point-right'></i>" + bbsVO.getB_subject();
			bbsVO.setB_subject(b_subject);
			
		}
		
		retList.add(bbsVO);
		
		List<BBsVO> tempList = bbsDao.findByPId(bbsVO.getB_id());
		
		if(tempList.size()<1) return retList;
		
		List<BBsVO> repList;
		for (BBsVO vo : tempList) {
			repList = this.selectRepl(vo,depth+1);
			retList.addAll(repList);
		}
		
		return retList;
		
	};
	

	@Override
	public BBsVO findById(long b_id) {

		return bbsDao.findById(b_id);
	}

	@Override
	public List<BBsVO> findBySubject(String b_subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BBsVO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BBsVO bbsVO) {
		// TODO Auto-generated method stub

		// 작성일자를 현재 저장하는 날짜로 세팅을 하자
		LocalDateTime ldt = LocalDateTime.now();

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		bbsVO.setB_date_time(ldt.format(df).toString());

		int ret = bbsDao.insert(bbsVO);

		return ret;
	}

	@Override
	public int delete(long b_id) {
		
		int ret = bbsDao.delete(b_id);
		
		return ret;
	}

	@Override
	public int update(BBsVO bbsVO) {

		int ret = bbsDao.update(bbsVO);

		return ret;
	}

}
