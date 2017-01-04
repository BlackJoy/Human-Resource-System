package com.sys.login.biz.impl;

import com.sys.login.ILoginConstant;
import com.sys.login.User;
import com.sys.login.biz.IUserBIZ;
import com.sys.login.dao.IUserDAO;

public class UserBIZImpl implements IUserBIZ {

	private IUserDAO userDao = null;
	public IUserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}
	public User find_checkLogin(User user) throws Exception {
		User u = userDao.findByUserName(user.getUsername());
		return u;
	}

}
