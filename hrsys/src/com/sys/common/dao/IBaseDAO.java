package com.sys.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;

/**
 * DAO�ӿ�
 * @author Administrator
 *
 */
public interface IBaseDAO {
	/**
	 * ʹ�ñ���SQL��ѯʵ�弯��
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public List findSQL2EntityList(final String sql, final Class clz) throws HibernateException;
	
	/**
	 * ʹ�ñ���SQL��ѯMAP����
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public List findSQL2MapList(final String sql) throws HibernateException;
	
	/**
	 * ʹ�ñ���SQL��ѯΨһʵ�����
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public Object findSQL2UniqueEntity(final String sql, final Class clz) throws HibernateException;
	
	/**
	 * ͨ��ID��ѯ����
	 * @param clz
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public Object findById(Class clz, java.io.Serializable id) throws HibernateException;
	
	/**
	 * �������
	 * @param obj
	 * @throws HibernateException
	 */
	public java.io.Serializable save(Object obj) throws HibernateException;
	
	/**
	 * ���¶���
	 * @param obj
	 * @throws HibernateException
	 */
	public void update(Object obj) throws HibernateException;
	
	/**
	 * ɾ������
	 * @param obj
	 * @throws HibernateException
	 */
	public void delete(Object obj) throws HibernateException;
	
	/**
	 * ʹ��HQL��ҳ
	 * @param hql
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 * @throws HibernateException
	 */
	public Page findPaginationByHQL_EntityList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ʹ��SQL��ҳ
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 * @throws HibernateException
	 */
	public Page findPaginationBySQL_MapList(String sql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * ʹ��SQLɾ��
	 * @param sql
	 * @throws HibernateException
	 */
	public int deleteSQL(String sql) throws HibernateException;
	
	/**
	 * ʹ��SQL����
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public int updateSQL(String sql) throws HibernateException;
	
	public Object getEntityById(String id,Class en);
	
	
	
	/**
	 * ͨ����ҳ����ʽ������id����tableName���м�¼
	 * @author zgy
	 */
	
	public Page findObjectByOrgId_page(String id, int pageIndex, int pageSize,String tableName);
	
	/**
	 * ͨ��sql��䴴���±�
	 * @throws SQLException 
	 */
	
	public void createBySql( final String sql) throws SQLException;
	
	/**
	 * ͨ��jdbcִ��sql���(��������в�������)
	 */
	public void executeByJdbcSql(final String tableName,final String id,final double value) throws Exception;
	
	
	
	public void saveOrUpdate(Object obj) throws Exception;

	public void executeByDmlSql(final String sql);
	 /**
	    * ���ݱ��������ض�ʵ��
	    * @author Jp
	    */
   public Object getEntityByTableName(String tableName,String id);
	
}
