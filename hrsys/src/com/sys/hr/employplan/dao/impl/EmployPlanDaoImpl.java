package com.sys.hr.employplan.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;
import com.sys.common.Page;
import com.sys.common.dao.BaseDAO;
import com.sys.hr.employplan.dao.EmployPlanDao;

public class EmployPlanDaoImpl extends BaseDAO implements EmployPlanDao {

	public Object findSQL2UniqueEntity(final String sql, final Class clz) throws HibernateException{
		return this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).addEntity(clz).uniqueResult();
			}
			
		});
	}

	public Page findEmployPlanByOrgId(String orgId, int pageIndex, int pageSize)
			throws HibernateException {
		// TODO Auto-generated method stub
		String sql;
		if(orgId == null || orgId.equals("")){
			sql="select * from TBL_EMPLOYPLAN where org='' and status=0";
		}else{
			sql="select * from TBL_EMPLOYPLAN where org='"+orgId+"' and status=0";
		}
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public Page findEmployPlanMangByOrgId(String orgId, int pageIndex,
			int pageSize) throws HibernateException {
		// TODO Auto-generated method stub
		String sql;
		if(orgId == null || orgId.equals("")){
			sql="select * from TBL_EMPLOYPLAN where org='' and status in(1,2,3)";
		}else{
			sql="select * from TBL_EMPLOYPLAN where org='"+orgId+"' and status in(1,2,3)";
		}
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}
}
