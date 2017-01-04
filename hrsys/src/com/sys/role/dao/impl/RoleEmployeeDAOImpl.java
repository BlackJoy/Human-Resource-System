package com.sys.role.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.role.RoleEmployee;
import com.sys.role.dao.IRoleEmployeeDAO;

/**
 * Ա����ɫDAOʵ��
 * @author Administrator
 *
 */
public class RoleEmployeeDAOImpl extends BaseDAO implements IRoleEmployeeDAO {

	public List<RoleEmployee> findByEmployeeId(String empId)
			throws HibernateException {
		List<RoleEmployee> res = null;
		res = (List<RoleEmployee>)this.getHibernateTemplate().find("From RoleEmployee re where re.employeeId=?", empId);
		return res;
	}
	
	/**
	 * ����Ա��IDɾ����ӵ�еĽ�ɫ
	 * @param empId
	 * @throws HibernateException
	 */
	public void deleteRoleEmpByEmpId(String empId) throws HibernateException {
		String sql = "delete TBL_Role_Employee re where re.employeeId='"+empId+"'";
		this.deleteSQL(sql);
	}
	
}
