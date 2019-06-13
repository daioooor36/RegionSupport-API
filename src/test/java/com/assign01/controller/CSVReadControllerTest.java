package com.assign01.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.assign01.service.CSVReadService;
import com.assign01.vo.CSVReadVO;

@Transactional
public class CSVReadControllerTest
{	
	@InjectMocks
	private CSVReadController readCon;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{		
		readCon = new CSVReadController();
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(readCon).build();
	}

	@Test
	public void readCsvTest()
	{
		List<CSVReadVO> list = readCon.readCsv();
		
		assertEquals(98, list.size());		
	}

	@Transactional
	@Test
	public void insertCsvTest() throws Exception
	{
		/*
		ModelAndView model = readCon.insertCsv();
		
		model.getViewName();
		
		assertEquals("main", model.getViewName());
		*/
	}

	@Transactional
	@Test
	public void manageDataTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("list_raw")).andDo(print());
		/*
		ModelAndView model = readCon.manageData();
		
		Map<String,Object> map = model.getModel();
		
		assertEquals("CSV파일을 성공적으로 읽어왔습니다.", map.get("message"));
		*/
	}
}
