package com.sys.login.biz;

import com.sys.login.User;

/**
 * ��¼�û�ҵ����
 * @author Administrator
 *
 */
public interface IUserBIZ {

	/**
	 * ��¼��֤
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User find_checkLogin(User user) throws Exception;
	
}
