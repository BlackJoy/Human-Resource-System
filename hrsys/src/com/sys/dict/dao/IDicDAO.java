package com.sys.dict.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;
import com.sys.dict.DicData;

public interface IDicDAO extends IBaseDAO {

	/**
	 * 查找所有字典类型
	 * @param hql
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws HibernateException
	 */
	public Page getDicTypeList(String hql, int pageIndex, int pageSize) throws HibernateException;
	
	/**
	 * 根据字典类型查找字典数据
	 * @param dicTypeId
	 * @return
	 * @throws HibernateException
	 */
	public List<DicData> getDicDataByDicTypeId(String dicTypeId) throws HibernateException;
	
	/**
	 * 根据字典类型编码查找字典数据
	 * @param dicTypeCode
	 * @return
	 * @throws HibernateException
	 */
	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode) throws HibernateException;
}
