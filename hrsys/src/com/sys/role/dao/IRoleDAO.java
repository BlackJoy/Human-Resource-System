package com.sys.role.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.role.Role;

/**
 * ��ɫDAO�ӿ�
 * @author Administrator
 *
 */
public interface IRoleDAO extends IBaseDAO {

	public Role findRoleById(java.io.Serializable id) throws HibernateException;
}
