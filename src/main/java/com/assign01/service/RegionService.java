package com.assign01.service;

import java.util.List;
import java.util.Map;

import com.assign01.vo.RegionApplyDataVO;
import com.assign01.vo.RegionDataJsonVO;
import com.assign01.vo.RegionInformVO;

public interface RegionService {
	
	public List<RegionDataJsonVO> getApplyDataJson(String region_nm);
	
	public List<RegionDataJsonVO> getDataSearch(String region_nm) throws Exception;
	
	public List<RegionInformVO> getLimitSortData(int cnt);

	public List<RegionInformVO> getRateSortData();
	
	public List <RegionDataJsonVO> update(Map<String,Object> param);
	
	public void deleteRegion(RegionApplyDataVO vo);
}
