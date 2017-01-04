package com.sys.role.biz;

import java.util.List;

import com.sys.role.RoleAuthority;

public interface IRoleAuthorityBIZ {

	/**
	 * �����ɫȨ��
	 * @param ras
	 * @throws Exception
	 */
	public void saveRoleAuthority(List<RoleAuthority> ras) throws Exception;
	
	/**
	 * ɾ����ɫ��ӦȨ��
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteByRoleId(String roleId) throws Exception;
	
	/**
	 * ��Ȩ
	 * @param roleId
	 * @param ras
	 * @throws Exception
	 */
	public void saveAuthorization(String roleId, List<RoleAuthority> ras) throws Exception;
}
