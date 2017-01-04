package com.sys.hr.train;

// default package

import java.math.BigDecimal;

/**
 * TblTrainScore entity. @author MyEclipse Persistence Tools
 */

public class TrainScore implements java.io.Serializable {

	// Fields

	private String id;
	private String employeecode;
	private String employeename;
	private String traincode;
	private String trainsubject;
	private Double empscore;
	private String evaluate;
	private String employeeid;
	private String trainid;
    private String status;
	// Constructors

	

	// Property accessors


	public TrainScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrainScore(String id, String employeecode, String employeename,
			String traincode, String trainsubject, Double empscore,
			String evaluate, String employeeid, String trainid) {
		super();
		this.id = id;
		this.employeecode = employeecode;
		this.employeename = employeename;
		this.traincode = traincode;
		this.trainsubject = trainsubject;
		this.empscore = empscore;
		this.evaluate = evaluate;
		this.employeeid = employeeid;
		this.trainid = trainid;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeecode() {
		return this.employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}

	public String getEmployeename() {
		return this.employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getTraincode() {
		return this.traincode;
	}

	public void setTraincode(String traincode) {
		this.traincode = traincode;
	}

	public String getTrainsubject() {
		return this.trainsubject;
	}

	public void setTrainsubject(String trainsubject) {
		this.trainsubject = trainsubject;
	}

	public Double getEmpscore() {
		return this.empscore;
	}

	public void setEmpscore(Double empscore) {
		this.empscore = empscore;
	}

	public String getEvaluate() {
		return this.evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getTrainid() {
		return this.trainid;
	}

	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}