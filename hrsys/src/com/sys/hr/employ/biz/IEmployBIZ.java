package com.sys.hr.employ.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sys.common.Page;
import com.sys.hr.employ.Employ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;

/**
 * 招聘管理业务接口
 * @author Administrator
 *
 */
public interface IEmployBIZ {

	/**
	 * 查找应聘信息
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 查找组织
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Org findOrgById(String orgId) throws Exception;
	
	/**
	 * 新增应聘信息
	 * @param employ
	 * @throws Exception
	 */
	public void saveYingPin(Employ employ) throws Exception;
	
	/**
	 * 根据ID查找应聘信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map findYingPinById(String id) throws Exception;
	
	/**
	 * 修改应聘信息
	 * @param employ
	 * @throws Exception
	 */
	public void updateYingPin(Employ employ) throws Exception;
	
	/**
	 * 删除应聘信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteYingPin(String id) throws Exception;
	
	/**
	 * 应聘转入试用考核
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToShiYong(String id, Date shiyongTime) throws Exception;
	
	/**
	 * 查找录用信息
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 修改录用信息
	 * @param employee
	 * @throws Exception
	 */
	public void updateLuYong(Employee employee) throws Exception;
	
	/**
	 * 根据ID查找录用信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map findLuYongById(String id) throws Exception;
	
	/**
	 * 转入入职
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToRuZhi(String id, Date ruzhiTime) throws Exception;
	
	/**
	 * 转入应聘
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToYingPin(String id) throws Exception;
	
	/**
	 * 查找入职信息
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * 转入录用
	 * @param id
	 * @param shiyongTime
	 * @throws Exception
	 */
	public boolean updateToLuYong(String id) throws Exception;
	
	/**
	 * 根据ID查找待入职人员
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List findRuZhiById(String id) throws Exception;
	
	/**
	 * 转入入职审批
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateToRuZhiApprove(String id) throws Exception;
	
	/**
	 * 审批结束数据存档
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean flowOverAndSaveData(String id) throws Exception;
}
