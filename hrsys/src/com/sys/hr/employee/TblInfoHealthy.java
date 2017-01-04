package com.sys.hr.employee;

import java.util.Date;

/**
 * TblInfoHealthy entity. @author MyEclipse Persistence Tools
 */

public class TblInfoHealthy implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5868782302507176529L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String id;
	private String empid;
	private String bingname;
	private Date time;
	private String iszhiyu;
	private String reason;

	// Constructors

	/** default constructor */
	public TblInfoHealthy() {
	}

	/** full constructor */
	public TblInfoHealthy(String empid, String bingname, Date time,
			String iszhiyu, String reason) {
		this.empid = empid;
		this.bingname = bingname;
		this.time = time;
		this.iszhiyu = iszhiyu;
		this.reason = reason;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getBingname() {
		return this.bingname;
	}

	public void setBingname(String bingname) {
		this.bingname = bingname;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getIszhiyu() {
		return this.iszhiyu;
	}

	public void setIszhiyu(String iszhiyu) {
		this.iszhiyu = iszhiyu;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}