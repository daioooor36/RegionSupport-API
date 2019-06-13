package com.assign01.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assign01.vo.RegionApplyDataVO;
import com.assign01.vo.RegionDataJsonVO;
import com.assign01.vo.RegionInformVO;

@Repository
public class RegionDAOImpl implements RegionDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<RegionDataJsonVO> getApplyDataJson(String region_nm) {
		return sqlSession.selectList("region.getApplyDataJson", region_nm);
	}

	@Override
	public List<RegionDataJsonVO> getDataSearch(String region_nm)
	{
		return sqlSession.selectList("region.getDataSearch", region_nm);
	}

	@Override
	public List<RegionInformVO> getLimitSortData(int cnt)
	{
		return sqlSession.selectList("region.getLimitSortData", cnt);
	}

	@Override
	public List<RegionInformVO> getRateSortData()
	{
		return sqlSession.selectList("region.getRateSortData");
	}

	@Override
	public void update(Map<String,Object> param)
	{
		sqlSession.update("region.update", param);
	}

	@Override
	public void deleteRegion(RegionApplyDataVO vo)
	{
		sqlSession.delete("region.deleteRegion", vo);
	}
}
