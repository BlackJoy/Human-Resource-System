package com.sys.account.biz;

import com.sys.common.Page;
import com.sys.login.User;

public interface IAccountBIZ {

	/**
	 * ������֯ID����Ա�����˺�
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Page findEmpAccountByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ����Ա��ID�����˺�
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public User findAccountByEmpId(String empId) throws Exception;
	
	/**
	 * �����˻�
	 * @param user
	 * @throws Exception
	 */
	public void saveAccount(User user) throws Exception;
	
	/**
	 * �����˻�����ѯ�˻�
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public User findAccountByName(String name) throws Exception;
	
	/**
	 * �����˻�
	 * @param user
	 * @throws Exception
	 */
	public void updateAccount(User user) throws Exception;
	
	/**
	 * ɾ���˻�
	 * @param user
	 * @throws Exception
	 */
	public void deleteAccount(User user) throws Exception;
}
