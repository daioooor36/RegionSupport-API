package com.assign01.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CSVReadDAOImpl implements CSVReadDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void addRegion(Map<String, Object> map) {
		sqlSession.insert("csvRead.addRegion", map);
	}

	@Override
	public void addData(Map<String, Object> map) {
		sqlSession.insert("csvRead.addData", map);
	}
}
