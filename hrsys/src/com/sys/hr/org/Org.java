package com.sys.hr.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Org {

	private String id;
	private String orgCode;
	private String orgFullName;
	private String orgShortName;
	private Integer orgType;
	private Integer orgStatus;
	private String orgParentId;
	private List<Org> orgList = new ArrayList<Org>();
	private Set<Post> orgPosts;
	public Set<Post> getOrgPosts() {
		return orgPosts;
	}
	public void setOrgPosts(Set<Post> orgPosts) {
		this.orgPosts = orgPosts;
	}
	public Org() {
	}
	public Org(String orgCode, String orgFullName, String orgShortName,
			Integer orgType, Integer orgStatus, String orgParentId) {
		this.orgCode = orgCode;
		this.orgFullName = orgFullName;
		this.orgShortName = orgShortName;
		this.orgType = orgType;
		this.orgStatus = orgStatus;
		this.orgParentId = orgParentId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgFullName() {
		return orgFullName;
	}
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	public Integer getOrgType() {
		return orgType;
	}
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	public Integer getOrgStatus() {
		return orgStatus;
	}
	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}
	public String getOrgParentId() {
		return orgParentId;
	}
	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}
	public List<Org> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<Org> orgList) {
		this.orgList = orgList;
	}
	public boolean addSubOrg(Org sub){
		return this.getOrgList().add(sub);
	}
}
