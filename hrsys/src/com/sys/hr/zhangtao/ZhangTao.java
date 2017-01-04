package com.sys.hr.zhangtao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sys.hr.employee.Employee;
import com.sys.hr.empwage.CmpnWageInfo;
import com.sys.hr.wageitem.WageItem;

public class ZhangTao {
	// Fields

		private String wageTypeId;
		private String wageTypeNo;
		private String deptNo;
		private String deptId;
		private Date createDat;
		private String wageTypeName;
		private String note;
		private Integer iactive;
		
		private Set<WageItem> wageItemSet = new HashSet<WageItem>();//帐套下的工资项 多对多
		private Set<CmpnWageInfo> empSet = new HashSet<CmpnWageInfo>();//帐套下的所有员工   一对多
		
		
		
 
		// Constructors

	

		public Set<WageItem> getWageItemSet() {
			return wageItemSet;
		}

		public Set<CmpnWageInfo> getEmpSet() {
			return empSet;
		}

		public void setEmpSet(Set<CmpnWageInfo> empSet) {
			this.empSet = empSet;
		}

		public void setWageItemSet(Set<WageItem> wageItemSet) {
			this.wageItemSet = wageItemSet;
		}

		/** default constructor */
		public ZhangTao() {
		}

		/** full constructor */
		public ZhangTao(String wageTypeNo, String deptNo, String deptId,
				Date createDat, String wageTypeName, String note, Integer iactive) {
			this.wageTypeNo = wageTypeNo;
			this.deptNo = deptNo;
			this.deptId = deptId;
			this.createDat = createDat;
			this.wageTypeName = wageTypeName;
			this.note = note;
			this.iactive = iactive;
		}

		// Property accessors

		public String getWageTypeId() {
			return this.wageTypeId;
		}

		public void setWageTypeId(String wageTypeId) {
			this.wageTypeId = wageTypeId;
		}

		public String getWageTypeNo() {
			return this.wageTypeNo;
		}

		public void setWageTypeNo(String wageTypeNo) {
			this.wageTypeNo = wageTypeNo;
		}

		public String getDeptNo() {
			return this.deptNo;
		}

		public void setDeptNo(String deptNo) {
			this.deptNo = deptNo;
		}

		public String getDeptId() {
			return this.deptId;
		}

		public void setDeptId(String deptId) {
			this.deptId = deptId;
		}

		public Date getCreateDat() {
			return this.createDat;
		}

		public void setCreateDat(Date createDat) {
			this.createDat = createDat;
		}

		public String getWageTypeName() {
			return this.wageTypeName;
		}

		public void setWageTypeName(String wageTypeName) {
			this.wageTypeName = wageTypeName;
		}

		public String getNote() {
			return this.note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public Integer getIactive() {
			return this.iactive;
		}

		public void setIactive(Integer iactive) {
			this.iactive = iactive;
		}
	

}
