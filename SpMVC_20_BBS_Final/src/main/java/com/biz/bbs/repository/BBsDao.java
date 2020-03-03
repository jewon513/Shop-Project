package com.biz.bbs.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.biz.bbs.domain.BBsVO;

public interface BBsDao {
	
	public List<BBsVO> selectLevel();

	@Select("SELECT * FROM tbl_bbs WHERE b_p_id = 0 ORDER BY b_id DESC")
	public List<BBsVO> selectAll();
	
	@Select("SELECT * FROM tbl_bbs WHERE b_p_id = #{b_p_id} ORDER BY b_date_time DESC")
	public List<BBsVO> findByPId(long b_p_id);
	
	@Select("SELECT * FROM tbl_bbs WHERE b_id = #{b_id} ORDER BY b_id DESC")
	public BBsVO findById(long b_id);
	
	@Select("SELECT * FROM tbl_bbs WHERE b_subject LIKE '&' || #{b_subject} || '&' ORDER BY b_date_time DESC")
	public List<BBsVO> findBySubject(String b_subject);
	
	@Select("SELECT * FROM tbl_bbs WHERE b_subject LIKE '&' || #{b_writer} || '&' ORDER BY b_date_time DESC")
	public List<BBsVO> findByWriter(String b_writer);
	
	
	public int insert(BBsVO bbsVO);
	
	@Delete("DELETE FROM tbl_bbs WHERE b_id = #{b_id}")
	public int delete(long b_id);
	
	
	public int update(BBsVO BBsVO);
	
}
