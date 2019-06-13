package com.assign01.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assign01.dao.CSVReadDAO;
import com.assign01.vo.CSVReadVO;

@Transactional(rollbackFor=Exception.class)
@Service
public class CSVReadServiceImpl implements CSVReadService{

	@Autowired
	private CSVReadDAO csvRead;
	
	@Override
	public void addData(List<CSVReadVO> vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionList", vo);
		csvRead.addRegion(map);
		csvRead.addData(map);
	}
}
