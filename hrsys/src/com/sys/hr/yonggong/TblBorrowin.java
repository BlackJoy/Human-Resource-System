package com.sys.hr.yonggong;

import java.util.Date;

/**
 * TblBorrowin entity. @author MyEclipse Persistence Tools
 */

public class TblBorrowin implements java.io.Serializable {

	// Fields

	private String id;
	private String empname;
	private String toorgid;
	private String fromcompany;
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
	private String phone;

	// Constructors

	/** default constructor */
	public TblBorrowin() {
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** full constructor */
	public TblBorrowin(String empname, String toorgid, String fromcompany,
			Date starttime, Date endtime, String education,
			String zhichengdj, String pingdingtime, String duzifei,
			String shebaojishu, String gongjijin, String reason, Date time,
			String isfanhui, String content,String phone) {
		this.empname = empname;
		this.toorgid = toorgid;
		this.fromcompany = fromcompany;
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
		this.phone=phone;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpname() {
		return this.empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getToorgid() {
		return this.toorgid;
	}

	public void setToorgid(String toorgid) {
		this.toorgid = toorgid;
	}

	public String getFromcompany() {
		return this.fromcompany;
	}

	public void setFromcompany(String fromcompany) {
		this.fromcompany = fromcompany;
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