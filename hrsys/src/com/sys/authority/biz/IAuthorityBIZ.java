package com.sys.authority.biz;

import java.util.List;

import com.sys.authority.Authority;
import com.sys.role.RoleEmployee;

/**
 * 权限业务接口
 * @author Administrator
 *
 */
public interface IAuthorityBIZ {

	/**
	 * 保存多个系统权限
	 * @param authorities
	 * @throws Exception
	 */
	public void saveAuthorities(List<Authority> authorities) throws Exception;
	
	/**
	 * 删除所有权限
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;
	
	/**
	 * 通过角色获取对应的权限
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getAuthoritiesByRoles(List<RoleEmployee> res) throws Exception;
	
	/**
	 * 获得角色的所有权限
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getAuthoritiesByRoleId(String roleId) throws Exception;
}
