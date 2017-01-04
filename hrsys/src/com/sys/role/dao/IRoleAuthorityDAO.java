package com.sys.role.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.role.RoleAuthority;

public interface IRoleAuthorityDAO extends IBaseDAO {

	/**
	 * 保存角色权限
	 * @param ra
	 * @throws HibernateException
	 */
	public void saveRoleAuthority(RoleAuthority ra) throws HibernateException;
}
