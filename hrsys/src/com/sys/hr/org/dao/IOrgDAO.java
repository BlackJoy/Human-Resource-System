package com.sys.hr.org.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.IBaseDAO;
import com.sys.hr.org.Org;

public interface IOrgDAO extends IBaseDAO {

	public List<Org> findAllOrg() throws HibernateException;
	
}
