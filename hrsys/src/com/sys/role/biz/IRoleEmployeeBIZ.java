package com.sys.role.biz;

import java.util.List;

import com.sys.role.RoleEmployee;

/**
 * Ա����ɫҵ��ӿ�
 * @author Administrator
 *
 */
public interface IRoleEmployeeBIZ {

	/**
	 * ����Ա����Ӧ�Ľ�ɫ
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public List<RoleEmployee> findRoleByEmployeeId(String empId) throws Exception;
	
	/**
	 * ɾ����ɫ��Ӧ��Ա
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteByRoleId(String roleId) throws Exception;
	
	/**
	 * ��������
	 * @param res
	 * @throws Exception
	 */
	public void updateRoleEmployees(String roleId, List<RoleEmployee> res) throws Exception;
}
