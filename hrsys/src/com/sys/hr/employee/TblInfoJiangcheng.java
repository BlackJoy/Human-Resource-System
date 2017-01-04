package com.sys.hr.employee;

import java.util.Date;

/**
 * TblInfoJiangcheng entity. @author MyEclipse Persistence Tools
 */

public class TblInfoJiangcheng implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5868782302507176529L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String id;
	private String empid;
	private String empname;
	private String cuoshi;
	private String reason;
	private Date time;

	// Constructors

	/** default constructor */
	public TblInfoJiangcheng() {
	}

	/** full constructor */
	public TblInfoJiangcheng(String empid, String empname, String cuoshi,
			String reason, Date time) {
		this.empid = empid;
		this.empname = empname;
		this.cuoshi = cuoshi;
		this.reason = reason;
		this.time = time;
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

	public String getEmpname() {
		return this.empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getCuoshi() {
		return this.cuoshi;
	}

	public void setCuoshi(String cuoshi) {
		this.cuoshi = cuoshi;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}