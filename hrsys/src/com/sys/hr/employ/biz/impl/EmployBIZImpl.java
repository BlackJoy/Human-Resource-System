package com.sys.hr.employ.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sys.approve.ApproveExec;
import com.sys.common.Page;
import com.sys.hr.emp2position.Emp2Position;
import com.sys.hr.employ.Employ;
import com.sys.hr.employ.biz.IEmployBIZ;
import com.sys.hr.employ.dao.IEmployDAO;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;

public class EmployBIZImpl implements IEmployBIZ {

	private IEmployDAO employDao;

	public IEmployDAO getEmployDao() {
		return employDao;
	}

	public void setEmployDao(IEmployDAO employDao) {
		this.employDao = employDao;
	}

	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org) employDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
		return employDao.findYingPinByOrgId(orgId, pageIndex, pageSize);
	}

	public Org findOrgById(String orgId) throws Exception {
		return (Org) employDao.findById(Org.class, orgId);
	}

	public void saveYingPin(Employ employ) throws Exception {
		employDao.save(employ);
	}

	public Map findYingPinById(String id) throws Exception {
		Map yp = null;
		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po, to_char(e.yingpintime,'yyyy-mm-dd') ypt from TBL_employ e "
				+ "left join Tbl_Dicdata d "
				+ "on e.education=d.id "
				+ "left join Tbl_Dicdata d1 "
				+ "on e.position=d1.id "
				+ "inner join TBL_ORG o "
				+ "on e.orgid=o.id "
				+ "where e.id='"
				+ id + "'";
		List<Map> yplist = employDao.findSQL2MapList(sql);
		if (yplist != null && yplist.size() > 0) {
			yp = yplist.get(0);
		}
		return yp;
	}

	public void updateYingPin(Employ employ) throws Exception {
		employDao.update(employ);
	}

	public void deleteYingPin(String id) throws Exception {
		Object e = employDao.findById(Employ.class, id);
		if (e != null) {
			employDao.delete(e);
		}
	}

//	public boolean updateToShiYong(String id, Date shiyongTime)
//			throws Exception {
//		boolean b = false;
//		Employ e = (Employ) employDao.findById(Employ.class, id);
//		if (e.getEmployStatus() == 1) {
//			e.setShiyongTime(shiyongTime);
//			e.setEmployStatus(2);
//			employDao.update(e);
//			b = true;
//		}
//		return b;
//	}

	public boolean updateToShiYong(String id, Date shiyongTime)
			throws Exception {
		boolean b = false;
		Employ e = (Employ) employDao.findById(Employ.class, id);
		if (e.getEmployStatus() == 1) {
			e.setShiyongTime(shiyongTime);
			e.setEmployStatus(2);
			employDao.update(e);
			
			Employee emp = new Employee();
			emp.setEmployeeName(e.getName());
			emp.setOrgid(e.getOrgid());
			emp.setGender(e.getGender());
			emp.setIdentityCard(e.getIdentityCard());
			emp.setEducation(e.getEducation());
			emp.setSpecialty(e.getSpecialty());
			emp.setGraduateSchool(e.getGraduateSchool());
			emp.setAddress(e.getAddress());
			emp.setPhone(e.getPhone());
			emp.setShiyongTime(shiyongTime);
			emp.setEmployeeStatus(2);
			emp.setEmployID(id);
			String empId = (String)employDao.save(emp);
			
			Emp2Position e2p = new Emp2Position();
			e2p.setEmployeeId(empId);
			e2p.setOrgId(e.getOrgid());
			e2p.setPositionId(e.getPosition());
			e2p.setPositionType(1);
			e2p.setStartTime(shiyongTime);
			e2p.setPositionStatus(1);
			employDao.save(e2p);
			b = true;
		}
		return b;
	}

	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org) employDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
		return employDao.findLuYongByOrgId(orgId, pageIndex, pageSize);
	}
	
	public Map findLuYongById(String id) throws Exception {
		Map yp = null;
		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po, to_char(e.shiyongtime,'yyyy-mm-dd') lyt from " +
				"(select * from (select * from Tbl_Employee e where e.employeestatus=2) e " +
				"inner join (select e2p.employee_id, e2p.org_id, e2p.position_id position from Tbl_Employee2position e2p where e2p.positiontype=1 and e2p.positionstatus=1) e2p " +
				"on e.id=e2p.employee_id) e " +
				"left join Tbl_Dicdata d " +
				"on e.education=d.id " +
				"left join Tbl_Dicdata d1 " +
				"on e.position=d1.id " +
				"inner join TBL_ORG o " +
				"on e.orgid=o.id " +
				"where e.id='"+id+"'";
		List<Map> yplist = employDao.findSQL2MapList(sql);
		if (yplist != null && yplist.size() > 0) {
			yp = yplist.get(0);
		}
		return yp;
	}

	public void updateLuYong(Employee employee) throws Exception {
		employDao.update(employee);
	}

	public boolean updateToRuZhi(String id, Date ruzhiTime) throws Exception {
		Employee e = (Employee) employDao.findById(Employee.class, id);
		if (e.getEmployeeStatus() == 2) {
			e.setRuzhiTime(ruzhiTime);
			e.setEmployeeStatus(3);
			employDao.update(e);
			return true;
		}
		return false;
	}

	public boolean updateToYingPin(String id) throws Exception {
		Employee emp = (Employee) employDao.findById(Employee.class, id);
		if (emp.getEmployeeStatus() == 2) {
			Employ e = (Employ) employDao.findById(Employ.class, emp.getEmployID());
			e.setShiyongTime(null);
			e.setEmployStatus(1);
			employDao.update(e);
			
			String sql = "update TBL_EMPLOYEE2POSITION e2p set e2p.positionstatus=2 where e2p.employee_id='"+id+"'";
			employDao.updateSQL(sql);
			
			employDao.delete(emp);
			return true;
		}
		return false;
	}

	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org) employDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
		return employDao.findRuZhiByOrgId(orgId, pageIndex, pageSize);
	}

	public boolean updateToLuYong(String id) throws Exception {
		Employee e = (Employee) employDao.findById(Employee.class, id);
		if (e.getEmployeeStatus() == 3) {
			e.setRuzhiTime(null);
			e.setEmployeeStatus(2);
			employDao.update(e);
			return true;
		}
		return false;
	}

	public List findRuZhiById(String id) throws Exception {
		return employDao.findRuZhiById(id);
	}

	public boolean updateToRuZhiApprove(String id) throws Exception {
		Employee e = (Employee) employDao.findById(Employee.class, id);
		if (e.getEmployeeStatus() == 3) {
			e.setEmployeeStatus(4);
			employDao.update(e);
			return true;
		}
		return false;
	}

	public boolean flowOverAndSaveData(String id) throws Exception {
		ApproveExec ae = (ApproveExec) employDao.findById(ApproveExec.class, id);
		if (ae.getStatus() == 3 && ae.getIsoverandsave() == 1) {
			Employee e = (Employee) employDao.findById(Employee.class, ae.getContentID());
			e.setEmployeeStatus(5);
			employDao.update(e);
			ae.setIsoverandsave(2);
			employDao.update(ae);
			return true;
		}
		if (ae.getStatus() == 2 && ae.getIsoverandsave() == 1) {
			Employee e = (Employee) employDao.findById(Employee.class, ae.getContentID());
			e.setEmployeeStatus(2);
			e.setRuzhiTime(null);
			employDao.update(e);
			ae.setIsoverandsave(2);
			employDao.update(ae);
			return true;
		}
		return false;
	}


}
