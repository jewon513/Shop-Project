package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

/*
 *  summernote 에서 dragAndDrop으로 이미지 파일을 업로드 하면 일단 서버에 파일을 업로드 하고
 *  파일 이름을 다시 내려 보내서 base64로 encoding된 파일 정보를 img src= "저정된경로/파일이름" 형식으로 변경할 것이다.
 * 
 */
@RequiredArgsConstructor
@Service
public class FileService {

	// servlet-context.xml에 등록된 filePath bean을 injection 한다.
	private final String filePath;
	
	
	/*
	 * 	1. 브라우저에서 파일이 전송되어 오면 원래 파일 이름을 UUID가 부착된 파일 이름으로 변경
	 *  2. 변경된 이름으로 서버의 filePath에 저장을 하고 그 파일 이름을 호출한 곳에 return 할 것이다.
	 */
	public String fileup(MultipartFile upFile) {
		
		// 파일이름을 추출한다. (ex 그림.jpg)
		String originalFileName = upFile.getOriginalFilename();
		
		// UUID가 부착된 파일 새로운 이름을 생성한다.
		String strUUID = UUID.randomUUID().toString();
		
		// (ex UUID 그림.jpg)
		strUUID += originalFileName;
		
		
		// filePath와 변경된 파일이름을 결합하여 File 객체를 생성
		File serverFile = new File(filePath, strUUID);
		
		// upload할 filePath가 있는지 확인을 먼저 할 것이다.
		// 없으면 폴더를 생성 할 것이다.
		
		File dir = new File(filePath);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// upFile을 serverFile 이름으로 복사 수행
		// 실제로 서버에 업로드되는 부분이다.
		try {
			
			upFile.transferTo(serverFile);
			
			return strUUID;
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}


















