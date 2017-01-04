package com.sys.hr.employ.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sys.common.Page;
import com.sys.hr.employ.Employ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;

/**
 * ��Ƹ����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface IEmployBIZ {

	/**
	 * ����ӦƸ��Ϣ
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ������֯
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Org findOrgById(String orgId) throws Exception;
	
	/**
	 * ����ӦƸ��Ϣ
	 * @param employ
	 * @throws Exception
	 */
	public void saveYingPin(Employ employ) throws Exception;
	
	/**
	 * ����ID����ӦƸ��Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map findYingPinById(String id) throws Exception;
	
	/**
	 * �޸�ӦƸ��Ϣ
	 * @param employ
	 * @throws Exception
	 */
	public void updateYingPin(Employ employ) throws Exception;
	
	/**
	 * ɾ��ӦƸ��Ϣ
	 * @param id
	 * @throws Exception
	 */
	public void deleteYingPin(String id) throws Exception;
	
	/**
	 * ӦƸת�����ÿ���
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToShiYong(String id, Date shiyongTime) throws Exception;
	
	/**
	 * ����¼����Ϣ
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * �޸�¼����Ϣ
	 * @param employee
	 * @throws Exception
	 */
	public void updateLuYong(Employee employee) throws Exception;
	
	/**
	 * ����ID����¼����Ϣ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map findLuYongById(String id) throws Exception;
	
	/**
	 * ת����ְ
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToRuZhi(String id, Date ruzhiTime) throws Exception;
	
	/**
	 * ת��ӦƸ
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToYingPin(String id) throws Exception;
	
	/**
	 * ������ְ��Ϣ
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ת��¼��
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToLuYong(String id) throws Exception;
	
	/**
	 * ����ID���Ҵ���ְ��Ա
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List findRuZhiById(String id) throws Exception;
	
	/**
	 * ת����ְ����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateToRuZhiApprove(String id) throws Exception;
	
	/**
	 * �����������ݴ浵
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean flowOverAndSaveData(String id) throws Exception;
}
