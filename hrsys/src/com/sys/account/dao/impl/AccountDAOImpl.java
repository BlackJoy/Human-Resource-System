package com.sys.account.dao.impl;

import org.hibernate.HibernateException;

import com.sys.account.dao.IAccountDAO;
import com.sys.common.dao.BaseDAO;
import com.sys.login.User;

public class AccountDAOImpl extends BaseDAO implements IAccountDAO {

	public User findAccountByEmpId(String empId) throws HibernateException {
		String sql = "select * from TBL_Users u where u.employeeId='"+empId+"'";
		return (User)this.findSQL2UniqueEntity(sql, User.class);
	}

	public void saveAccount(User user) throws HibernateException {
		this.save(user);
	}

	public User findAccountByName(String name) throws HibernateException {
		String sql = "select * from TBL_Users u where u.username='"+name+"'";
		return (User)this.findSQL2UniqueEntity(sql, User.class);
	}

}
