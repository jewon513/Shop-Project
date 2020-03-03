package com.biz.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.CommentService;


/*
 * 	class에 부착하는 ReqeustMapping
 * 	type 수준의 Request....
 *  top level Request.......
 *  
 *  메서드에 /list 라고 RequestMapping을 붙이면 호출을 할 때 
 *  context/comment/list 라고 path가 지정됨
 */

@RequestMapping(value = "/comment")
@Controller
public class CommentController {

	
	@Autowired
	@Qualifier("cmtV2")
	private CommentService commentService;
	
	/*
	 *  게시판의 id 값을 매개변수로 받아서 코멘트 리스트를 조회하고 넘겨주는 메서드
	 */
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(String c_b_id, Model model){

		List<CommentVO> list = commentService.findByBId(Long.valueOf(c_b_id));
		
		model.addAttribute("COMMENTLIST", list);
		
		return "incldue/include-comment-list";
		
	}
	
	/*
	 *  코멘트 입력값을 매개변수로 받아서 db insert를 수행할 메서드
	 */
	
	@ResponseBody
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(CommentVO commentVO) {
		
		int ret = commentService.insert(commentVO);
		
		if (ret > 0) {
			return "OK";
		}else {
			return "FALSE";
		}
		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(String c_id, String b_id, Model model) {
		
		int ret = commentService.delete(Long.valueOf(c_id));
		model.addAttribute("c_b_id", b_id);
		
		return "redirect:/comment/list";
	}
	
	
	@RequestMapping(value = "reply", method = RequestMethod.GET)
	public String reply(CommentVO cmtVO, Model model) {
	
		model.addAttribute("CMT", cmtVO);
		
		return "incldue/include-comment-write";
	}
	
}




















