package com.sys.authority.dao;

import org.hibernate.HibernateException;

import com.sys.authority.Authority;
import com.sys.common.dao.IBaseDAO;

/**
 * Ȩ��DAO�ӿ�
 * @author Administrator
 *
 */
public interface IAuthorityDAO extends IBaseDAO {

	/**
	 * ����ϵͳȨ��
	 * @param authority
	 * @throws HibernateException
	 */
	public void saveAuthority(Authority authority) throws HibernateException;
	
}
