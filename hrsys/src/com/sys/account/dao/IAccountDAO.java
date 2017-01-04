package com.sys.account.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.login.User;

public interface IAccountDAO extends IBaseDAO {

	/**
	 * ����Ա��ID�����˻�
	 * @param empId
	 * @return
	 * @throws HibernateException
	 */
	public User findAccountByEmpId(String empId) throws HibernateException;
	
	/**
	 * �����˻�
	 * @param user
	 * @throws HibernateException
	 */
	public void saveAccount(User user) throws HibernateException;
	
	/**
	 * �����˻�����ѯ�˻�
	 * @param name
	 * @throws HibernateException
	 */
	public User findAccountByName(String name) throws HibernateException;
}
