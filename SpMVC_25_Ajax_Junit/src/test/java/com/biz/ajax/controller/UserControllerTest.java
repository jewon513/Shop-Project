package com.biz.ajax.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biz.ajax.domain.UserVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"}
		)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
	}

	@Test
	public void testSaveUser() throws Exception {

		
		UserVO userVO = new UserVO();
		
		/*
		 * 	saveUser를 POST 방식으로 호출하면서 4개의 requestParam 데이터를 주입하고
		 * 결과가 200인지 검사하고 최종적으로 model에 담겨서 return 되는 값이 userVO인가?
		 * 
		 */
		
		mockMvc.perform(post("/saveUser")
				.param("userId", userVO.SampleVO().getUserId())
				.param("password", userVO.SampleVO().getPassword())
				.param("userName", userVO.SampleVO().getUserName())
				.param("userRole", userVO.SampleVO().getUserRole())
				)
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userVO"))
				.andReturn();
	
	}

	@Test
	public void testSendUserId() throws Exception {
		
		mockMvc.perform(post("/sendUserId")
				.param("userId", "admin")
				.param("password", "12345")
				.param("userName", "Hong")
				.param("userRole", "admin")
				)
				 .andDo(print())
				 .andExpect(status().isOk())
				 .andExpect(content().contentType("application/json;charset=UTF-8"))
				 .andExpect(jsonPath("$.userVO.userId", is("admin")))
				 .andExpect(jsonPath("$.userVO.password").exists())
				 .andExpect(jsonPath("$.userVO.userName").exists())
				 .andExpect(jsonPath("$.userVO.userRole").exists());
	
	}
//
//	@Test
//	public void testSendUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSendUserFromHtml() {
//		fail("Not yet implemented");
//	}

}
