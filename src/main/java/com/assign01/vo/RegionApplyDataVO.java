package com.assign01.vo;

public class RegionApplyDataVO
{
	private int ID;
	private String region_cd, region_nm, target, usage, limit, rate, institute, mgmt, reception, reg_dt, upd_dt;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getRegion_cd() {
		return region_cd;
	}
	public void setRegion_cd(String region_cd) {
		this.region_cd = region_cd;
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
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getUpd_dt() {
		return upd_dt;
	}
	public void setUpd_dt(String upd_dt) {
		this.upd_dt = upd_dt;
	}
	
	@Override
	public String toString() {
		return "RegionApplyDataVO [ID=" + ID + ", region_cd=" + region_cd + ", region_nm=" + region_nm + ", target="
				+ target + ", usage=" + usage + ", limit=" + limit + ", rate=" + rate + ", institute=" + institute
				+ ", mgmt=" + mgmt + ", reception=" + reception + ", reg_dt=" + reg_dt + ", upd_dt=" + upd_dt + "]";
	}
	
}
