package com.sys.common.biz;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.hr.train.TrainScore;
import com.sys.hr.zhangtao.ZhangTao;

public interface IBaseBIZ {

	public Object findNeiBorrowById(Serializable id);
	public Object findBorrowInById(Serializable id);
	public Object findBorrowOutById(Serializable id);
	public Page findPageBySql_MapList(String sql, int pageIndex, int pageSize ) 	throws Exception;
	/**
	 * 根据组织ID查找员工及账号
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Page findEntityByCondition(String sql, int pageIndex, int pageSize,Map<String,Object> params) throws Exception;
	
		
	/**
	 * 新增
	 * @param user
	 * @throws Exception
	 */
	public void save(Object obj) throws Exception;
	
	
	
	/**
	 * 更新
	 * @param user
	 * @throws Exception
	 */
	public void update(Object obj) throws Exception;
	
	/**
	 * 删除
	 * @param user
	 * @throws Exception
	 */
	public void delete(Object obj) throws Exception;
	
	public Object getEntityById(String id,Class en);
	
	
	/**
	 * 通过分页的形式，根据id查找tableName表中记录
	 * @author zgy
	 */
	
	public Page findObjectByOrgId_page(String id, int pageIndex, int pageSize,String tableName);
	
	
	/**
	 * 查询所有的实体，通过类名
	 * @author zgy
	 */
	public List findALl2EntityList(String tableName,Class clz);
	
	//根据表明，查询条件，查询
	//example:ZhangTao empZt=(ZhangTao) commonBiz.findByCondition("WAGE_TYPE", "WAGE_TYPE_NAME='"+wageTypName+"'", ZhangTao.class);
	public List findByCondition(String tableName,String condition,Class clz);
	
	
	/**
	 * 给定表名，创建新表
	 * @throws SQLException 
	 */
	public void createByTableName(String tableName) throws Exception;
	
	/**
	 * 给定工资项表名字，向表中插入值
	 */
	public void insertIntoWageItem(String tableName,String id,double value) throws Exception;
	
	public List findSQL2MapList(String sql)throws Exception;
	
	public void saveOrUpdate(Object obj) throws Exception;
	
	public void saveFlowEntityById(String id,String status);
	
	
	/**
	 * 给定sql语句执行
	 */
	public void excuteByDmlSql(String sql);
	
	/**
	 * 根据培训计划编号获取该计划下的员工
	 * @param trainPlainId
	 * @return
	 * @throws Exception
	 * @author Jp
	 */
	public List getEmployeeByTrainId(String trainPlainId) throws Exception ;
	/**
	 * 保存新添加的人员
	 * @param scoreList
	 * @author Jp
	 */
	public void saveOrUpdateScoreList(String trainId,List<TrainScore> scoreList);
	/**
	 * @author Jp
	 */
	public void deleteScoresByTrainId(String trainId);
	/**
	 * 
	 */
	public int deleteSQL(final String sql) ;
	/**
	 * @author Jp
	 */
	public Page findPaginationBySQL_MapList(String sql, int pageIndex, int pageSize) throws HibernateException;
	public Object findSQL2UniqueEntity(final String sql, final Class clz) ;
	public List findSQL2EntityList(final String sql, final Class clz) throws HibernateException;
}
