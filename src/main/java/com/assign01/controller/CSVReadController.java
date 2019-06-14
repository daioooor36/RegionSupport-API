package com.assign01.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assign01.service.CSVReadService;
import com.assign01.vo.CSVReadVO;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

@Controller
public class CSVReadController
{
	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
	
	@Autowired
	private CSVReadService csvRead;
	
	ClassLoader classLoader = getClass().getClassLoader();
	private String filename = classLoader.getResource("assign01.csv").getFile();
	
		
	public List<CSVReadVO> readCsv()
	{
		logger.info("Start to Read .csv file.");
		
		List<CSVReadVO> data = null;
		
		try
		{
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "euc-kr"));
			
			ColumnPositionMappingStrategy<CSVReadVO> start = new ColumnPositionMappingStrategy<CSVReadVO>();
			start.setType(CSVReadVO.class);
			String[] columns = new String[] {"id", "region_nm", "target", "usage", "limit", "rate", "institute", "mgmt", "reception"};
			start.setColumnMapping(columns);
			CsvToBean<CSVReadVO> csv = new CsvToBean<CSVReadVO>();
			data = csv.parse(start, reader);
			data.remove(0);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}

	public void insertCsv() throws Exception
	{
		//DB insert
		List<CSVReadVO> data = readCsv();
				
		csvRead.addData(data);
	}

	// 1. CSV파일을 읽어 DB에 Insert하는 메소드
	@RequestMapping(value="csvInsert.do", method=RequestMethod.GET)
	public ModelAndView manageData() throws Exception
	{		
		insertCsv();
		
		// '/WEB-INF/views/hoem.jsp'로 포워딩 
		return new ModelAndView("home", "message", "CSV파일을 정상적으로 불러왔습니다.");
	}
}
