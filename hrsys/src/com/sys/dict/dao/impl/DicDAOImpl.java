package com.sys.dict.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.BaseDAO;
import com.sys.dict.DicData;
import com.sys.dict.dao.IDicDAO;

public class DicDAOImpl extends BaseDAO implements IDicDAO {

	public Page getDicTypeList(String hql, int pageIndex, int pageSize) throws HibernateException {
		return findPaginationByHQL_EntityList(hql, pageIndex, pageSize);
	}

	public List<DicData> getDicDataByDicTypeId(String dicTypeId)
			throws HibernateException {
		String sql = "select * from TBL_DICDATA d where d.DICTYPE_ID='" + dicTypeId + "' order by d.DICDATA_CODE";
		return (List<DicData>)findSQL2EntityList(sql, DicData.class);
	}

	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode)
			throws HibernateException {
		String sql = "select * from TBL_DICDATA d where d.DICTYPE_ID=(select dt.id from Tbl_Dictype dt where dt.dictype_code='"+dicTypeCode+"') order by d.DICDATA_CODE";
		return (List<DicData>)findSQL2EntityList(sql, DicData.class);
	}

	
}
