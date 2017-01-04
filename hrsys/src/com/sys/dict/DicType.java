package com.sys.dict;

import java.io.Serializable;

public class DicType implements Serializable {
	private static final long serialVersionUID = 3137659352100103511L;
	private String id;
	private String dicTypeCode;
	private String dicTypeName;
	private String dicTypeComment;
	public DicType() {
	}
	public DicType(String dicTypeCode, String dicTypeName, String dicTypeComment) {
		this.dicTypeCode = dicTypeCode;
		this.dicTypeName = dicTypeName;
		this.dicTypeComment = dicTypeComment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDicTypeCode() {
		return dicTypeCode;
	}
	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}
	public String getDicTypeName() {
		return dicTypeName;
	}
	public void setDicTypeName(String dicTypeName) {
		this.dicTypeName = dicTypeName;
	}
	public String getDicTypeComment() {
		return dicTypeComment;
	}
	public void setDicTypeComment(String dicTypeComment) {
		this.dicTypeComment = dicTypeComment;
	}
}
