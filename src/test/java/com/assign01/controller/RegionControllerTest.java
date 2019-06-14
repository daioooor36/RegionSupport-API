package com.assign01.controller;

import static org.mockito.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class RegionControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();		
	}

	// 1. 지원하는 지자체 목록 검색
	@Test
	public void searchList() throws Exception
	{
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andDo(print())
                .andExpect(view().name("home"))
				.andExpect(model().attributeExists("list"))
				.andExpect(content().string(contains("강릉시")));
	}

	// 2. 지자체명 입력받아 해당 지자체 지원정보 출력
	@Test
	public void searchRegionTest() throws Exception
	{
		HashMap<String, Object> objMap = new HashMap<>();		
		objMap.put("region_nm", "강릉시");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(objMap);
		
		this.mockMvc.perform(post("/regionSearch.do").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$[0].rate").value("3%"));
	}
	
	// 3. 지자체 정보 수정기능
	@Test
	@Rollback(true)
	public void updateTest() throws Exception
	{		
		HashMap<String, Object> objMap = new HashMap<>();
		objMap.put("region_cd", "RG105");
		objMap.put("region_nm", "가강서구2");
		objMap.put("target", "경상남도 소재 중소기업으로서 경상남도지사가 추천한 자");
		objMap.put("usage", "운전 및 시설");
		objMap.put("limit", "34억원 이내");
		objMap.put("rate", "2.0%");
		objMap.put("institute", "경상남도");
		objMap.put("mgmt", "창원지점");
		objMap.put("reception", "전 영업점점");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(objMap);
		
		this.mockMvc.perform(put("/update.do").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$[0].limit").value("34억원 이내"));		
	}
	
	// 4.지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정개수만 출력
	@Test
	public void limitSortTest() throws Exception
	{
		HashMap<String, Object> objMap = new HashMap<>();		
		objMap.put("cnt", "1");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(objMap);
		
		this.mockMvc.perform(post("/limitSort.do").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$[0].region_NM").value("경기도"));
	}

	// 5. 이차보전 비율이 가장 작은 추천 기관명 출력
	@Test
	public void rateSortTest() throws Exception
	{
		HashMap<String, Object> objMap = new HashMap<>();		
		objMap.put("cnt", "1");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(objMap);
		
		this.mockMvc.perform(get("/rateSort.do").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$[0].region_NM").value("안산시"));
	}
}
