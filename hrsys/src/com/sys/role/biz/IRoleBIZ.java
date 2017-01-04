package com.sys.role.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.role.Role;

/**
 * ��ɫҵ��ӿ�
 * @author Administrator
 *
 */
public interface IRoleBIZ {

	/**
	 * �½���ɫ
	 * @param role
	 * @throws Exception
	 */
	public void saveRole(Role role) throws Exception;
	
	/**
	 * �޸Ľ�ɫ
	 * @param role
	 * @throws Exception
	 */
	public void updateRoel(Role role) throws Exception;
	
	/**
	 * ɾ����ɫ
	 * @param role
	 * @throws Exception
	 */
	public void deleteRole(java.io.Serializable id) throws Exception;
	
	/**
	 * ����ID���ҽ�ɫ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(java.io.Serializable id) throws Exception;
	
	/**
	 * ��ý�ɫҳ��Page����
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public Page getRolePage(int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ��ý�ɫ��Ӧ��Ա��
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List getEmployeeByRoleId(String roleId) throws Exception;
}
