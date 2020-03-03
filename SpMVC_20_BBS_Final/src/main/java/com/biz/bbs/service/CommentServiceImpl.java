package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentDao commentDao;
	
	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentVO findById(long c_id) {

		CommentVO commentVO = commentDao.findById(c_id);
		
		return commentVO;
	}
	
	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		
		return commentDao.findByPId(c_p_id);
	}

	@Override
	public List<CommentVO> findByBId(long c_b_id) {
		
		return commentDao.findByBId(c_b_id);
		
	}

	@Override
	public int insert(CommentVO commentVO) {

		if(commentVO.getC_id() > 0) {
			int ret = commentDao.update(commentVO);
			return ret;
		}else {
			log.debug("입력받은 댓글 객체 : " + commentVO.toString());
			
			LocalDateTime ldt = LocalDateTime.now();

			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			commentVO.setC_date_time(ldt.format(df).toString());
			
			int ret = commentDao.insert(commentVO);
			return ret;
		}
		
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_id) {

		int ret = commentDao.delete(c_id);
		
		
		
		return ret;
	}





	
	
}
