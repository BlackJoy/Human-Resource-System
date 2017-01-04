package com.sys.hr.org;

import java.util.ArrayList;
import java.util.List;

public class Post {

	private String id;
	private String postCod;
	private String postName;
	private String postType;
	private String postDuty;
	private String iskey;
	private String orgid;
	
	public Post(String id, String postCod, String postName, String postType,
			String postDuty, String iskey, String orgid) {
		super();
		this.id = id;
		this.postCod = postCod;
		this.postName = postName;
		this.postType = postType;
		this.postDuty = postDuty;
		this.iskey = iskey;
		this.orgid = orgid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPostCod() {
		return postCod;
	}

	public void setPostCod(String postCod) {
		this.postCod = postCod;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPostDuty() {
		return postDuty;
	}

	public void setPostDuty(String postDuty) {
		this.postDuty = postDuty;
	}

	public String getIskey() {
		return iskey;
	}

	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Post() {
	}
	
}
