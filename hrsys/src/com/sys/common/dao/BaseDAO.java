package com.sys.common.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sys.common.Page;

/**
 * DAO����
 * @author Administrator
 *
 */
public class BaseDAO extends HibernateDaoSupport implements IBaseDAO{
	
	/**
	 * ͨ��SQL��ѯʵ�弯��
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public List findSQL2EntityList(final String sql, final Class clz) throws HibernateException{
		return (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).addEntity(clz).list();
			}
			
		});
	}
	
	/**
	 * ͨ��SQL��ѯ����ʵ��
	 * @param sql
	 * @param clz
	 * @return
	 * @throws HibernateException
	 */
	public Object findSQL2UniqueEntity(final String sql, final Class clz) throws HibernateException{
		return this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).addEntity(clz).uniqueResult();
			}
			
		});
	}

	public Object findById(Class clz, Serializable id)
			throws HibernateException {
		return this.getHibernateTemplate().get(clz, id);
	}

	public java.io.Serializable save(Object obj) throws HibernateException {
		return this.getHibernateTemplate().save(obj);
	}

	public void update(Object obj) throws HibernateException {
		this.getHibernateTemplate().update(obj);
	}

	public void saveOrUpdate(Object obj) throws HibernateException {
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
	public void delete(Object obj) throws HibernateException {
		this.getHibernateTemplate().delete(obj);
	}

	public Page findPaginationByHQL_EntityList(final String hql, final int pageIndex, final int pageSize)
			throws HibernateException {
		Page page = null;
		page = (Page)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List list = null;
				int rowCount = 0;
				Query query = session.createQuery(hql);
				rowCount = query.list().size();
				if (pageSize > 0) {
					query.setFirstResult((pageIndex - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				list = query.list();
				return new Page(list, pageIndex, pageSize, rowCount);
			}
			
		});
		return page;
	}

	public Page findPaginationBySQL_MapList(final String sql, final int pageIndex, final int pageSize)
			throws HibernateException {
		Page page = null;
		page = (Page)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List list = null;
				int rowCount = 0;
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				rowCount = sqlQuery.list().size();
				if (pageSize > 0) {
					sqlQuery.setFirstResult((pageIndex - 1) * pageSize);
					sqlQuery.setMaxResults(pageSize);
				}
				list = sqlQuery.list();
				return new Page(list, pageIndex, pageSize, rowCount);
			}
		});
		return page;
	}

	public int deleteSQL(final String sql) throws HibernateException {
		int i = 0;
		i = (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				int rowCount = query.executeUpdate();
				return rowCount;
			}
		});
		return i;
	}

	public int updateSQL(final String sql) throws HibernateException {
		int i = 0;
		i = (Integer)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				int rowCount = query.executeUpdate();
				return rowCount;
			}
		});
		return i;
	}

	public List findSQL2MapList(final String sql) throws HibernateException {
		List list = null;
		list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return sqlQuery.list();
			}
		});
		return list;
	}

	public Object getEntityById(String id, Class en) {
		// TODO Auto-generated method stub
		return  this.getHibernateTemplate().get(en, id);
	
	}

	// public Object findObjectById_page(String id, String pageIndex,
	// String pageSize) {
	// // TODO Auto-generated method stub
	//
	// return null;
	// }
	
	public Page findObjectByOrgId_page(String id, int pageIndex,
			int pageSize,String tableName)
			throws HibernateException {
		String sql = "select o.orgshortname, e.* from "+tableName+" e "
				+ "inner join "
				+ "(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 "
				+ "start with o.id='"
				+ id
				+ "' connect by o.orgparentid=prior id) o "
				+ "on e.orgid=o.id " ;
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public void createBySql( final String sql) throws SQLException {
		// TODO Auto-generated method stub
//		this.getHibernateTemplate().execute(new HibernateCallback(){
//
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				 return session.createSQLQuery(sql);
//			}
//			
//		});
		Connection con=getHibernateTemplate().getSessionFactory().openSession().connection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.execute();
		con.commit();
//		if(con!=null){
//			con.close();
//		}
		if(ps!=null){
			ps.close();
		}
	}

//	public void executeByJdbcSql(String sql) throws Exception {
//		// TODO Auto-generated method stub
//		Connection con=getHibernateTemplate().getSessionFactory().openSession().connection();
//		PreparedStatement ps=con.prepareStatement(sql);
//		ps.execute();
//		if(con!=null){
//			con.close();
//		}
//		if(ps!=null){
//			ps.close();
//		}
//		
//	}

	public void executeByJdbcSql(String tableName, String id, double value)
			throws Exception {
		// TODO Auto-generated method stub
		final String sql="insert into hrsys."+tableName+" values("+id+","+value+")";
		this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).executeUpdate();
			}
			
		});
	}

	public void executeByDmlSql(final String sql) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).executeUpdate();
			}
			
		});
	}
	/**
	 * @author Jp
	 */
		public Object getEntityByTableName(String tableName, String id) {
			// TODO Auto-generated method stub
			final String sql = "select t.* from "+tableName+" t where t.id="+id;
			Object obj = this.getHibernateTemplate().execute(new HibernateCallback(){

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					SQLQuery sqlQuery = session.createSQLQuery(sql);
					sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
					return sqlQuery.uniqueResult();
				}
			});
			return obj;
		}

}
