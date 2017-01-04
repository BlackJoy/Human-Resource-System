package com.sys.hr.emp2position;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工职位
 * @author Administrator
 *
 */
public class Emp2Position implements Serializable {

	private static final long serialVersionUID = -8056380484642204042L;
	private String id;
	private String employeeId;		//员工ID
	private String orgId;			//组织ID
	private String positionId;		//职位ID
	private Integer positionType;	//职位类型（1.主职、2.兼职）
	private Date startTime;		//开始时间
	private Date endTime;			//结束时间
	private Integer positionStatus;	//职位状态（1.聘、2.免）
	public Emp2Position() {
	}
	public Emp2Position(String employeeId, String orgId, String positionId,
			Integer positionType, Date startTime, Date endTime,
			Integer positionStatus) {
		this.employeeId = employeeId;
		this.orgId = orgId;
		this.positionId = positionId;
		this.positionType = positionType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.positionStatus = positionStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public Integer getPositionType() {
		return positionType;
	}
	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
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
	public Integer getPositionStatus() {
		return positionStatus;
	}
	public void setPositionStatus(Integer positionStatus) {
		this.positionStatus = positionStatus;
	}
}
