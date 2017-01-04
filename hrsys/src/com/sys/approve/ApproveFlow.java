package com.sys.approve;

import java.io.Serializable;

/**
 * 审批流程类
 * @author Administrator
 *
 */
public class ApproveFlow implements Serializable {
	private static final long serialVersionUID = -6889158014274195095L;
	private String id;				//审批流程编号
	private String approveId;		//审批项目编号
	private Integer orderId;		//审批流程顺序号
	private String approverId;		//审批人编号
	private String approverName;	//审批人姓名
	private Integer approveType;	//审批类型
	public ApproveFlow(String approveId, Integer orderId, String approverId,
			String approverName, Integer approveType) {
		this.approveId = approveId;
		this.orderId = orderId;
		this.approverId = approverId;
		this.approverName = approverName;
		this.approveType = approveType;
	}
	public ApproveFlow() {
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
	@Override
	public String toString() {
		return "ApproveFlow [id=" + id + ", approveId=" + approveId
				+ ", orderId=" + orderId + ", approverId=" + approverId
				+ ", approverName=" + approverName + ", approveType="
				+ approveType + "]";
	}
}
