package com.sys.hr.socialSecurity;

public class Contribution_Rate {
	private String CId;
	private String template_Id;
	private String e_Percent;
	private String p_Percent;
	private String curItem;
	
	public Contribution_Rate(){
		
	}
	public Contribution_Rate(String cId, String template_Id, String e_Percent,
			String p_Percent, String curItem) {
		super();
		CId = cId;
		this.template_Id = template_Id;
		this.e_Percent = e_Percent;
		this.p_Percent = p_Percent;
		this.curItem = curItem;
	}
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	
	public String getTemplate_Id() {
		return template_Id;
	}
	public void setTemplate_Id(String template_Id) {
		this.template_Id = template_Id;
	}
	public String getE_Percent() {
		return e_Percent;
	}
	public void setE_Percent(String e_Percent) {
		this.e_Percent = e_Percent;
	}
	public String getP_Percent() {
		return p_Percent;
	}
	public void setP_Percent(String p_Percent) {
		this.p_Percent = p_Percent;
	}
	public String getCurItem() {
		return curItem;
	}
	public void setCurItem(String curItem) {
		this.curItem = curItem;
	}
	
	
	
}
