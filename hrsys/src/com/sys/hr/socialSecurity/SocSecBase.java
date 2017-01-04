package com.sys.hr.socialSecurity;

import java.util.ArrayList;
import java.util.List;

import com.sys.hr.employee.Employee;

public class SocSecBase {
	private String id;
	private String employeeCode;
	private String employeeName;
	private String ssId;
	private String pFound;
	private String aFound;
	private String templateId;
	private String safetyBase;
	private String orgId;
	private String  curItem;
	
	
	
	
	public SocSecBase(){}

	public SocSecBase(String id, String employeeCode, String employeeName,
			String ssId, String pFound, String aFound, String templateId,
			String safetyBase, String orgId) {
		super();
		this.id = id;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.ssId = ssId;
		this.pFound = pFound;
		this.aFound = aFound;
		this.templateId = templateId;
		this.safetyBase = safetyBase;
		this.orgId = orgId;
	}






	public String getCurItem() {
		return curItem;
	}

	public void setCurItem(String curItem) {
		this.curItem = curItem;
	}

	public String getpFound() {
		return pFound;
	}






	public void setpFound(String pFound) {
		this.pFound = pFound;
	}






	public String getaFound() {
		return aFound;
	}






	public void setaFound(String aFound) {
		this.aFound = aFound;
	}






	public String getTemplateId() {
		return templateId;
	}






	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}






	public String getSafetyBase() {
		return safetyBase;
	}






	public void setSafetyBase(String safetyBase) {
		this.safetyBase = safetyBase;
	}






	public String getOrgId() {
		return orgId;
	}






	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	
	
	
}
