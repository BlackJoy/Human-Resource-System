package com.sys.role.biz;

import java.util.List;

import com.sys.role.RoleAuthority;

public interface IRoleAuthorityBIZ {

	/**
	 * 保存角色权限
	 * @param ras
	 * @throws Exception
	 */
	public void saveRoleAuthority(List<RoleAuthority> ras) throws Exception;
	
	/**
	 * 删除角色对应权限
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteByRoleId(String roleId) throws Exception;
	
	/**
	 * 授权
	 * @param roleId
	 * @param ras
	 * @throws Exception
	 */
	public void saveAuthorization(String roleId, List<RoleAuthority> ras) throws Exception;
}
