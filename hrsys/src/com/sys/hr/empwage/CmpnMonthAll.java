package com.sys.hr.empwage;

import java.util.Date;

/**
 * CmpnMonthAll entity. @author MyEclipse Persistence Tools
 */

public class CmpnMonthAll implements java.io.Serializable {

	// Fields

	private String wagInfoId;
	private String empNo;
	private String empId;
	private String corp;
	private String dept;
	private String post;
	private Date createDat;
	private String operNam;
	private Date operDte;
	private String deptId;
	private Double wageItem13;
	private Double wageItem14;
	private Double wageItem15;
	private Double wageItem16;
	private Double wageItem17;
	private Double wageItem18;
	private Double wageItem19;
	private Double wageItem20;
	private Double wageItem21;
	private Double wageItem25;
	private Double wageItem26;
	private Double wageItem27;
	private Double wageItem29;
	private Double wageItem30;
	private Double wageItem31;
	private Double wageItem32;
	private Double wageItem33;
	private Double wageItem34;
	private Double wageItem35;
	private Double wageItem36;
	private Double wageItem37;
	private Double wageItem38;
	private Double wageItem39;
	private Double wageItem40;
	private Double wageItem41;
	private Double wageItem42;
	private Double wageItem43;
	private Double wageItem44;
	private Double wageItem45;
	private Double wageItem46;
	private Double wageItem47;
	private Double wageItem48;
	private Double wageItem49;
	private Double wageItem50;
	private Double wageItem51;
	private Double wageItem52;
	private Double wageItem53;
	private Double wageItem54;
	private Double wageItem55;
	private Double wageItem56;
	private Double wageItem57;
	private Double wageItem58;
	private Double wageItem60;
	private Double wageItem61;
	private Double wageItem62;
	private Double wageItem63;
	private Double wageItem64;
	private String wageTypeId;
	private Double wageItem66;
	private Double absenceWage;
	private Double assuranceWage;
	private String monthtime;

	// Constructors

	/** default constructor */
	public CmpnMonthAll() {
	}

	/** full constructor */
	public CmpnMonthAll(String empNo, String empId, String corp, String dept,
			String post, Date createDat, String operNam, Date operDte,
			String deptId, Double wageItem13, Double wageItem14,
			Double wageItem15, Double wageItem16, Double wageItem17,
			Double wageItem18, Double wageItem19, Double wageItem20,
			Double wageItem21, Double wageItem25, Double wageItem26,
			Double wageItem27, Double wageItem29, Double wageItem30,
			Double wageItem31, Double wageItem32, Double wageItem33,
			Double wageItem34, Double wageItem35, Double wageItem36,
			Double wageItem37, Double wageItem38, Double wageItem39,
			Double wageItem40, Double wageItem41, Double wageItem42,
			Double wageItem43, Double wageItem44, Double wageItem45,
			Double wageItem46, Double wageItem47, Double wageItem48,
			Double wageItem49, Double wageItem50, Double wageItem51,
			Double wageItem52, Double wageItem53, Double wageItem54,
			Double wageItem55, Double wageItem56, Double wageItem57,
			Double wageItem58, Double wageItem60, Double wageItem61,
			Double wageItem62, Double wageItem63, Double wageItem64,
			String wageTypeId, Double wageItem66, Double absenceWage,
			Double assuranceWage, String monthtime) {
		this.empNo = empNo;
		this.empId = empId;
		this.corp = corp;
		this.dept = dept;
		this.post = post;
		this.createDat = createDat;
		this.operNam = operNam;
		this.operDte = operDte;
		this.deptId = deptId;
		this.wageItem13 = wageItem13;
		this.wageItem14 = wageItem14;
		this.wageItem15 = wageItem15;
		this.wageItem16 = wageItem16;
		this.wageItem17 = wageItem17;
		this.wageItem18 = wageItem18;
		this.wageItem19 = wageItem19;
		this.wageItem20 = wageItem20;
		this.wageItem21 = wageItem21;
		this.wageItem25 = wageItem25;
		this.wageItem26 = wageItem26;
		this.wageItem27 = wageItem27;
		this.wageItem29 = wageItem29;
		this.wageItem30 = wageItem30;
		this.wageItem31 = wageItem31;
		this.wageItem32 = wageItem32;
		this.wageItem33 = wageItem33;
		this.wageItem34 = wageItem34;
		this.wageItem35 = wageItem35;
		this.wageItem36 = wageItem36;
		this.wageItem37 = wageItem37;
		this.wageItem38 = wageItem38;
		this.wageItem39 = wageItem39;
		this.wageItem40 = wageItem40;
		this.wageItem41 = wageItem41;
		this.wageItem42 = wageItem42;
		this.wageItem43 = wageItem43;
		this.wageItem44 = wageItem44;
		this.wageItem45 = wageItem45;
		this.wageItem46 = wageItem46;
		this.wageItem47 = wageItem47;
		this.wageItem48 = wageItem48;
		this.wageItem49 = wageItem49;
		this.wageItem50 = wageItem50;
		this.wageItem51 = wageItem51;
		this.wageItem52 = wageItem52;
		this.wageItem53 = wageItem53;
		this.wageItem54 = wageItem54;
		this.wageItem55 = wageItem55;
		this.wageItem56 = wageItem56;
		this.wageItem57 = wageItem57;
		this.wageItem58 = wageItem58;
		this.wageItem60 = wageItem60;
		this.wageItem61 = wageItem61;
		this.wageItem62 = wageItem62;
		this.wageItem63 = wageItem63;
		this.wageItem64 = wageItem64;
		this.wageTypeId = wageTypeId;
		this.wageItem66 = wageItem66;
		this.absenceWage = absenceWage;
		this.assuranceWage = assuranceWage;
		this.monthtime = monthtime;
	}

