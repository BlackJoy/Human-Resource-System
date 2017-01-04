package com.sys.dict.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.dict.DicData;
import com.sys.dict.DicType;

public interface IDicBIZ {

	/**
	 * 查询字典类型
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getDicTypeList(int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 根据字典类型查找字典数据
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public List<DicData> getDicDataByDicTypeId(String dicTypeId) throws Exception;
	
	/**
	 * 根据字典类型编码查找字典数据
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode) throws Exception;
	
	/**
	 * 添加新字典类型
	 * @param dicType
	 * @throws Exception
	 */
	public void saveDicType(DicType dicType) throws Exception;
	
	/**
	 * 添加新字典数据
	 * @param dicData
	 * @throws Exception
	 */
	public void saveDicData(DicData dicData) throws Exception;
	
	/**
	 * 根据ID查找字典数据
	 * @param dicDataId
	 * @return
	 * @throws Exception
	 */
	public DicData getDicDataById(String dicDataId) throws Exception;
	
	/**
	 * 更新字典数据
	 * @param dicData
	 * @throws Exception
	 */
	public void updateDicData(DicData dicData) throws Exception;
	
	/**
	 * 删除字典数据
	 * @param dicData
	 * @throws Exception
	 */
	public void deleteDicData(DicData dicData) throws Exception;
	
	/**
	 * 根据ID查找字典类型
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public DicType getDicTypeById(String dicTypeId) throws Exception;
	
	/**
	 * 更新字典类型
	 * @param dicType
	 * @throws Exception
	 */
	public void updateDicType(DicType dicType) throws Exception;
	
	/**
	 * 删除字典类型
	 * @param dicType
	 * @throws Exception
	 */
	public void deleteDicType(DicType dicType) throws Exception;
	
}
