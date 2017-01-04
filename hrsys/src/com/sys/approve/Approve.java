package com.sys.approve;

import java.io.Serializable;

/**
 * ������
 * @author Administrator
 *
 */
public class Approve implements Serializable {
	private static final long serialVersionUID = -3704726779148327336L;
	private String id;			//�������
	private String name;		//������Ŀ��
	private Integer timeLimit;	//��������
	private String approveContent;
	public Approve(String name, Integer timeLimit, String approveContent) {
		this.name = name;
		this.timeLimit = timeLimit;
		this.approveContent = approveContent;
	}
	public Approve() {
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
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getApproveContent() {
		return approveContent;
	}
	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}
}
