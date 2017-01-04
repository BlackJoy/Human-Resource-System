package com.sys.hr.employee;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

	private static final long serialVersionUID = 5868782302507176529L;
	private String id;
	private String employeeCode;
	private String employeeName;
	private String orgid;
	private Integer gender;
	private String identityCard;
	private String education;
	private String specialty;
	private String graduateSchool;
	private String address;
	private String phone;
	private Date shiyongTime;
	private Date ruzhiTime;
	private Integer employeeStatus;
	private String employID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
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
	public Integer getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(Integer employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public String getEmployID() {
		return employID;
	}
	public void setEmployID(String employID) {
		this.employID = employID;
	}
}
