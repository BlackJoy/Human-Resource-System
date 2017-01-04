package com.sys.hr.socialSecurity;

import java.util.ArrayList;
import java.util.List;

import com.sys.hr.employee.Employee;

public class SocSecrityInfo {
	private String id;
	private String employeeCode;
	private String employeeName;
	private String ssId;
	private String foundId;
	private String remark;
	private String orgid;
	
	
	
	
	public SocSecrityInfo(){}
	
	
	

	
	public SocSecrityInfo(String id, String employeeCode, String employeeName,
			String ssId, String foundId, String remark, String orgid) {
		super();
		this.id = id;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.ssId = ssId;
		this.foundId = foundId;
		this.remark = remark;
		this.orgid = orgid;
	}





	public String getEmployeeCode() {
		return employeeCode;
	}





	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}





	public String getEmployeeName() {
		return employeeName;
	}





	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}





	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getFoundId() {
		return foundId;
	}

	public void setFoundId(String foundId) {
		this.foundId = foundId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgId) {
		this.orgid = orgId;
	}
	
	
	
}
