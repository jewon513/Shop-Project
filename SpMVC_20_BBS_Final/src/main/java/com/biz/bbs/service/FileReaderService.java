package com.biz.bbs.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

import com.biz.bbs.domain.BBsVO;

public class FileReaderService {

	public List<BBsVO> getBBsData(){
		
		// src/main/resources 폴더에 있는 파일 정보 가져오기
		
		ClassPathResource cr = new ClassPathResource("bbs_data.txt");
		
		Path path;
		
		try {
			
			path = Paths.get(cr.getURI());
			
			List<String> lines = Files.readAllLines(path);
			
			List<BBsVO> bbsList = new ArrayList<BBsVO>();
			
			for (String string : lines) {
				String[] items = string.split(":");
				BBsVO bbsVO = new BBsVO();
				
				bbsVO.setB_id(Long.valueOf(items[0]));
				bbsVO.setB_p_id(Long.valueOf(items[1]));
				bbsVO.setB_writer(items[2]);
				String date_time = items[3];
				date_time += ":" +items[4];
				date_time += ":" +items[5];
				bbsVO.setB_date_time(date_time);
				bbsVO.setB_subject(items[6]);
				bbsVO.setB_content(items[7]);
				
				bbsList.add(bbsVO);
			
			}
			
			return bbsList;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}// getBBsData()
	
	
	public List<BBsVO> getMain(List<BBsVO> bbsList){
	
		List<BBsVO> pList;
		
		// List 객체를 stream 객체로 변환
		pList = bbsList.stream().filter( vo -> vo.getB_p_id() == 0 ).collect(Collectors.toList());
		
		return pList;
	}
	
	public List<BBsVO> getRepl(List<BBsVO> bbsList, BBsVO bbsVO){
		
		List<BBsVO> rList = new ArrayList<BBsVO>();
		List<BBsVO> tempList;
		
		rList.add(bbsVO);
		
		tempList = bbsList.stream().filter(vo -> vo.getB_p_id() == bbsVO.getB_id()).collect(Collectors.toList());
		
		if(tempList.size()<1) {
			
			return rList;
			
		}
		
		
		tempList.forEach(vo ->{
			
			rList.addAll(this.getRepl(bbsList, vo));
			
		});
		
		return rList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
