package com.sys.approve.dao;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;

/**
 * �������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface IApproveDAO extends IBaseDAO {

	/**
	 * ��ȡ��������
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getApproveList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ������֯ID���Ա��
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getEmpByOrgId(String sql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ����Ա��ID��ô���������
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getBacklogApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;

	/**
	 * ����Ա��ID��ô���������
	 * @param eid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getReviewApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ����Ա��ID�������������
	 * @param eid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getCompletedApproveList(String eid, int pageIndex, int pageSize) throws HibernateException;
	
	
}
