package com.sys.hr.org.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.dao.BaseDAO;
import com.sys.hr.org.Org;
import com.sys.hr.org.dao.IOrgDAO;

public class OrgDAOImpl extends BaseDAO implements IOrgDAO {

	public List<Org> findAllOrg() throws HibernateException {
		String sql = "select * from TBL_Org o order by o.ORGCODE";
		return this.findSQL2EntityList(sql, Org.class);
	}

}
