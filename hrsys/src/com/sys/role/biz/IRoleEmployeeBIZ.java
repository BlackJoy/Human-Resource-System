package com.sys.role.biz;

import java.util.List;

import com.sys.role.RoleEmployee;

/**
 * 员工角色业务接口
 * @author Administrator
 *
 */
public interface IRoleEmployeeBIZ {

	/**
	 * 查找员工对应的角色
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public List<RoleEmployee> findRoleByEmployeeId(String empId) throws Exception;
	
	/**
	 * 删除角色对应人员
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteByRoleId(String roleId) throws Exception;
	
	/**
	 * 批量保存
	 * @param res
	 * @throws Exception
	 */
	public void updateRoleEmployees(String roleId, List<RoleEmployee> res) throws Exception;
}
