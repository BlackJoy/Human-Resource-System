package com.sys.login.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.login.User;
import com.sys.login.dao.IUserDAO;

public class UserDAOImpl extends BaseDAO implements IUserDAO {

	public User findByUserName(String userName) throws HibernateException {
		User user = null;
		List<User> list = (List<User>)this.getHibernateTemplate().find("From User u Where u.username=?", userName);
		if (list != null && list.size() > 0) {
			user = (User)list.get(0);
		}
		return user;
	}

}
