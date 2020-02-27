package com.biz.bbs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 	VO(Value Ojbect) 클래스
 * 	메서드와 메서드간에 web 브라우저와 컨트롤러간에 컨트롤러와 view.jsp 간에 데이터를 교환하는 매개체 역할을 수행
 *  일반적으로는 select 되는 table의 칼럼을 포함하고	
 *  
 *  VO클래스는 객체지향 특징 중 추상화, 정보은닉, 캡슐화 특징을 포함하고 있다.
 *  
 *  추상화 : 어떤 필드변수들을 만들 것인지
 *  정보은닉 : 필드변수를 private으로 선언
 *  캡슐화 : getter, setter 메서드의 코드 정의
 *  
 *  
 *  예 ) b_data_time 필드 변수에는 2020-02-26 14:06:00 형태의 문자열만을 저장하도록 setter()메서드에 관련 코드를 정의할 수 있다.
 *  	 또는 나이를 저장하는 피륻변수 같으면 나이의 범위를 0 ~~ 100까지 제한하는 등의 코드를 setter 메서드에 정의 할 수 있다.
 *  
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BBsVO {

	private long b_id;//	NUMBER
	private long b_p_id;//	NUMBER
	private String b_date_time;//	VARCHAR2(30)
	private String b_writer;//	nVARCHAR2(30)
	private String b_subject;//	nVARCHAR2(125)
	private String b_content;//	nVARCHAR2(2000)
	private String b_file;//	nVARCHAR2(125)

	
}
