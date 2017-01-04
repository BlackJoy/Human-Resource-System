package com.sys.hr.employee.biz;

import java.util.List;

import com.sys.hr.employee.Employee;

/**
 * Ա��ҵ��ӿ�
 * @author Administrator
 *
 */
public interface IEmployeeBIZ {

	/**
	 * ����Ա��ID����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Employee findEmployeeById(java.io.Serializable id) throws Exception;
	
	/**
	 * ������֯������Ա��
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List findEmpsByOrgId(String orgId) throws Exception;
	
}
