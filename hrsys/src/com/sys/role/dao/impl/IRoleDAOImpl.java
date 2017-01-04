package com.sys.role.dao.impl;

import java.io.Serializable;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.role.Role;
import com.sys.role.dao.IRoleDAO;

/**
 * ½ÇÉ«DAOÊµÏÖ
 * @author Administrator
 *
 */
public class IRoleDAOImpl extends BaseDAO implements IRoleDAO {

	public Role findRoleById(Serializable id) throws HibernateException {
		return (Role)findById(Role.class, id);
	}


}
