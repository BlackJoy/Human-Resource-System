package com.sys.dict.biz.impl;

import java.util.List;

import com.sys.common.Page;
import com.sys.dict.DicData;
import com.sys.dict.DicType;
import com.sys.dict.biz.IDicBIZ;
import com.sys.dict.dao.IDicDAO;

public class DicBizImpl implements IDicBIZ {

	private IDicDAO dicDao;
	public IDicDAO getDicDao() {
		return dicDao;
	}

	public void setDicDao(IDicDAO dicDao) {
		this.dicDao = dicDao;
	}

	public Page getDicTypeList(int pageIndex, int pageSize) throws Exception {
		String hql = "from DicType d order by d.dicTypeCode";
		return dicDao.findPaginationByHQL_EntityList(hql, pageIndex, pageSize);
	}

	public List<DicData> getDicDataByDicTypeId(String dicTypeId)
			throws Exception {
		return dicDao.getDicDataByDicTypeId(dicTypeId);
	}

	public void saveDicType(DicType dicType) throws Exception {
		dicDao.save(dicType);
	}

	public void saveDicData(DicData dicData) throws Exception {
		dicDao.save(dicData);
	}

	public DicData getDicDataById(String dicDataId) throws Exception {
		return (DicData)dicDao.findById(DicData.class, dicDataId);
	}

	public void updateDicData(DicData dicData) throws Exception {
		dicDao.update(dicData);
	}

	public void deleteDicData(DicData dicData) throws Exception {
		dicDao.delete(dicData);
	}

	public DicType getDicTypeById(String dicTypeId) throws Exception {
		return (DicType)dicDao.findById(DicType.class, dicTypeId);
	}

	public void updateDicType(DicType dicType) throws Exception {
		dicDao.update(dicType);
	}

	public void deleteDicType(DicType dicType) throws Exception {
		String sql = "delete TBL_DICDATA d where d.DICTYPE_ID='"+dicType.getId()+"'";
		dicDao.deleteSQL(sql);
		dicDao.delete(dicType);
	}

	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode)
			throws Exception {
		return dicDao.getDicDataByDicTypeCode(dicTypeCode);
	}

}
