package com.assign01.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

	public ModelAndView insertCsv() throws Exception
	{
		//DB insert 작업
		List<CSVReadVO> data = readCsv();
				
		csvRead.addData(data);
		
		// '/WEB-INF/views/csvReadResult.jsp' 으로 포워딩 
		return new ModelAndView("main", "message", "CSV파일을 성공적으로 읽어왔습니다.");
	}

	// 1. 가져온 파일명으로 CSV파일 읽어 DB에 입력
	@RequestMapping(value="csvInsert.do", method=RequestMethod.GET)
	public ModelAndView manageData() throws Exception
	{
		// CSV파일 입력
		insertCsv();
		
		// '/WEB-INF/views/csvReadResult.jsp' 으로 포워딩 
		return new ModelAndView("home", "message", "CSV파일을 성공적으로 읽어왔습니다.");
	}
}
