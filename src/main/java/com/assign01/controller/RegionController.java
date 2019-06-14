package com.assign01.controller;

import java.net.URLDecoder;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.assign01.service.RegionService;
import com.assign01.vo.RegionDataJsonVO;
import com.assign01.vo.RegionInformVO;

@RestController
public class RegionController
{
	@Autowired
	private RegionService region;

	public void sortList(List<RegionDataJsonVO> list) throws Exception
	{
		Comparator <RegionDataJsonVO> RegionComparator = new Comparator<RegionDataJsonVO>()
		{
			@Override
			public int compare(RegionDataJsonVO c1, RegionDataJsonVO c2)
			{
				String nm1 = c1.getRegion_nm();
				String nm2 = c2.getRegion_nm();
				
				return nm1.compareToIgnoreCase(nm2);
			}
		};
		list.sort(RegionComparator);
	}
	
	// 1. 지원하는 지자체 목록 검색
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView searchList(@RequestBody @RequestParam(value="region_nm", required=false, defaultValue="%") String param) throws Exception
	{
		String region_nm = param;
		if(!region_nm.equals("%"))
		{
			region_nm = URLDecoder.decode(region_nm, "UTF-8");
		}
		System.out.println("param:"+region_nm);

		List <RegionDataJsonVO> list = region.getApplyDataJson(region_nm);

		// 지자체 명 오름차순 정렬(리턴값 불필요)
		sortList(list);
		
		System.out.println(list);
		
		return new ModelAndView("home", "list", list);
	}
	
	// 1. 지원하는 지자체 목록 검색
	@RequestMapping(value = "/regionSearch.do", method=RequestMethod.POST)
	public List <RegionDataJsonVO> searchRegion(@RequestBody Map<String,Object> param) throws Exception
	{
		String region_nm = (String) param.get("region_nm");
		if(!region_nm.equals("%"))
		{
			region_nm = URLDecoder.decode(region_nm, "UTF-8");
		}
		System.out.println("param:"+region_nm);

		List <RegionDataJsonVO> list = region.getApplyDataJson(region_nm);
		
		// 지자체 명 오름차순 정렬(리턴값 불필요)
		sortList(list);
		
		System.out.println(list);
		
		return list;
	}
	
	// 2. 지자체명 입력받아 해당 지자체 지원정보 출력
	@RequestMapping(value="/search.do", method=RequestMethod.POST)
	public List <RegionDataJsonVO> search(@RequestBody Map<String,Object> param) throws Exception
	{
		String region_nm = (String) param.get("region_nm");
						
		List <RegionDataJsonVO> list = region.getDataSearch(region_nm);
		
		return list;		
	}
	
	// 3. 지자체 정보 수정기능
	@RequestMapping(value = "update.do", method=RequestMethod.PUT)
	public List <RegionDataJsonVO> update(@RequestBody Map<String,Object> param) throws Exception
	{
		List <RegionDataJsonVO> list = region.update(param);
		
		return list;
	}
	
	// 4.지원한도 컬럼에서 지원금액으로 내림차순 정렬하여 특정개수만 출력
	@RequestMapping(value = "limitSort.do", method=RequestMethod.POST)
	public List <RegionInformVO> limitSort(@RequestBody Map<String,Object> param) throws Exception
	{
		// 파라미터에 아무것도 안들어있을 경우 리턴
		if(param.isEmpty() || param.get("cnt").equals(""))
			return null;
		
		// 파라미터에서 id="cnt"에 해당하는 value 추출
		int cnt = Integer.parseInt((String)param.get("cnt"));
		
		// 추출대상 개수가 0이하인 경우 리턴
		if(cnt <= 0)
			return null;
		
		// 전체 지자체 지원정보 조회
		List <RegionInformVO> list = region.getLimitSortData(cnt);
		
		return list;
	}
	
	// 5. 이차보전 비율이 가장 작은 추천 기관명 출력
	@RequestMapping(value = "rateSort.do", method=RequestMethod.GET)
	public List <RegionInformVO> rateSort() throws Exception
	{
		// 전체 지자체 지원정보 조회
		List <RegionInformVO> list = region.getRateSortData();
		
		return list;
	}
}
