package com.biz.naver.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverSearchCover {
	
	private String lastBuildDate;
	private String total;
	private String start;
	private String display;
	
	private List<ItemsVO> items;

}
