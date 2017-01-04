package com.sys.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;

/**
 * DAO接口
 * @author Administrator
 *
 */
public interface IBaseDAO {
	/**
	 * 使用本地SQL查询实体集合
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public List findSQL2EntityList(final String sql, final Class clz) throws HibernateException;
	
	/**
	 * 使用本地SQL查询MAP集合
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public List findSQL2MapList(final String sql) throws HibernateException;
	
	/**
	 * 使用本地SQL查询唯一实体对象
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public Object findSQL2UniqueEntity(final String sql, final Class clz) throws HibernateException;
	
	/**
	 * 通过ID查询对象
	 * @param clz
	 * @param id
	 * @return
	 * @throws HibernateException
	 */
	public Object findById(Class clz, java.io.Serializable id) throws HibernateException;
	
	/**
	 * 保存对象
	 * @param obj
	 * @throws HibernateException
	 */
	public java.io.Serializable save(Object obj) throws HibernateException;
	
	/**
	 * 更新对象
	 * @param obj
	 * @throws HibernateException
	 */
	public void update(Object obj) throws HibernateException;
	
	/**
	 * 删除对象
	 * @param obj
	 * @throws HibernateException
	 */
	public void delete(Object obj) throws HibernateException;
	
	/**
	 * 使用HQL分页
	 * @param hql
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 * @throws HibernateException
	 */
	public Page findPaginationByHQL_EntityList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 使用SQL分页
	 * @param sql
	 * @param pageIndex
	 * @param pageSize
	 * @return 
	 * @throws HibernateException
	 */
	public Page findPaginationBySQL_MapList(String sql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 使用SQL删除
	 * @param sql
	 * @throws HibernateException
	 */
	public int deleteSQL(String sql) throws HibernateException;
	
	/**
	 * 使用SQL更新
	 * @param sql
	 * @return
	 * @throws HibernateException
	 */
	public int updateSQL(String sql) throws HibernateException;
	
	public Object getEntityById(String id,Class en);
	
	
	
	/**
	 * 通过分页的形式，根据id查找tableName表中记录
	 * @author zgy
	 */
	
	public Page findObjectByOrgId_page(String id, int pageIndex, int pageSize,String tableName);
	
	/**
	 * 通过sql语句创建新表
	 * @throws SQLException 
	 */
	
	public void createBySql( final String sql) throws SQLException;
	
	/**
	 * 通过jdbc执行sql语句(向工资项表中插入数据)
	 */
	public void executeByJdbcSql(final String tableName,final String id,final double value) throws Exception;
	
	
	
	public void saveOrUpdate(Object obj) throws Exception;

	public void executeByDmlSql(final String sql);
	 /**
	    * 根据表名查找特定实体
	    * @author Jp
	    */
   public Object getEntityByTableName(String tableName,String id);
	
}
