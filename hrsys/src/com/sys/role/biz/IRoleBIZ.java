package com.sys.role.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.role.Role;

/**
 * 角色业务接口
 * @author Administrator
 *
 */
public interface IRoleBIZ {

	/**
	 * 新建角色
	 * @param role
	 * @throws Exception
	 */
	public void saveRole(Role role) throws Exception;
	
	/**
	 * 修改角色
	 * @param role
	 * @throws Exception
	 */
	public void updateRoel(Role role) throws Exception;
	
	/**
	 * 删除角色
	 * @param role
	 * @throws Exception
	 */
	public void deleteRole(java.io.Serializable id) throws Exception;
	
	/**
	 * 根据ID查找角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(java.io.Serializable id) throws Exception;
	
	/**
	 * 获得角色页面Page对象
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public Page getRolePage(int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 获得角色对应的员工
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List getEmployeeByRoleId(String roleId) throws Exception;
}
