package com.sys.hr.employee;

import java.util.Date;

/**
 * TblInfoFamily1 entity. @author MyEclipse Persistence Tools
 */

public class TblInfoFamily1 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 5868782302507176529L;
	private String id;
	private String empid;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String membername;
	private String memberjob;
	private Date memberbirth;
	private String memberphone;
	private String memberralation;

	// Constructors

	/** default constructor */
	public TblInfoFamily1() {
	}

	/** full constructor */
	public TblInfoFamily1(String empid, String membername, String memberjob,
			Date memberbirth, String memberphone, String memberralation) {
		this.empid = empid;
		this.membername = membername;
		this.memberjob = memberjob;
		this.memberbirth = memberbirth;
		this.memberphone = memberphone;
		this.memberralation = memberralation;
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

	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getMemberjob() {
		return this.memberjob;
	}

	public void setMemberjob(String memberjob) {
		this.memberjob = memberjob;
	}

	public Date getMemberbirth() {
		return this.memberbirth;
	}

	public void setMemberbirth(Date memberbirth) {
		this.memberbirth = memberbirth;
	}

	public String getMemberphone() {
		return this.memberphone;
	}

	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}

	public String getMemberralation() {
		return this.memberralation;
	}

	public void setMemberralation(String memberralation) {
		this.memberralation = memberralation;
	}

}