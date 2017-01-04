package com.sys.hr.train;

public class TrainAuth {
 private String id;
 private String orgId;
 private String trainId;
 private String trainName;
 private String  whichIdea;
 private String status;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public String getTrainId() {
	return trainId;
}
public void setTrainId(String trainId) {
	this.trainId = trainId;
}
public String getTrainName() {
	return trainName;
}
public void setTrainName(String trainName) {
	this.trainName = trainName;
}
public String getWhichIdea() {
	return whichIdea;
}
public void setWhichIdea(String whichIdea) {
	this.whichIdea = whichIdea;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public TrainAuth(String id, String orgId, String trainId, String trainName,
		String whichIdea, String status) {
	super();
	this.id = id;
	this.orgId = orgId;
	this.trainId = trainId;
	this.trainName = trainName;
	this.whichIdea = whichIdea;
	this.status = status;
}
public TrainAuth() {
	super();
	// TODO Auto-generated constructor stub
}
 
 
 
}
