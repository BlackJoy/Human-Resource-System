package com.sys.authority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Authority implements Comparable<Authority>{

	private String id;
	private String parentId;
	private String menuId;
	private String name;
	private String desc;
	private List<Authority> subAuthority = new ArrayList<Authority>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	public List<Authority> getSubAuthority() {
		return subAuthority;
	}
	public void setSubAuthority(List<Authority> subAuthority) {
		this.subAuthority = subAuthority;
	}
	/**
	 * 增加子权限
	 * @param authority
	 * @return
	 */
	public boolean addAuthority(Authority authority) {
		return this.getSubAuthority().add(authority);
	}

	public int compareTo(Authority authority) {
		int this_id = Integer.parseInt(this.getId().substring(this.getId().lastIndexOf('.') + 1));
		int authority_id = Integer.parseInt(authority.getId().substring(authority.getId().lastIndexOf('.') + 1));
		return this_id - authority_id;
	}
	
	/**
	 * 子权限排序
	 */
	public void sortAuthority() {
		Collections.sort(this.getSubAuthority());
	}
}
