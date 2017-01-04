package com.sys.hr.employ;

import java.io.Serializable;
import java.util.Date;

/**
 * ’–∆∏–≈œ¢¿‡
 * @author Administrator
 *
 */
public class Employ implements Serializable {

	private static final long serialVersionUID = 3856980819654313099L;
	private String id;
	private String name;
	private Integer gender;
	private String identityCard;
	private String education;
	private String specialty;
	private String graduateSchool;
	private String address;
	private String phone;
	private String orgid;
	private String position;
	private Date yingpinTime;
	private Date shiyongTime;
	private Date ruzhiTime;
	private Integer employStatus;
	public Employ() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getYingpinTime() {
		return yingpinTime;
	}
	public void setYingpinTime(Date yingpinTime) {
		this.yingpinTime = yingpinTime;
	}
	public Date getShiyongTime() {
		return shiyongTime;
	}
	public void setShiyongTime(Date shiyongTime) {
		this.shiyongTime = shiyongTime;
	}
	public Date getRuzhiTime() {
		return ruzhiTime;
	}
	public void setRuzhiTime(Date ruzhiTime) {
		this.ruzhiTime = ruzhiTime;
	}
	public Integer getEmployStatus() {
		return employStatus;
	}
	public void setEmployStatus(Integer employStatus) {
		this.employStatus = employStatus;
	}
}
