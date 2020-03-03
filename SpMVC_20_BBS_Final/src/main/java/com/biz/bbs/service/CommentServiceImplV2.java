package com.biz.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cmtV2")
public class CommentServiceImplV2 extends CommentServiceImpl{

	
	public CommentServiceImplV2(CommentDao commentDao) {
		super(commentDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
