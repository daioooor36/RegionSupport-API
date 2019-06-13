package com.assign01.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assign01.dao.RegionDAO;
import com.assign01.vo.RegionApplyDataVO;
import com.assign01.vo.RegionDataJsonVO;
import com.assign01.vo.RegionInformVO;

@Transactional(rollbackFor=Exception.class)
@Service
public class RegionServiceImpl implements RegionService
{	
	@Autowired
	private RegionDAO region;

	@Override
	public List<RegionDataJsonVO> getApplyDataJson(String region_nm) {
		return region.getApplyDataJson(region_nm);
	}

	@Override
	public List<RegionDataJsonVO> getDataSearch(String region_nm) throws Exception
	{
		return region.getDataSearch(region_nm);
	}

	@Override
	public List<RegionInformVO> getLimitSortData(int cnt)
	{
		return region.getLimitSortData(cnt);
	}

	@Override
	public List<RegionInformVO> getRateSortData()
	{
		return region.getRateSortData();
	}

	@Override
	public List <RegionDataJsonVO> update(Map<String,Object> param)
	{
		region.update(param);
		return region.getApplyDataJson("%");
	}

	@Override
	public void deleteRegion(RegionApplyDataVO vo)
	{
		region.deleteRegion(vo);
	}
}
