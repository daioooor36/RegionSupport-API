package com.assign01.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

		// DB에 98개 rows의 데이터가 잘 들어갔는지 테스트
		assertThat(list.size(), CoreMatchers.is(98));
		
		// 그 중 첫번째 행의 region_nm이 강릉시로 제대로 들어갔는지 테스트
		assertThat(list.get(0).getRegion_nm(), CoreMatchers.is("강릉시"));
	}

	@Transactional
	@Test
	public void manageDataTest() throws Exception
	{
		//HTTP Method GET요청을 잘받았는지 테스트
		mockMvc.perform(MockMvcRequestBuilders.get("csvInsert")).andDo(print());
	}
}
