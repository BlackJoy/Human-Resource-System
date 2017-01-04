package com.sys.account.biz;

import com.sys.common.Page;
import com.sys.login.User;

public interface IAccountBIZ {

	/**
	 * 根据组织ID查找员工及账号
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Page findEmpAccountByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 根据员工ID查找账号
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public User findAccountByEmpId(String empId) throws Exception;
	
	/**
	 * 新增账户
	 * @param user
	 * @throws Exception
	 */
	public void saveAccount(User user) throws Exception;
	
	/**
	 * 根据账户名查询账户
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public User findAccountByName(String name) throws Exception;
	
	/**
	 * 更新账户
	 * @param user
	 * @throws Exception
	 */
	public void updateAccount(User user) throws Exception;
	
	/**
	 * 删除账户
	 * @param user
	 * @throws Exception
	 */
	public void deleteAccount(User user) throws Exception;
}
