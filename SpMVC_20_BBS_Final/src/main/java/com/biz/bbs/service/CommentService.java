package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;

public interface CommentService {

	public List<CommentVO> selectAll();
	public CommentVO findById(long c_id);
	
	// 해당 게시글의 댓글만 가져오기
	public List<CommentVO> findByBId(long c_b_id);
	
	public List<CommentVO> findByPId(long c_p_id);
	
	public int insert(CommentVO commentVO);
	public int update(CommentVO commentVO);
	
	public int delete(long c_id);
	
}
