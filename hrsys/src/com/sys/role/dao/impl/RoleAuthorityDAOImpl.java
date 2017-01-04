package com.sys.role.dao.impl;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.role.RoleAuthority;
import com.sys.role.dao.IRoleAuthorityDAO;

/**
 * ��ɫȨ��DAOʵ��
 * @author Administrator
 *
 */
public class RoleAuthorityDAOImpl extends BaseDAO implements IRoleAuthorityDAO {

	public void saveRoleAuthority(RoleAuthority ra) throws HibernateException {
		this.getHibernateTemplate().save(ra);
	}

}
