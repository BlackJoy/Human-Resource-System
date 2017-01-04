package com.sys.hr.wageitem;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sys.hr.zhangtao.ZhangTao;

/**
 * WageItem entity. @author MyEclipse Persistence Tools
 */

public class WageItem implements java.io.Serializable {

	// Fields

	private String wageId;
	private String wageNo;
	private String algoEn;
	private String algo;
	private String wageCodeNo;
	private String varNm;
	private Integer varSn;
	private Date createDat;
	private String note;
	private Integer reduceFlag;
	private String operNam;
	private Date operDte;
	private Integer iactive;
	private Integer type;
	private String wageName;
	
	private Set<ZhangTao> zhangtaoSet = new HashSet<ZhangTao>();
	

	// Constructors

	public Set<ZhangTao> getZhangtaoSet() {
		return zhangtaoSet;
	}

	public void setZhangtaoSet(Set<ZhangTao> zhangtaoSet) {
		this.zhangtaoSet = zhangtaoSet;
	}

	/** default constructor */
	public WageItem() {
	}

	/** full constructor */
	public WageItem(String wageNo, String algoEn, String algo,
			String wageCodeNo, String varNm, Integer varSn, Date createDat,
			String note, Integer reduceFlag, String operNam, Date operDte,
			Integer iactive, Integer type, String wageName) {
		this.wageNo = wageNo;
		this.algoEn = algoEn;
		this.algo = algo;
		this.wageCodeNo = wageCodeNo;
		this.varNm = varNm;
		this.varSn = varSn;
		this.createDat = createDat;
		this.note = note;
		this.reduceFlag = reduceFlag;
		this.operNam = operNam;
		this.operDte = operDte;
		this.iactive = iactive;
		this.type = type;
		this.wageName = wageName;
	}

	// Property accessors

	public String getWageId() {
		return this.wageId;
	}

	public void setWageId(String wageId) {
		this.wageId = wageId;
	}

	public String getWageNo() {
		return this.wageNo;
	}

	public void setWageNo(String wageNo) {
		this.wageNo = wageNo;
	}

	public String getAlgoEn() {
		return this.algoEn;
	}

	public void setAlgoEn(String algoEn) {
		this.algoEn = algoEn;
	}

	public String getAlgo() {
		return this.algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public String getWageCodeNo() {
		return this.wageCodeNo;
	}

	public void setWageCodeNo(String wageCodeNo) {
		this.wageCodeNo = wageCodeNo;
	}

	public String getVarNm() {
		return this.varNm;
	}

	public void setVarNm(String varNm) {
		this.varNm = varNm;
	}

	public Integer getVarSn() {
		return this.varSn;
	}

	public void setVarSn(Integer varSn) {
		this.varSn = varSn;
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

	public Integer getReduceFlag() {
		return this.reduceFlag;
	}

	public void setReduceFlag(Integer reduceFlag) {
		this.reduceFlag = reduceFlag;
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

	public Integer getIactive() {
		return this.iactive;
	}

	public void setIactive(Integer iactive) {
		this.iactive = iactive;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getWageName() {
		return this.wageName;
	}

	public void setWageName(String wageName) {
		this.wageName = wageName;
	}

}