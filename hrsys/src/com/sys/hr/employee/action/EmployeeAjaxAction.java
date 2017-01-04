package com.sys.hr.employee.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.sys.hr.employee.biz.IEmployeeBIZ;

public class EmployeeAjaxAction extends ActionSupport {

	private IEmployeeBIZ empBiz;
	
	private List emps;
	
	private String orgId;

	public IEmployeeBIZ getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(IEmployeeBIZ empBiz) {
		this.empBiz = empBiz;
	}

	public List getEmps() {
		return emps;
	}

	public void setEmps(List emps) {
		this.emps = emps;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getEmpsByOrgId() throws Exception {
		emps = empBiz.findEmpsByOrgId(orgId);
		return SUCCESS;
	}
}
