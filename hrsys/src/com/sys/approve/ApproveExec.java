package com.sys.approve;

import java.io.Serializable;
import java.util.Date;

public class ApproveExec implements Serializable {

	private static final long serialVersionUID = -485845196939802719L;
	private String id;
	private String approveId;
	private String approveName;
	private Date startTime;
	private Date endTime;
	private Integer status;
	private String contentURL;
	private String contentID;
	private Integer isoverandsave;
	public ApproveExec() {
	}
	public ApproveExec(String approveId, String approveName, Date startTime,
			Date endTime, Integer status, String contentURL, String contentID, Integer isoverandsave) {
		this.approveId = approveId;
		this.approveName = approveName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.contentURL = contentURL;
		this.contentID = contentID;
		this.isoverandsave = isoverandsave;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApproveId() {
		return approveId;
	}
	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}
	public String getApproveName() {
		return approveName;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getContentURL() {
		return contentURL;
	}
	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}
	public String getContentID() {
		return contentID;
	}
	public void setContentID(String contentID) {
		this.contentID = contentID;
	}
	public Integer getIsoverandsave() {
		return isoverandsave;
	}
	public void setIsoverandsave(Integer isoverandsave) {
		this.isoverandsave = isoverandsave;
	}
}
