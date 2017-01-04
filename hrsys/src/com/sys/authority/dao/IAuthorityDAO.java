package com.sys.authority.dao;

import org.hibernate.HibernateException;

import com.sys.authority.Authority;
import com.sys.common.dao.IBaseDAO;

/**
 * 权限DAO接口
 * @author Administrator
 *
 */
public interface IAuthorityDAO extends IBaseDAO {

	/**
	 * 保存系统权限
	 * @param authority
	 * @throws HibernateException
	 */
	public void saveAuthority(Authority authority) throws HibernateException;
	
}
