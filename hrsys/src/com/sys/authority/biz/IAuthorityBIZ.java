package com.sys.authority.biz;

import java.util.List;

import com.sys.authority.Authority;
import com.sys.role.RoleEmployee;

/**
 * Ȩ��ҵ��ӿ�
 * @author Administrator
 *
 */
public interface IAuthorityBIZ {

	/**
	 * ������ϵͳȨ��
	 * @param authorities
	 * @throws Exception
	 */
	public void saveAuthorities(List<Authority> authorities) throws Exception;
	
	/**
	 * ɾ������Ȩ��
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;
	
	/**
	 * ͨ����ɫ��ȡ��Ӧ��Ȩ��
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getAuthoritiesByRoles(List<RoleEmployee> res) throws Exception;
	
	/**
	 * ��ý�ɫ������Ȩ��
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getAuthoritiesByRoleId(String roleId) throws Exception;
}
