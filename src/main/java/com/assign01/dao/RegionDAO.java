package com.assign01.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.assign01.vo.RegionApplyDataVO;
import com.assign01.vo.RegionDataJsonVO;
import com.assign01.vo.RegionInformVO;

public interface RegionDAO {

	public List<RegionDataJsonVO> getApplyDataJson(String region_nm);
	
	public List<RegionDataJsonVO> getDataSearch(@Param("region_nm") String region_nm);
	
	public List<RegionInformVO> getLimitSortData(int cnt);

	public List<RegionInformVO> getRateSortData();
	
	public void update(Map<String,Object> param);
	
	public void deleteRegion(RegionApplyDataVO vo);
}
