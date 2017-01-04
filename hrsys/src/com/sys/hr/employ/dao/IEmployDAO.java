package com.sys.hr.employ.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;

/**
 * 招聘管理数据访问接口
 * @author Administrator
 *
 */
public interface IEmployDAO extends IBaseDAO {

	/**
	 * 根据组织ID查找应聘信息
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据组织ID查找录用信息
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据组织ID查找入职信息
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据ID查找待入职人员
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public List findRuZhiById(String id) throws HibernateException;
}
