package com.sys.approve;

import java.io.Serializable;

public class ApproveContent implements Serializable {

	private static final long serialVersionUID = 8785093457280511962L;
	private String id;
	private String name;
	private String desc;
	private String action;
	public ApproveContent(String id, String name, String desc, String action) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.action = action;
	}
	public ApproveContent() {
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
