package com.sys.account.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.login.User;

public interface IAccountDAO extends IBaseDAO {

	/**
	 * 根据员工ID查找账户
	 * @param empId
	 * @return
	 * @throws HibernateException
	 */
	public User findAccountByEmpId(String empId) throws HibernateException;
	
	/**
	 * 新增账户
	 * @param user
	 * @throws HibernateException
	 */
	public void saveAccount(User user) throws HibernateException;
	
	/**
	 * 根据账户名查询账户
	 * @param name
	 * @throws HibernateException
	 */
	public User findAccountByName(String name) throws HibernateException;
}