	// Property accessors

	public String getWagInfoId() {
		return this.wagInfoId;
	}

	public void setWagInfoId(String wagInfoId) {
		this.wagInfoId = wagInfoId;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public Date getCreateDat() {
		return this.createDat;
	}

	public void setCreateDat(Date createDat) {
		this.createDat = createDat;
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

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Double getWageItem13() {
		return this.wageItem13;
	}

	public void setWageItem13(Double wageItem13) {
		this.wageItem13 = wageItem13;
	}

	public Double getWageItem14() {
		return this.wageItem14;
	}

	public void setWageItem14(Double wageItem14) {
		this.wageItem14 = wageItem14;
	}

	public Double getWageItem15() {
		return this.wageItem15;
	}

	public void setWageItem15(Double wageItem15) {
		this.wageItem15 = wageItem15;
	}

	public Double getWageItem16() {
		return this.wageItem16;
	}

	public void setWageItem16(Double wageItem16) {
		this.wageItem16 = wageItem16;
	}

	public Double getWageItem17() {
		return this.wageItem17;
	}

	public void setWageItem17(Double wageItem17) {
		this.wageItem17 = wageItem17;
	}

	public Double getWageItem18() {
		return this.wageItem18;
	}

	public void setWageItem18(Double wageItem18) {
		this.wageItem18 = wageItem18;
	}

	public Double getWageItem19() {
		return this.wageItem19;
	}

	public void setWageItem19(Double wageItem19) {
		this.wageItem19 = wageItem19;
	}

	public Double getWageItem20() {
		return this.wageItem20;
	}

	public void setWageItem20(Double wageItem20) {
		this.wageItem20 = wageItem20;
	}

	public Double getWageItem21() {
		return this.wageItem21;
	}

	public void setWageItem21(Double wageItem21) {
		this.wageItem21 = wageItem21;
	}

	public Double getWageItem25() {
		return this.wageItem25;
	}

	public void setWageItem25(Double wageItem25) {
		this.wageItem25 = wageItem25;
	}

	public Double getWageItem26() {
		return this.wageItem26;
	}

	public void setWageItem26(Double wageItem26) {
		this.wageItem26 = wageItem26;
	}

	public Double getWageItem27() {
		return this.wageItem27;
	}

	public void setWageItem27(Double wageItem27) {
		this.wageItem27 = wageItem27;
	}

	public Double getWageItem29() {
		return this.wageItem29;
	}

	public void setWageItem29(Double wageItem29) {
		this.wageItem29 = wageItem29;
	}

	public Double getWageItem30() {
		return this.wageItem30;
	}

	public void setWageItem30(Double wageItem30) {
		this.wageItem30 = wageItem30;
	}

	public Double getWageItem31() {
		return this.wageItem31;
	}

	public void setWageItem31(Double wageItem31) {
		this.wageItem31 = wageItem31;
	}

	public Double getWageItem32() {
		return this.wageItem32;
	}

	public void setWageItem32(Double wageItem32) {
		this.wageItem32 = wageItem32;
	}

	public Double getWageItem33() {
		return this.wageItem33;
	}

	public void setWageItem33(Double wageItem33) {
		this.wageItem33 = wageItem33;
	}

	public Double getWageItem34() {
		return this.wageItem34;
	}

	public void setWageItem34(Double wageItem34) {
		this.wageItem34 = wageItem34;
	}

	public Double getWageItem35() {
		return this.wageItem35;
	}

	public void setWageItem35(Double wageItem35) {
		this.wageItem35 = wageItem35;
	}

	public Double getWageItem36() {
		return this.wageItem36;
	}

	public void setWageItem36(Double wageItem36) {
		this.wageItem36 = wageItem36;
	}

	public Double getWageItem37() {
		return this.wageItem37;
	}

	public void setWageItem37(Double wageItem37) {
		this.wageItem37 = wageItem37;
	}

	public Double getWageItem38() {
		return this.wageItem38;
	}

	public void setWageItem38(Double wageItem38) {
		this.wageItem38 = wageItem38;
	}

	public Double getWageItem39() {
		return this.wageItem39;
	}

	public void setWageItem39(Double wageItem39) {
		this.wageItem39 = wageItem39;
	}

	public Double getWageItem40() {
		return this.wageItem40;
	}

	public void setWageItem40(Double wageItem40) {
		this.wageItem40 = wageItem40;
	}

	public Double getWageItem41() {
		return this.wageItem41;
	}

	public void setWageItem41(Double wageItem41) {
		this.wageItem41 = wageItem41;
	}

	public Double getWageItem42() {
		return this.wageItem42;
	}

	public void setWageItem42(Double wageItem42) {
		this.wageItem42 = wageItem42;
	}

	public Double getWageItem43() {
		return this.wageItem43;
	}

	public void setWageItem43(Double wageItem43) {
		this.wageItem43 = wageItem43;
	}

	public Double getWageItem44() {
		return this.wageItem44;
	}

	public void setWageItem44(Double wageItem44) {
		this.wageItem44 = wageItem44;
	}

	public Double getWageItem45() {
		return this.wageItem45;
	}

	public void setWageItem45(Double wageItem45) {
		this.wageItem45 = wageItem45;
	}

	public Double getWageItem46() {
		return this.wageItem46;
	}

	public void setWageItem46(Double wageItem46) {
		this.wageItem46 = wageItem46;
	}

	public Double getWageItem47() {
		return this.wageItem47;
	}

	public void setWageItem47(Double wageItem47) {
		this.wageItem47 = wageItem47;
	}

	public Double getWageItem48() {
		return this.wageItem48;
	}

	public void setWageItem48(Double wageItem48) {
		this.wageItem48 = wageItem48;
	}

	public Double getWageItem49() {
		return this.wageItem49;
	}

	public void setWageItem49(Double wageItem49) {
		this.wageItem49 = wageItem49;
	}

	public Double getWageItem50() {
		return this.wageItem50;
	}

	public void setWageItem50(Double wageItem50) {
		this.wageItem50 = wageItem50;
	}

	public Double getWageItem51() {
		return this.wageItem51;
	}

	public void setWageItem51(Double wageItem51) {
		this.wageItem51 = wageItem51;
	}

	public Double getWageItem52() {
		return this.wageItem52;
	}

	public void setWageItem52(Double wageItem52) {
		this.wageItem52 = wageItem52;
	}

	public Double getWageItem53() {
		return this.wageItem53;
	}

	public void setWageItem53(Double wageItem53) {
		this.wageItem53 = wageItem53;
	}

	public Double getWageItem54() {
		return this.wageItem54;
	}

	public void setWageItem54(Double wageItem54) {
		this.wageItem54 = wageItem54;
	}

	public Double getWageItem55() {
		return this.wageItem55;
	}

	public void setWageItem55(Double wageItem55) {
		this.wageItem55 = wageItem55;
	}

	public Double getWageItem56() {
		return this.wageItem56;
	}

	public void setWageItem56(Double wageItem56) {
		this.wageItem56 = wageItem56;
	}

	public Double getWageItem57() {
		return this.wageItem57;
	}

	public void setWageItem57(Double wageItem57) {
		this.wageItem57 = wageItem57;
	}

	public Double getWageItem58() {
		return this.wageItem58;
	}

	public void setWageItem58(Double wageItem58) {
		this.wageItem58 = wageItem58;
	}

	public Double getWageItem60() {
		return this.wageItem60;
	}

	public void setWageItem60(Double wageItem60) {
		this.wageItem60 = wageItem60;
	}

	public Double getWageItem61() {
		return this.wageItem61;
	}

	public void setWageItem61(Double wageItem61) {
		this.wageItem61 = wageItem61;
	}

	public Double getWageItem62() {
		return this.wageItem62;
	}

	public void setWageItem62(Double wageItem62) {
		this.wageItem62 = wageItem62;
	}

	public Double getWageItem63() {
		return this.wageItem63;
	}

	public void setWageItem63(Double wageItem63) {
		this.wageItem63 = wageItem63;
	}

	public Double getWageItem64() {
		return this.wageItem64;
	}

	public void setWageItem64(Double wageItem64) {
		this.wageItem64 = wageItem64;
	}

	public String getWageTypeId() {
		return this.wageTypeId;
	}

	public void setWageTypeId(String wageTypeId) {
		this.wageTypeId = wageTypeId;
	}

	public Double getWageItem66() {
		return this.wageItem66;
	}

	public void setWageItem66(Double wageItem66) {
		this.wageItem66 = wageItem66;
	}

	public Double getAbsenceWage() {
		return this.absenceWage;
	}

	public void setAbsenceWage(Double absenceWage) {
		this.absenceWage = absenceWage;
	}

	public Double getAssuranceWage() {
		return this.assuranceWage;
	}

	public void setAssuranceWage(Double assuranceWage) {
		this.assuranceWage = assuranceWage;
	}

	public String getMonthtime() {
		return this.monthtime;
	}

	public void setMonthtime(String monthtime) {
		this.monthtime = monthtime;
	}

}