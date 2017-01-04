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
	 * ������֯ID����Ա�����˺�
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Page findEntityByCondition(String sql, int pageIndex, int pageSize,Map<String,Object> params) throws Exception;
	
		
	/**
	 * ����
	 * @param user
	 * @throws Exception
	 */
	public void save(Object obj) throws Exception;
	
	
	
	/**
	 * ����
	 * @param user
	 * @throws Exception
	 */
	public void update(Object obj) throws Exception;
	
	/**
	 * ɾ��
	 * @param user
	 * @throws Exception
	 */
	public void delete(Object obj) throws Exception;
	
	public Object getEntityById(String id,Class en);
	
	
	/**
	 * ͨ����ҳ����ʽ������id����tableName���м�¼
	 * @author zgy
	 */
	
	public Page findObjectByOrgId_page(String id, int pageIndex, int pageSize,String tableName);
	
	
	/**
	 * ��ѯ���е�ʵ�壬ͨ������
	 * @author zgy
	 */
	public List findALl2EntityList(String tableName,Class clz);
	
	//���ݱ�������ѯ��������ѯ
	//example:ZhangTao empZt=(ZhangTao) commonBiz.findByCondition("WAGE_TYPE", "WAGE_TYPE_NAME='"+wageTypName+"'", ZhangTao.class);
	public List findByCondition(String tableName,String condition,Class clz);
	
	
	/**
	 * ���������������±�
	 * @throws SQLException 
	 */
	public void createByTableName(String tableName) throws Exception;
	
	/**
	 * ��������������֣�����в���ֵ
	 */
	public void insertIntoWageItem(String tableName,String id,double value) throws Exception;
	
	public List findSQL2MapList(String sql)throws Exception;
	
	public void saveOrUpdate(Object obj) throws Exception;
	
	public void saveFlowEntityById(String id,String status);
	
	
	/**
	 * ����sql���ִ��
	 */
	public void excuteByDmlSql(String sql);
	
	/**
	 * ������ѵ�ƻ���Ż�ȡ�üƻ��µ�Ա��
	 * @param trainPlainId
	 * @return
	 * @throws Exception
	 * @author Jp
	 */
	public List getEmployeeByTrainId(String trainPlainId) throws Exception ;
	/**
	 * ��������ӵ���Ա
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
