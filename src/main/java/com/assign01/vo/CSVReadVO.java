package com.assign01.vo;

public class CSVReadVO
{
	private String id, region_nm, target, usage, limit, rate, institute, mgmt, reception;
	
	public CSVReadVO()
	{		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegion_nm() {
		return region_nm;
	}

	public void setRegion_nm(String region_nm) {
		this.region_nm = region_nm;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getMgmt() {
		return mgmt;
	}

	public void setMgmt(String mgmt) {
		this.mgmt = mgmt;
	}

	public String getReception() {
		return reception;
	}

	public void setReception(String reception) {
		this.reception = reception;
	}

	@Override
	public String toString() {
		return "CSVReadVO [id=" + id + ", region_nm=" + region_nm + ", target=" + target + ", usage=" + usage
				+ ", limit=" + limit + ", rate=" + rate + ", institute=" + institute + ", mgmt=" + mgmt + ", reception="
				+ reception + "]";
	}
}
