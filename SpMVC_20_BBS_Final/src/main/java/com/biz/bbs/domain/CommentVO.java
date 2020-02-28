package com.biz.bbs.domain;

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

public class CommentVO {

	private long c_id;
	private long c_b_id;
	private long c_p_id;
	private String c_date_time;
	private String c_writer;
	private String c_comment;

	
	
}
