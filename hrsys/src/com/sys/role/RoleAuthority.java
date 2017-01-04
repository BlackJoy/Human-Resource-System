package com.sys.role;

import java.io.Serializable;

/**
 * ╫ги╚х╗оч
 * @author Administrator
 *
 */
public class RoleAuthority implements Serializable {
	private static final long serialVersionUID = -964689959912419575L;
	private String id;
	private String roleId;
	private String authorityId;
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
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	
}
