package com.sys.hr.empwage;

import java.util.Date;

/**
 * CmpnWageInfo entity. @author MyEclipse Persistence Tools
 */

public class CmpnWageInfo implements java.io.Serializable {

	// Fields

	private String empId;
	private String empNo;
	private String corp;
	private String dept;
	private String post;
	private Integer fraction;
	private String wageType;
	private String proFundCard;
	private String socCard;
	private String wageCardNo;
	private Date createDat;
	private String note;
	private String operNam;
	private Date operDte;
	private String empNm;
	private String empCd;
	private String sex;
	private String idCardNo;
	private Integer workAge;
	private String deptId;
	private String wageTypeId;
	
	

	// Constructors

	/** default constructor */
	public CmpnWageInfo() {
	}

	/** minimal constructor */
	public CmpnWageInfo(String empNo) {
		this.empNo = empNo;
	}

	/** full constructor */
	public CmpnWageInfo(String empNo, String corp, String dept, String post,
			Integer fraction, String wageType, String proFundCard,
			String socCard, String wageCardNo, Date createDat, String note,
			String operNam, Date operDte, String empNm, String empCd,
			String sex, String idCardNo, Integer workAge, String deptId) {
		this.empNo = empNo;
		this.corp = corp;
		this.dept = dept;
		this.post = post;
		this.fraction = fraction;
		this.wageType = wageType;
		this.proFundCard = proFundCard;
		this.socCard = socCard;
		this.wageCardNo = wageCardNo;
		this.createDat = createDat;
		this.note = note;
		this.operNam = operNam;
		this.operDte = operDte;
		this.empNm = empNm;
		this.empCd = empCd;
		this.sex = sex;
		this.idCardNo = idCardNo;
		this.workAge = workAge;
		this.deptId = deptId;
	}

	// Property accessors

	
	
	public String getEmpId() {
		return this.empId;
	}

	public String getWageTypeId() {
		return wageTypeId;
	}

	public void setWageTypeId(String wageTypeId) {
		this.wageTypeId = wageTypeId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getCorp() {
		return this.corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getFraction() {
		return this.fraction;
	}

	public void setFraction(Integer fraction) {
		this.fraction = fraction;
	}

	public String getWageType() {
		return this.wageType;
	}

	public void setWageType(String wageType) {
		this.wageType = wageType;
	}

	public String getProFundCard() {
		return this.proFundCard;
	}

	public void setProFundCard(String proFundCard) {
		this.proFundCard = proFundCard;
	}

	public String getSocCard() {
		return this.socCard;
	}

	public void setSocCard(String socCard) {
		this.socCard = socCard;
	}

	public String getWageCardNo() {
		return this.wageCardNo;
	}

	public void setWageCardNo(String wageCardNo) {
		this.wageCardNo = wageCardNo;
	}

	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOperNam() {
		return this.operNam;
	}

	public void setOperNam(String operNam) {
		this.operNam = operNam;
	}

	public Date getOperDte() {
		return this.operDte;
	}

	public void setOperDte(Date operDte) {
		this.operDte = operDte;
	}

	public String getEmpNm() {
		return this.empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getEmpCd() {
		return this.empCd;
	}

	public void setEmpCd(String empCd) {
		this.empCd = empCd;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCardNo() {
		return this.idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Integer getWorkAge() {
		return this.workAge;
	}

	public void setWorkAge(Integer workAge) {
		this.workAge = workAge;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}