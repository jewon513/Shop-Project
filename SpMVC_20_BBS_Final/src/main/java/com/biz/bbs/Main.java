package com.biz.bbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.service.FileReaderService;

public class Main {

	// 진입점 또는 end point method
	public static void main(String[] args) {
		
		FileReaderService fService = new FileReaderService();
		
		List<BBsVO> bbsList = fService.getBBsData();
		
		Collections.sort(bbsList, new Comparator<BBsVO>() {

			@Override
			public int compare(BBsVO o1, BBsVO o2) {

				int s = (int)(o1.getB_id() - o2.getB_id());
				
				return s;
			}
			
		});
		
		// 날짜 시간 역순 정렬
		Collections.sort(bbsList, (o1,o2) -> o2.getB_date_time().compareTo(o1.getB_date_time()) );
		
		// 람다식 코드
//		Collections.sort(bbsList, (o1,o2) -> (int) (o1.getB_id() - o2.getB_id()));
		
		// 부모id(b_p_id)가 0인 리스트만을 추출
		List<BBsVO> pList = new ArrayList<BBsVO>();
		
		for (BBsVO bBsVO : bbsList) {
			
			if(bBsVO.getB_p_id() == 0) {
				pList.add(bBsVO);
			}
			
		}
		
		// 람다식 코드
		/*
		bbsList.forEach(vo -> {
			
			if(vo.getB_p_id() == 0) {
				pList.add(vo);
			}
			
		});
		*/
		
		for (BBsVO bBsVO : pList) {
			System.out.println(bBsVO);
		}
		

		
		
	}
	
}
