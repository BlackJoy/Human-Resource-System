package com.sys.authority.dao.impl;

import org.hibernate.HibernateException;

import com.sys.authority.Authority;
import com.sys.authority.dao.IAuthorityDAO;
import com.sys.common.dao.BaseDAO;

/**
 * Ȩ��DAOʵ��
 * @author Administrator
 *
 */
public class AuthorityDAOImpl extends BaseDAO implements IAuthorityDAO {

	public void saveAuthority(Authority authority) throws HibernateException {
		this.getHibernateTemplate().saveOrUpdate(authority);
	}

}
