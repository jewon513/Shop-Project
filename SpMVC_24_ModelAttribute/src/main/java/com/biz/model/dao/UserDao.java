package com.biz.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.biz.model.domain.UserVO;

@Mapper
public interface UserDao {

	@Select("SELECT * FROM tbl_user WHERE userId = #{userId}")
	public UserVO findByUserId(String userId);

	
	@Insert("	INSERT INTO  `books` . `tbl_user` " + 
			"	( userId ," + 
			"	 password ," + 
			"	 userName ," + 
			"	 userRole )" + 
			"	VALUES" + 
			"	(#{userId} ," + 
			"	#{password} ," + 
			"	#{userName} ," + 
			"	#{userRole} )" )
	public int insert(UserVO userVO);
	
	@Delete("DELETE FROM tbl_user WHERE userId = #{userId}")
	public int delete(String userId);
	
}
