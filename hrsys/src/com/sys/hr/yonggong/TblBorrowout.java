package com.sys.hr.yonggong;

import java.util.Date;

/**
 * TblBorrowout entity. @author MyEclipse Persistence Tools
 */

public class TblBorrowout implements java.io.Serializable {

	// Fields

	private String id;
	private String empid;
	private String empname;
	private String fromorgid;
	private String tocompany;
	private Date starttime;
	private Date endtime;
	private String education;
	private String zhichengdj;
	private String pingdingtime;
	private String duzifei;
	private String shebaojishu;
	private String gongjijin;
	private String reason;
	private Date time;
	private String isfanhui;
	private String content;

	// Constructors

	/** default constructor */
	public TblBorrowout() {
	}

	/** full constructor */
	public TblBorrowout(String empid, String empname, String fromorgid,
			String tocompany, Date starttime, Date endtime,
			String education, String zhichengdj, String pingdingtime,
			String duzifei, String shebaojishu, String gongjijin,
			String reason, Date time, String isfanhui, String content) {
		this.empid = empid;
		this.empname = empname;
		this.fromorgid = fromorgid;
		this.tocompany = tocompany;
		this.starttime = starttime;
		this.endtime = endtime;
		this.education = education;
		this.zhichengdj = zhichengdj;
		this.pingdingtime = pingdingtime;
		this.duzifei = duzifei;
		this.shebaojishu = shebaojishu;
		this.gongjijin = gongjijin;
		this.reason = reason;
		this.time = time;
		this.isfanhui = isfanhui;
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

	public String getTocompany() {
		return this.tocompany;
	}

	public void setTocompany(String tocompany) {
		this.tocompany = tocompany;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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

	public String getIsfanhui() {
		return this.isfanhui;
	}

	public void setIsfanhui(String isfanhui) {
		this.isfanhui = isfanhui;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}