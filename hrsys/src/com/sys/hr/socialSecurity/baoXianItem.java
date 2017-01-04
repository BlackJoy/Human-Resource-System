package com.sys.hr.socialSecurity;

public class baoXianItem {
	private String Id;
	private String curItem;
	
	public baoXianItem(){}
	
	public baoXianItem(String id, String curItem) {
		super();
		Id = id;
		this.curItem = curItem;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCurItem() {
		return curItem;
	}
	public void setCurItem(String curItem) {
		this.curItem = curItem;
	}
	
	
}
