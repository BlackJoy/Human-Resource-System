package com.sys.role.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.role.RoleEmployee;

/**
 * Ա����ɫDAO�ӿ�
 * @author Administrator
 *
 */
public interface IRoleEmployeeDAO extends IBaseDAO {

	/**
	 * ����Ա��ID���Ҷ�Ӧ�Ľ�ɫ
	 * @param empId
	 * @return
	 * @throws HibernateException
	 */
	public List<RoleEmployee> findByEmployeeId(String empId) throws HibernateException;
	
	/**
	 * ����Ա��IDɾ����ӵ�еĽ�ɫ
	 * @param empId
	 * @throws HibernateException
	 */
	public void deleteRoleEmpByEmpId(String empId) throws HibernateException;
}
