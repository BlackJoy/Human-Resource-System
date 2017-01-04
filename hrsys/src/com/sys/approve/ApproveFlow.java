package com.sys.approve;

import java.io.Serializable;

/**
 * ����������
 * @author Administrator
 *
 */
public class ApproveFlow implements Serializable {
	private static final long serialVersionUID = -6889158014274195095L;
	private String id;				//�������̱��
	private String approveId;		//������Ŀ���
	private Integer orderId;		//��������˳���
	private String approverId;		//�����˱��
	private String approverName;	//����������
	private Integer approveType;	//��������
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
