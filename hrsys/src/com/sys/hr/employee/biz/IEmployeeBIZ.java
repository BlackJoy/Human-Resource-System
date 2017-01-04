package com.sys.hr.employee.biz;

import java.util.List;

import com.sys.hr.employee.Employee;

/**
 * 员工业务接口
 * @author Administrator
 *
 */
public interface IEmployeeBIZ {

	/**
	 * 根据员工ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Employee findEmployeeById(java.io.Serializable id) throws Exception;
	
	/**
	 * 查找组织下所有员工
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List findEmpsByOrgId(String orgId) throws Exception;
	
}
