package com.biz.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.BBsService;
import com.biz.bbs.service.CommentService;
import com.biz.bbs.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BBsController {

	/*
	 * 현재 BBsController와 BBsService* 는 BBsSErvice 인터페이스를 거쳐서 연결이 되어 있다.
	 * BBsService 인터페이스를 implements 한 클래스의 코드가 미완성 상태이지만
	 * BBsControler 입장장에서는 BBsService 의 코드가 완성되어있다라는 전제하에 Controller의 코드 작성을 진행 할 수 있다.
	 * 
	 * 만약 이후에 BBsService의 코드가 완성되고 여러개의 BBsServiceImp 클래스가 작성되면 필요한 클래스를 가져다가 부착만한다면 프로젝트가 완성이 될것 이다.
	 * 
	 * 결합도를 낮추는 결과가 된다.
	 */
	@Autowired
	private BBsService bbsService;
	
	@Autowired
	private FileService fileservice;
	
	@Autowired
	private CommentService commentService;


	/*
	 * 	게시판 목록전체를 보기 위한 method 이고 DB에서 tbl_bbs 테이블 전체를 SELECT 한 결과를 BBS_LIST라는 이름으로 model에 Attribute를 추가하여
	 *  bbs_list.jsp와 rendering 하도록 수행 한다. 
	 * 
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		
		List<BBsVO> list = bbsService.selectAll();
		
		model.addAttribute("BBS_LIST", list);
		
		return "bbs_list";
	}
	

	/*
	 * 글쓰기 버튼을 클릭했을때 게시판 작성 화면을 열어줄 path 버튼을 클릭했을 때 작동할 path는 method를 GET로 설정한다.
	 */
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert() {
		
		return "bbs_write";
	}
	
	/*
	 *  게시판 작성화면에서 저장(글쓰기)버튼을 클릭했을때
	 *  - 게시판 작성 page url은 bbs/insert 
	 *  - form tag 내의 button을 클릭하면 method 방식을 POST로 설정해두었고
	 *  - action을 설정해두지 않았기 때문에,
	 *  - bbs/insert , method = POST 로 설정해둔 controller로 데이터를 전송한다.
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(BBsVO bbsVO) {
		
		bbsService.insert(bbsVO);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(@RequestParam(value = "b_id")String b_id , Model model) {
		
		BBsVO bbsVO = bbsService.findById(Long.valueOf(b_id));
		
		model.addAttribute("BBSVO", bbsVO);
		
		return "bbs_write";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(BBsVO bbsVO) {
		
		bbsService.update(bbsVO);
		
		return "redirect:/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(String b_id) {
		
		int ret = bbsService.delete(Long.valueOf(b_id));
		
		if (ret > 0) {
			return "OK";
		}else {
			return "FALSE";
		}
		
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(String b_id, Model model) {
		
		BBsVO bbsVO = bbsService.findById(Long.valueOf(b_id));
		List<CommentVO> commentList = commentService.findByBId(Long.valueOf(b_id));
		
		model.addAttribute("BBSVO", bbsVO);
		model.addAttribute("COMMENTLIST", commentList);
		
		return "bbs_detail";
		
	}
	
	
	// 파일 업로드 부분
	
	
	@ResponseBody
	@RequestMapping(value = "image_up", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String fileUp (MultipartFile upFile) {
		
		log.debug("업로드 된 파일의 파일 이름" +upFile.getOriginalFilename());
		
		// UUID가 적용된 filename을 받는다.
		String retrunFileName = fileservice.fileup(upFile);
		
		// 업로드에 실패하면 null 값이 return 되므로 여기서 체크해준다.
		if(retrunFileName == null) {
			return "FAIL";
		}
		
		return retrunFileName;
		
	}
	
	
}










