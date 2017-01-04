package com.sys.role.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.role.RoleEmployee;

/**
 * 员工角色DAO接口
 * @author Administrator
 *
 */
public interface IRoleEmployeeDAO extends IBaseDAO {

	/**
	 * 根据员工ID查找对应的角色
	 * @param empId
	 * @return
	 * @throws HibernateException
	 */
	public List<RoleEmployee> findByEmployeeId(String empId) throws HibernateException;
	
	/**
	 * 根据员工ID删除所拥有的角色
	 * @param empId
	 * @throws HibernateException
	 */
	public void deleteRoleEmpByEmpId(String empId) throws HibernateException;
}
