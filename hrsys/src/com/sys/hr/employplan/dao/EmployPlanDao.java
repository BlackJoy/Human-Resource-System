package com.sys.hr.employplan.dao;

import org.hibernate.HibernateException;

import com.sys.common.Page;

public interface EmployPlanDao {
	/**
	 * 使用本地SQL查询唯一实体对象
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public Object findSQL2UniqueEntity(final String sql, final Class clz) throws HibernateException;
	public Page findEmployPlanByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	public Page findEmployPlanMangByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
}
