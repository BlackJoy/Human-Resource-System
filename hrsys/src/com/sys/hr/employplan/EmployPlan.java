package com.sys.hr.employplan;

import java.util.Date;

public class EmployPlan {
	private String id;
	private String org;
	private String position;
	private int num;
	private String major;
	private String sex;
	private String englishDegree;
	private String isTrain;
	private String positonAsk;
	private Date wwT;
	private String forSalary;
	private int status;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEnglishDegree() {
		return englishDegree;
	}
	public void setEnglishDegree(String englishDegree) {
		this.englishDegree = englishDegree;
	}

	public String getIsTrain() {
		return isTrain;
	}
	public void setIsTrain(String isTrain) {
		this.isTrain = isTrain;
	}
	public String getPositonAsk() {
		return positonAsk;
	}
	public void setPositonAsk(String positonAsk) {
		this.positonAsk = positonAsk;
	}

	public Date getWwT() {
		return wwT;
	}
	public void setWwT(Date wwT) {
		this.wwT = wwT;
	}
	public String getForSalary() {
		return forSalary;
	}
	public void setForSalary(String forSalary) {
		this.forSalary = forSalary;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EmployPlan(String id, String org, String position, int num,
			String major, String sex, String englishDegree, String isTrain,
			String positonAsk, Date wwT, String forSalary, int status,
			String name) {
		super();
		this.id = id;
		this.org = org;
		this.position = position;
		this.num = num;
		this.major = major;
		this.sex = sex;
		this.englishDegree = englishDegree;
		this.isTrain = isTrain;
		this.positonAsk = positonAsk;
		this.wwT = wwT;
		this.forSalary = forSalary;
		this.status = status;
		this.name = name;
	}	
	public EmployPlan(){
		
	}
}
