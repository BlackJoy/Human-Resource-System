package com.sys.hr.employee.biz.impl;

import java.io.Serializable;
import java.util.List;

import com.sys.common.Page;
import com.sys.hr.employee.Employee;
import com.sys.hr.employee.biz.IEmployeeBIZ;
import com.sys.hr.employee.dao.IEmployeeDAO;
import com.sys.hr.org.Org;

public class EmployeeBIZImpl implements IEmployeeBIZ {

	private IEmployeeDAO empDao;
	public IEmployeeDAO getEmpDao() {
		return empDao;
	}
	public void setEmpDao(IEmployeeDAO empDao) {
		this.empDao = empDao;
	}
	public Employee findEmployeeById(Serializable id) throws Exception {
		return empDao.findEmployeeById(id);
	}
	public List findEmpsByOrgId(String orgId) throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org)empDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
//		String sql = "select e.id, e.employeecode||'-'||e.employeename empname" +
//				" from Tbl_Employee e inner join " +
//				" (SELECT o.* FROM Tbl_Org o where o.orgstatus=1" +
//				" START WITH o.id = '"+orgId+"' CONNECT BY o.orgparentid = PRIOR id) o" +
//				" on e.orgid = o.id";
		
		String sql = "select ep.employee_id id,ep.employeecode||'-'||ep.employeename empname from" +
				" (select e2p.id,e2p.employee_id,e.employeecode,e.employeename,e2p.org_id,dd.dicdata_name,dd.dicdata_code from TBL_EMPLOYEE2POSITION e2p" +
				" left join Tbl_Dicdata dd" +
				" on e2p.position_id=dd.id" +
				" left join Tbl_Employee e" +
				" on e2p.employee_id=e.id" +
				" where e2p.positionstatus=1 and e2p.positiontype=1) ep" +
				" inner join" +
				" (select o.id,sys_connect_by_path(o.orgshortname,'.') orgname,o.orgcode from TBL_ORG o" +
				" where o.orgstatus=1" +
				" start with o.id= '"+orgId+"' connect by o.orgparentid=prior id) o" +
				" on ep.org_id=o.id" +
				" order by o.orgcode,ep.dicdata_code";
		return empDao.findSQL2MapList(sql);
	}

}
