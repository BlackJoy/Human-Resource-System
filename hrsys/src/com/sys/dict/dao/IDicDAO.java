package com.sys.dict.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;
import com.sys.dict.DicData;

public interface IDicDAO extends IBaseDAO {

	/**
	 * ���������ֵ�����
	 * @param hql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getDicTypeList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * �����ֵ����Ͳ����ֵ�����
	 * @param dicTypeId
	 * @return
	 * @throws HibernateException
	 */
	public List<DicData> getDicDataByDicTypeId(String dicTypeId) throws HibernateException;
	
	/**
	 * �����ֵ����ͱ�������ֵ�����
	 * @param dicTypeCode
	 * @return
	 * @throws HibernateException
	 */
	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode) throws HibernateException;
}
