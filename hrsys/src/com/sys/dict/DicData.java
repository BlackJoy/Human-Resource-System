package com.sys.dict;

import java.io.Serializable;

public class DicData implements Serializable {
	private static final long serialVersionUID = -6916410294113622055L;
	private String id;
	private String dicDataCode;
	private String dicTypeId;
	private String dicDataName;
	private String dicDataComment;
	public DicData() {
	}
	public DicData(String dicDataCode, String dicTypeId, String dicDataName, String dicDataComment) {
		this.dicDataCode = dicDataCode;
		this.dicTypeId = dicTypeId;
		this.dicDataName = dicDataName;
		this.dicDataComment = dicDataComment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDicDataCode() {
		return dicDataCode;
	}
	public void setDicDataCode(String dicDataCode) {
		this.dicDataCode = dicDataCode;
	}
	public String getDicTypeId() {
		return dicTypeId;
	}
	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}
	public String getDicDataName() {
		return dicDataName;
	}
	public void setDicDataName(String dicDataName) {
		this.dicDataName = dicDataName;
	}
	public String getDicDataComment() {
		return dicDataComment;
	}
	public void setDicDataComment(String dicDataComment) {
		this.dicDataComment = dicDataComment;
	}
}
