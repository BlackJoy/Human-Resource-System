package com.sys.role.biz.impl;

import java.util.List;

import com.sys.role.RoleAuthority;
import com.sys.role.biz.IRoleAuthorityBIZ;
import com.sys.role.dao.IRoleAuthorityDAO;

public class RoleAuthorityBIZImpl implements IRoleAuthorityBIZ {

	private IRoleAuthorityDAO roleAuthorityDao = null;
	
	public IRoleAuthorityDAO getRoleAuthorityDao() {
		return roleAuthorityDao;
	}

	public void setRoleAuthorityDao(IRoleAuthorityDAO roleAuthorityDao) {
		this.roleAuthorityDao = roleAuthorityDao;
	}

	public void saveRoleAuthority(List<RoleAuthority> ras) throws Exception {
		for (RoleAuthority roleAuthority : ras) {
			roleAuthorityDao.saveRoleAuthority(roleAuthority);
		}
	}

	public void deleteByRoleId(String roleId) throws Exception {
		String sql = "delete from tbl_role_authority ra where ra.roleid='"+roleId+"'";
		roleAuthorityDao.deleteSQL(sql);
	}
	
	public void saveAuthorization(String roleId, List<RoleAuthority> ras) throws Exception {
		//删除原有权限
		String sql = "delete from tbl_role_authority ra where ra.roleid='"+roleId+"'";
		roleAuthorityDao.deleteSQL(sql);
		//保存授权
		for (RoleAuthority roleAuthority : ras) {
			roleAuthorityDao.saveRoleAuthority(roleAuthority);
		}
	}

}
