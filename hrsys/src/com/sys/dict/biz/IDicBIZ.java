package com.sys.dict.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.dict.DicData;
import com.sys.dict.DicType;

public interface IDicBIZ {

	/**
	 * ��ѯ�ֵ�����
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getDicTypeList(int pageIndex, int pageSize) throws Exception;
	
	/**
	 * �����ֵ����Ͳ����ֵ�����
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public List<DicData> getDicDataByDicTypeId(String dicTypeId) throws Exception;
	
	/**
	 * �����ֵ����ͱ�������ֵ�����
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public List<DicData> getDicDataByDicTypeCode(String dicTypeCode) throws Exception;
	
	/**
	 * ������ֵ�����
	 * @param dicType
	 * @throws Exception
	 */
	public void saveDicType(DicType dicType) throws Exception;
	
	/**
	 * ������ֵ�����
	 * @param dicData
	 * @throws Exception
	 */
	public void saveDicData(DicData dicData) throws Exception;
	
	/**
	 * ����ID�����ֵ�����
	 * @param dicDataId
	 * @return
	 * @throws Exception
	 */
	public DicData getDicDataById(String dicDataId) throws Exception;
	
	/**
	 * �����ֵ�����
	 * @param dicData
	 * @throws Exception
	 */
	public void updateDicData(DicData dicData) throws Exception;
	
	/**
	 * ɾ���ֵ�����
	 * @param dicData
	 * @throws Exception
	 */
	public void deleteDicData(DicData dicData) throws Exception;
	
	/**
	 * ����ID�����ֵ�����
	 * @param dicTypeId
	 * @return
	 * @throws Exception
	 */
	public DicType getDicTypeById(String dicTypeId) throws Exception;
	
	/**
	 * �����ֵ�����
	 * @param dicType
	 * @throws Exception
	 */
	public void updateDicType(DicType dicType) throws Exception;
	
	/**
	 * ɾ���ֵ�����
	 * @param dicType
	 * @throws Exception
	 */
	public void deleteDicType(DicType dicType) throws Exception;
	
}
