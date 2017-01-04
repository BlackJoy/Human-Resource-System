package com.sys.hr.employee.dao.impl;

import java.io.Serializable;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.hr.employee.Employee;
import com.sys.hr.employee.dao.IEmployeeDAO;

public class EmployeeDAOImpl extends BaseDAO implements IEmployeeDAO {

	public Employee findEmployeeById(Serializable id) throws HibernateException {
		return (Employee)findById(Employee.class, id);
	}

}
