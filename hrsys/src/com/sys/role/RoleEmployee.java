package com.sys.role;

import java.io.Serializable;

public class RoleEmployee implements Serializable {

	private static final long serialVersionUID = 2378854304221935748L;
	private String id;
	private String roleId;
	private String employeeId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
