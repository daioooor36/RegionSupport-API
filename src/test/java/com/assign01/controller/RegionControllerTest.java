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

	@Test
	public void regionList_jsonTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("list_raw"))
			.andDo(print());
			//.andExpect(status().isOk()).andExpect(jsonPath("$[0].region_nm").value("강릉시"));
	}
	
	@Test
	public void searchFormTest() throws Exception
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
	
	@Test
	public void updateFormTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("updateForm"))
		.andDo(print());
	}
	
	@Test
	public void updateTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("update"))
		.andDo(print());
	}
	
	@Test
	public void limitSortTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("limitSort"))
		.andDo(print());
	}
	
	@Test
	public void limitSort_realTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("limitSort_real"))
		.andDo(print());
	}
	
	@Test
	public void rateSort() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("rateSort"))
		.andDo(print());
	}
}
