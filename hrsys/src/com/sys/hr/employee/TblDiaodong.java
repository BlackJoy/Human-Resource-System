package com.sys.hr.employee;

import java.util.Date;

/**
 * TblDiaodong entity. @author MyEclipse Persistence Tools
 */

public class TblDiaodong implements java.io.Serializable {

	// Fields
	private static long serialVersionUID = 5868782302507176529L;

	private String id;
	private String empid;
	private String empname;
	private String fromorgid;
	private String toorgid;
	private Date starttime;
	private String education;
	private String zhichengdj;
	private String pingdingtime;
	private String duzifei;
	private String shebaojishu;
	private String gongjijin;
	private String reason;
	private Date time;
	private String content;

	// Constructors

	/** default constructor */
	public TblDiaodong() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/** full constructor */
	public TblDiaodong(String empid, String empname, String fromorgid,
			String toorgid, Date starttime, String education,
			String zhichengdj, String pingdingtime, String duzifei,
			String shebaojishu, String gongjijin, String reason, Date time,
			String content) {
		this.empid = empid;
		this.empname = empname;
		this.fromorgid = fromorgid;
		this.toorgid = toorgid;
		this.starttime = starttime;
		this.education = education;
		this.zhichengdj = zhichengdj;
		this.pingdingtime = pingdingtime;
		this.duzifei = duzifei;
		this.shebaojishu = shebaojishu;
		this.gongjijin = gongjijin;
		this.reason = reason;
		this.time = time;
		this.content = content;
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

	public String getFromorgid() {
		return this.fromorgid;
	}

	public void setFromorgid(String fromorgid) {
		this.fromorgid = fromorgid;
	}

	public String getToorgid() {
		return this.toorgid;
	}

	public void setToorgid(String toorgid) {
		this.toorgid = toorgid;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getZhichengdj() {
		return this.zhichengdj;
	}

	public void setZhichengdj(String zhichengdj) {
		this.zhichengdj = zhichengdj;
	}

	public String getPingdingtime() {
		return this.pingdingtime;
	}

	public void setPingdingtime(String pingdingtime) {
		this.pingdingtime = pingdingtime;
	}

	public String getDuzifei() {
		return this.duzifei;
	}

	public void setDuzifei(String duzifei) {
		this.duzifei = duzifei;
	}

	public String getShebaojishu() {
		return this.shebaojishu;
	}

	public void setShebaojishu(String shebaojishu) {
		this.shebaojishu = shebaojishu;
	}

	public String getGongjijin() {
		return this.gongjijin;
	}

	public void setGongjijin(String gongjijin) {
		this.gongjijin = gongjijin;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}