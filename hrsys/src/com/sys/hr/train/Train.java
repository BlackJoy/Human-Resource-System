package com.sys.hr.train;

import java.util.Date;

public class Train {

	private String id;
	private String trainCode;//培训编码
	private String trainType;//培训类型
	private String trainSubject;//培训项目
	private String trainObject;//培训对象
	private String orgCode;//主办单位编码
	private String orgFullName;//主办单位名称
	private Date trainStart;//培训开始时间
	private Date trainEnd;//培训结束时间
	private double trainPrice;// 培训价格
	private String trainManner;//培训方式
	private String trainRemarks;//培训备注
	private String trainStatus;//培训状态
	private String applicantName;//申请人
	private String applicantCode;//申请人编码
	private String trainDetail;//申请细节
	private String orgId;
	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Train(String id, String trainCode, String trainType,
			String trainSubject, String trainObject, String orgCode,
			String orgFullName, Date trainStart, Date trainEnd,
			double trainPrice, String trainManner, String trainRemarks,
			String trainStatus, String applicantName, String applicantCode,
			String trainDetail) {
		super();
		this.id = id;
		this.trainCode = trainCode;
		this.trainType = trainType;
		this.trainSubject = trainSubject;
		this.trainObject = trainObject;
		this.orgCode = orgCode;
		this.orgFullName = orgFullName;
		this.trainStart = trainStart;
		this.trainEnd = trainEnd;
		this.trainPrice = trainPrice;
		this.trainManner = trainManner;
		this.trainRemarks = trainRemarks;
		this.trainStatus = trainStatus;
		this.applicantName = applicantName;
		this.applicantCode = applicantCode;
		this.trainDetail = trainDetail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrainCode() {
		return trainCode;
	}
	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getTrainSubject() {
		return trainSubject;
	}
	public void setTrainSubject(String trainSubject) {
		this.trainSubject = trainSubject;
	}
	public String getTrainObject() {
		return trainObject;
	}
	public void setTrainObject(String trainObject) {
		this.trainObject = trainObject;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgFullName() {
		return orgFullName;
	}
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}
	public Date getTrainStart() {
		return trainStart;
	}
	public void setTrainStart(Date trainStart) {
		this.trainStart = trainStart;
	}
	public Date getTrainEnd() {
		return trainEnd;
	}
	public void setTrainEnd(Date trainEnd) {
		this.trainEnd = trainEnd;
	}
	public double getTrainPrice() {
		return trainPrice;
	}
	public void setTrainPrice(double trainPrice) {
		this.trainPrice = trainPrice;
	}
	public void setTrainPrice(String trainPrice) {
		this.trainPrice = Double.valueOf(trainPrice);
	}
	public String getTrainManner() {
		return trainManner;
	}
	public void setTrainManner(String trainManner) {
		this.trainManner = trainManner;
	}
	public String getTrainRemarks() {
		return trainRemarks;
	}
	public void setTrainRemarks(String trainRemarks) {
		this.trainRemarks = trainRemarks;
	}
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantCode() {
		return applicantCode;
	}
	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	public String getTrainDetail() {
		return trainDetail;
	}
	public void setTrainDetail(String trainDetail) {
		this.trainDetail = trainDetail;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	

}
