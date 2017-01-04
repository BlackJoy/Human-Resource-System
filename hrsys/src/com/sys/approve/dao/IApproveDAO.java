package com.sys.approve.dao;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;

/**
 * 审批数据访问接口
 * @author Administrator
 *
 */
public interface IApproveDAO extends IBaseDAO {

	/**
	 * 获取所有审批
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getApproveList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据组织ID获得员工
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getEmpByOrgId(String sql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据员工ID获得待审批流程
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getBacklogApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;

	/**
	 * 根据员工ID获得待传阅流程
	 * @param eid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getReviewApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据员工ID获得已批阅流程
	 * @param eid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getCompletedApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;
	
	
}
