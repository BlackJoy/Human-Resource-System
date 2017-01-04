package com.sys.hr.employee.dao;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.hr.employee.Employee;

public interface IEmployeeDAO extends IBaseDAO {

	public Employee findEmployeeById(java.io.Serializable id) throws HibernateException;
}
