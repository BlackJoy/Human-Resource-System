package com.sys.approve;

import java.io.Serializable;
import java.util.Date;

public class ApproveFlowExec implements Serializable {

	private static final long serialVersionUID = 9171390252936424876L;
	private String id;
	private String approveExecId;
	private String approveFlowId;
	private Integer orderId;
	private String approverId;
	private String approverName;
	private Integer approveType;
	private Integer status;
	private Date approveTime;
	private String approveComment;
	public ApproveFlowExec() {
	}
	public ApproveFlowExec(String approveExecId, String approveFlowId,
			Integer orderId, String approverId, String approverName,
			Integer status, Date approveTime, String approveComment) {
		this.approveExecId = approveExecId;
		this.approveFlowId = approveFlowId;
		this.orderId = orderId;
		this.approverId = approverId;
		this.approverName = approverName;
		this.status = status;
		this.approveTime = approveTime;
		this.approveComment = approveComment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApproveExecId() {
		return approveExecId;
	}
	public void setApproveExecId(String approveExecId) {
		this.approveExecId = approveExecId;
	}
	public String getApproveFlowId() {
		return approveFlowId;
	}
	public void setApproveFlowId(String approveFlowId) {
		this.approveFlowId = approveFlowId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public Integer getApproveType() {
		return approveType;
	}
	public void setApproveType(Integer approveType) {
		this.approveType = approveType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public String getApproveComment() {
		return approveComment;
	}
	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}
}
