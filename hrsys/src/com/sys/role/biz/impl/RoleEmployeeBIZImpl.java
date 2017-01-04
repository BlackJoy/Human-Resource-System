package com.sys.role.biz.impl;

import java.util.Iterator;
import java.util.List;

import com.sys.role.RoleEmployee;
import com.sys.role.biz.IRoleEmployeeBIZ;
import com.sys.role.dao.IRoleEmployeeDAO;

/**
 * 员工角色业务实现
 * @author Administrator
 *
 */
public class RoleEmployeeBIZImpl implements IRoleEmployeeBIZ {

	private IRoleEmployeeDAO reDao;
	public IRoleEmployeeDAO getReDao() {
		return reDao;
	}
	public void setReDao(IRoleEmployeeDAO reDao) {
		this.reDao = reDao;
	}
	public List<RoleEmployee> findRoleByEmployeeId(String empId)
			throws Exception {
		return reDao.findByEmployeeId(empId);
	}
	public void deleteByRoleId(String roleId) throws Exception {
		String sql = "delete from tbl_role_employee re where re.roleid='"+roleId+"'";
		reDao.deleteSQL(sql);
	}
	public void updateRoleEmployees(String roleId, List<RoleEmployee> res) throws Exception {
		String sql = "delete from tbl_role_employee re where re.roleid='"+roleId+"'";
		reDao.deleteSQL(sql);
		for (Iterator it = res.iterator(); it.hasNext();) {
			RoleEmployee re = (RoleEmployee) it.next();
			reDao.save(re);
		}
	}

}
