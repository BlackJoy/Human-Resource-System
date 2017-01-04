package com.sys.authority.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.sys.authority.Authority;
import com.sys.authority.biz.IAuthorityBIZ;
import com.sys.authority.dao.IAuthorityDAO;
import com.sys.role.RoleEmployee;

/**
 * 权限业务实现
 * @author Administrator
 *
 */
public class AuthorityBIZImpl implements IAuthorityBIZ {

	private IAuthorityDAO authorityDao = null;
	
	public IAuthorityDAO getAuthorityDao() {
		return authorityDao;
	}

	public void setAuthorityDao(IAuthorityDAO authorityDao) {
		this.authorityDao = authorityDao;
	}

	public void saveAuthorities(List<Authority> authorities) throws Exception {
		for (Authority authority : authorities) {
			authorityDao.saveAuthority(authority);
		}
	}

	public List<Authority> getAuthoritiesByRoles(List<RoleEmployee> res)
			throws Exception {
		List<Authority> list = new ArrayList<Authority>();
		if (res != null && res.size() > 0) {
			StringBuffer sql_buf = new StringBuffer();
			sql_buf.append("select * from TBL_Authority a");
			sql_buf.append(" inner join");
			sql_buf.append(" (select distinct(ra.authorityId) aid from TBL_ROLE_AUTHORITY ra");
			sql_buf.append(" where ra.roleid in (");
			for (RoleEmployee re : res) {
				sql_buf.append("'"+re.getRoleId()+"',");
			}
			String sql = sql_buf.substring(0, sql_buf.length() - 1) + ")) ra on a.id=ra.aid";
			list = authorityDao.findSQL2EntityList(sql.toString(), Authority.class);
		}
		return list;
	}

	public List<Authority> getAuthoritiesByRoleId(String roleId)
			throws Exception {
		List<Authority> list = new ArrayList<Authority>();
		String sql = "select a.* from TBL_AUTHORITY a inner join TBL_ROLE_AUTHORITY ra on a.id = ra.authorityid where ra.roleid='"+roleId+"'";
		list = authorityDao.findSQL2EntityList(sql, Authority.class);
		return list;
	}

	public void deleteAll() throws Exception {
		String sql = "delete TBL_AUTHORITY";
		authorityDao.deleteSQL(sql);
	}

}
