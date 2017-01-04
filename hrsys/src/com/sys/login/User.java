package com.sys.login;

import java.io.Serializable;

/**
 * µÇÂ¼ÓÃ»§Àà
 * @author Administrator
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7111775017347705859L;
	private String id;
	private String username;
	private String password;
	private String employeeId;
	private int enable;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
}
