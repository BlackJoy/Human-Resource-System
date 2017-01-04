package com.sys.login.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.login.User;

public interface IUserDAO extends IBaseDAO {

	/**
	 * �����û��������û�
	 * @param userName
	 * @throws HibernateException
	 */
	public User findByUserName(String userName) throws HibernateException;
}
