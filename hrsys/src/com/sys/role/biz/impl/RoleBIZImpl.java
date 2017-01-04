package com.sys.role.biz.impl;

import java.io.Serializable;
import java.util.List;

import com.sys.common.Page;
import com.sys.role.Role;
import com.sys.role.biz.IRoleBIZ;
import com.sys.role.dao.IRoleDAO;

public class RoleBIZImpl implements IRoleBIZ {

	private IRoleDAO roleDao;
	
	public IRoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public void saveRole(Role role) throws Exception {
		roleDao.save(role);
	}

	public void updateRoel(Role role) throws Exception {
		roleDao.update(role);
	}

	public void deleteRole(Serializable id) throws Exception {
		//删除角色
		Role role = (Role)roleDao.findRoleById(id);
		roleDao.delete(role);
		//删除角色对应的权限
		String sql = "delete from tbl_role_authority ra where ra.roleid='"+id+"'";
		roleDao.deleteSQL(sql);
		//删除角色对应的人员
		sql = "delete from tbl_role_employee re where re.roleid='"+id+"'";
		roleDao.deleteSQL(sql);
	}

	public Role getRoleById(Serializable id) throws Exception {
		return (Role)roleDao.findRoleById(id);
	}

	public Page getRolePage(int pageIndex, int pageSize) throws Exception {
		String hql = "From Role r";
		return roleDao.findPaginationByHQL_EntityList(hql, pageIndex, pageSize);
	}

	public List getEmployeeByRoleId(String roleId) throws Exception {
		String sql = "select e.id, e.employeecode||'-'||e.employeename empname" +
				" from Tbl_Employee e inner join Tbl_Role_Employee re" +
				" on e.id=re.employeeid where re.roleid='"+roleId+"'";
		return roleDao.findSQL2MapList(sql);
	}

}
