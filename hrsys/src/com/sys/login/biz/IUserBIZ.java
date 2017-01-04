package com.sys.login.biz;

import com.sys.login.User;

/**
 * 登录用户业务类
 * @author Administrator
 *
 */
public interface IUserBIZ {

	/**
	 * 登录验证
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User find_checkLogin(User user) throws Exception;
	
}
