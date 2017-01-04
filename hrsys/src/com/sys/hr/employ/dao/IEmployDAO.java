package com.sys.hr.employ.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;

/**
 * ��Ƹ�������ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface IEmployDAO extends IBaseDAO {

	/**
	 * ������֯ID����ӦƸ��Ϣ
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ������֯ID����¼����Ϣ
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ������֯ID������ְ��Ϣ
	 * @param orgId
	 * @return
	 * @throws HibernateException
	 */
	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ����ID���Ҵ���ְ��Ա
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public List findRuZhiById(String id) throws HibernateException;
}
