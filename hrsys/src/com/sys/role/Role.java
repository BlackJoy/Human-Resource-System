package com.sys.role;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 2965681530294887122L;
	private String id;
	private String rolename;
	private String roledesc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
}
