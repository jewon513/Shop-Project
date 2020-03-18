package com.biz.naver.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.ItemsVO;
import com.biz.naver.domain.NaverSearchCover;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverService {
	
	private final String naver_news_url = "https://openapi.naver.com/v1/search/news.json";
	private final String naver_book_url = "https://openapi.naver.com/v1/search/book.json";
	private final String naver_movie_url = "https://openapi.naver.com/v1/search/movie.json";
	
	public List<ItemsVO> naverSearch(String search, String cat) throws UnsupportedEncodingException, URISyntaxException {
		
		String queryString = URLEncoder.encode(search,"UTF-8");
		
		if(cat.equalsIgnoreCase("NEWS")) {
			queryString = naver_news_url + "?query=" + queryString;		
		}else if(cat.equalsIgnoreCase("MOVIE")){
			queryString = naver_movie_url + "?query=" + queryString;		
		}else if(cat.equalsIgnoreCase("BOOK")) {
			queryString = naver_book_url + "?query=" + queryString;		
		}
		
		// restTemplete 으로 조회하기 위해 header 값을 설정한다
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id",NaverConfig.NAVER_CLIENT_ID);
		headers.set("X-Naver-Client-Secret",NaverConfig.NAVER_CLIENT_SECERT);
		
		HttpEntity<String> entitiy = new HttpEntity<String>(headers);
		
		
		// 주소변환
		URI restURI = new URI(queryString);
		RestTemplate restTemplate = new RestTemplate();
		
		// 데이터를 받아서 사용할 객체 타입을 지정한다
		// 아래는 기본 문자열로 받기
		ResponseEntity<String> strResult = null;
		
		// 아래는 객체로 받기
		ResponseEntity<NaverSearchCover> responseResult = null;
		
		// 문자열형태로 받기
//		strResult = restTemplate.exchange(restURI, HttpMethod.GET, entitiy, String.class);
		
		// 객체로 받기
		responseResult = restTemplate.exchange(restURI, HttpMethod.GET, entitiy, NaverSearchCover.class);
		
		
		NaverSearchCover naverSearchCover = responseResult.getBody();
		log.debug("가져온 데이터 개수 {}" , naverSearchCover.getTotal());
		if(Integer.valueOf(naverSearchCover.getTotal()) < 1) {
			return null;
		}else {
			return naverSearchCover.getItems();
		}
		
		
		
//		return strResult.getBody();
	}
	

}
