package com.sys.hr.kaoqin;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sys.hr.org.Org;
import com.sys.hr.org.Post;

public class Kaoqin {

	private String id;
	private String employeeName;
	private Date kaoqinTime;
	private String state;
	private String isadd="-1";
	private String orgid;
	private String postname;	


	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Kaoqin() {
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



	public Date getKaoqinTime() {
		return kaoqinTime;
	}

	public void setKaoqinTime(Date kaoqinTime) {
		this.kaoqinTime = kaoqinTime;
	}

	public Kaoqin(String id, String employeeName, Date kaoqinTime,
			String state, String isadd, String orgid, String postname) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.kaoqinTime = kaoqinTime;
		this.state = state;
		this.isadd = isadd;
		this.orgid = orgid;
		this.postname = postname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsadd() {
		return isadd;
	}

	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}


	
	
}
