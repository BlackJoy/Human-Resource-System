package com.sys.hr.kaoqin2;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sys.hr.org.Org;
import com.sys.hr.org.Post;

public class Kaoqinmonth{

	private String id;
	private String employeeName;
	private String orgid;
	private String postname;	
	private String kaoqinTime;
	private String stateQq;
	private String stateCq;
	private String stateQj;
	private String stateCd;
	private String isaddJb;



	public Kaoqinmonth() {
	}


	public Kaoqinmonth(String id, String employeeName, String kaoqinTime,
			String stateQq, String stateCq, String stateQj, String stateCd,
			String isaddJb, String orgid, String postname) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.kaoqinTime = kaoqinTime;
		this.stateQq = stateQq;
		this.stateCq = stateCq;
		this.stateQj = stateQj;
		this.stateCd = stateCd;
		this.isaddJb = isaddJb;
		this.orgid = orgid;
		this.postname = postname;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getKaoqinTime() {
		return kaoqinTime;
	}


	public void setKaoqinTime(String kaoqinTime) {
		this.kaoqinTime = kaoqinTime;
	}


	public String getStateQq() {
		return stateQq;
	}


	public void setStateQq(String stateQq) {
		this.stateQq = stateQq;
	}


	public String getStateCq() {
		return stateCq;
	}


	public void setStateCq(String stateCq) {
		this.stateCq = stateCq;
	}


	public String getStateQj() {
		return stateQj;
	}


	public void setStateQj(String stateQj) {
		this.stateQj = stateQj;
	}


	public String getStateCd() {
		return stateCd;
	}


	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}


	public String getIsaddJb() {
		return isaddJb;
	}


	public void setIsaddJb(String isaddJb) {
		this.isaddJb = isaddJb;
	}


	public String getOrgid() {
		return orgid;
	}


	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}


	public String getPostname() {
		return postname;
	}


	public void setPostname(String postname) {
		this.postname = postname;
	}



}
