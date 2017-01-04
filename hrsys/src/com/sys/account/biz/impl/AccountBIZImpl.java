package com.sys.account.biz.impl;

import com.sys.account.biz.IAccountBIZ;
import com.sys.account.dao.IAccountDAO;
import com.sys.common.Page;
import com.sys.hr.org.Org;
import com.sys.login.User;
import com.sys.role.dao.IRoleEmployeeDAO;

public class AccountBIZImpl implements IAccountBIZ {

	private IAccountDAO accountDao;
	private IRoleEmployeeDAO reDao;
	public IAccountDAO getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(IAccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	public IRoleEmployeeDAO getReDao() {
		return reDao;
	}
	public void setReDao(IRoleEmployeeDAO reDao) {
		this.reDao = reDao;
	}
	public Page findEmpAccountByOrgId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org)accountDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
//		String sql = "select e.id, e.employeecode, e.employeename, o.orgshortname, u.username, decode(u.enable,0,'注销',1,'启用') enable" +
//				" from Tbl_Employee e inner join " +
//				" (SELECT o.* FROM Tbl_Org o where o.orgstatus=1" +
//				" START WITH o.id = '"+orgId+"' CONNECT BY o.orgparentid = PRIOR id) o" +
//				" on e.orgid = o.id" +
//				" left join Tbl_Users u" +
//				" on e.id = u.employeeid";
		
		String sql = "select ep.employee_id id,ep.employeecode,ep.employeename,o.orgshortname,ep.dicdata_code, u.username, decode(u.enable,0,'注销',1,'启用') enable from" +
				" (select e2p.id,e2p.employee_id,e.employeecode,e.employeename,e2p.org_id,dd.dicdata_name,dd.dicdata_code from TBL_EMPLOYEE2POSITION e2p" +
				" left join Tbl_Dicdata dd on e2p.position_id=dd.id" +
				" left join Tbl_Employee e on e2p.employee_id=e.id" +
				" where e2p.positionstatus=1 and e2p.positiontype=1) ep" +
				" inner join" +
				" (select o.id,o.orgshortname,o.orgcode from TBL_ORG o" +
				" where o.orgstatus=1" +
				" start with o.id= '"+orgId+"' connect by o.orgparentid=prior id) o" +
				" on ep.org_id=o.id" +
				" left join Tbl_Users u" +
				" on ep.employee_id=u.employeeid" +
				" order by o.orgcode,ep.dicdata_code";
		Page page = accountDao.findPaginationBySQL_MapList(sql, pageIndex, pageSize);
		return page;
	}
	public User findAccountByEmpId(String empId) throws Exception {
		return accountDao.findAccountByEmpId(empId);
	}
	public void saveAccount(User user) throws Exception {
		accountDao.saveAccount(user);
	}
	public User findAccountByName(String name) throws Exception {
		return accountDao.findAccountByName(name);
	}
	public void updateAccount(User user) throws Exception {
		accountDao.update(user);
	}
	public void deleteAccount(User user) throws Exception {
		reDao.deleteRoleEmpByEmpId(user.getEmployeeId());
		accountDao.delete(user);
	}

}
