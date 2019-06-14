package com.assign01.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.assign01.service.RegionService;
import com.assign01.vo.RegionDataJsonVO;


@Transactional
public class RegionControllerTest {

	@Mock
	private RegionService region;
	
	@InjectMocks
	private RegionController regionCon;
	
	private MockMvc mockMvc;
	
    private RegionDataJsonVO regionDataJsonVO;
	
	public MockHttpSession session;
	public MockHttpServletRequest request;
	
	@Before
	public void setUp() throws Exception
	{
		regionDataJsonVO = new RegionDataJsonVO();
		regionDataJsonVO.setRegion_nm("강릉시");
		regionDataJsonVO.setTarget("강릉시 소재 중소기업으로서 강릉시장이 추천한 자");
		regionDataJsonVO.setUsage("운전");
		regionDataJsonVO.setLimit("추천금액 이내");
		regionDataJsonVO.setRate("3%");
		regionDataJsonVO.setInstitute("강릉시");
		regionDataJsonVO.setMgmt("강릉지점");
		regionDataJsonVO.setReception("강릉시 소재 영업점");
		
		session = new MockHttpSession();
		session.setAttribute("regionDataJsonVO", regionDataJsonVO);
		
		request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		
		MockitoAnnotations.initMocks(this);		 
		mockMvc = MockMvcBuilders.standaloneSetup(regionCon).build();
	}

	// 1. 지원하는 지자체 목록 검색
	@Test
	public void searchListTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("regionSearch"))
			.andDo(print())
			.andExpect(status().isOk());//.andExpect(jsonPath("$[0].region_nm").value("강릉시"));
	}
	
	// 2. 지자체명 입력받아 해당 지자체 지원정보 출력
	@Test
	public void searchRegionTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("searchForm"))
		.andDo(print());
	}
	
	@Test
	public void searchTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("search"))
		.andDo(print());
	}
	
	// 3. 지자체 정보 수정기능
	@Test
	public void updateTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("update"))
		.andDo(print());
	}
	
	// 4.지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정개수만 출력
	@Test
	public void limitSortTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("limitSort_real"))
		.andDo(print());
	}
	
	// 5. 이차보전 비율이 가장 작은 추천 기관명 출력
	@Test
	public void rateSortTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("rateSort"))
		.andDo(print());
	}
}
