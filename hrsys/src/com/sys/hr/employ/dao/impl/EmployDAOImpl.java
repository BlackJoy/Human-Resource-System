package com.sys.hr.employ.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.sys.common.Page;
import com.sys.common.dao.BaseDAO;
import com.sys.hr.employ.dao.IEmployDAO;

/**
 * 招聘管理数据访问实现
 * 
 * @author Administrator
 * 
 */
public class EmployDAOImpl extends BaseDAO implements IEmployDAO {

	public Page findYingPinByOrgId(String orgId, int pageIndex, int pageSize)
			throws HibernateException {
		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from TBL_employ e "
				+ "left join Tbl_Dicdata d "
				+ "on e.education=d.id "
				+ "left join Tbl_Dicdata d1 "
				+ "on e.position=d1.id "
				+ "inner join "
				+ "(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 "
				+ "start with o.id='"
				+ orgId
				+ "' connect by o.orgparentid=prior id) o "
				+ "on e.orgid=o.id " + "where e.employstatus=1";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

//	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize)
//			throws HibernateException {
//		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from TBL_employ e "
//				+ "left join Tbl_Dicdata d "
//				+ "on e.education=d.id "
//				+ "left join Tbl_Dicdata d1 "
//				+ "on e.position=d1.id "
//				+ "inner join "
//				+ "(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 "
//				+ "start with o.id='"
//				+ orgId
//				+ "' connect by o.orgparentid=prior id) o "
//				+ "on e.orgid=o.id " + "where e.employstatus=2";
//		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
//	}
	
	public Page findLuYongByOrgId(String orgId, int pageIndex, int pageSize)
			throws HibernateException {
		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from " +
				"(select * from (select * from Tbl_Employee e where e.employeestatus=2) e " +
				"inner join (select e2p.employee_id, e2p.org_id, e2p.position_id position from Tbl_Employee2position e2p where e2p.positiontype=1 and e2p.positionstatus=1) e2p " +
				"on e.id=e2p.employee_id) e " +
				"left join Tbl_Dicdata d " +
				"on e.education=d.id " +
				"left join Tbl_Dicdata d1 " +
				"on e.position=d1.id " +
				"inner join " +
				"(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 " +
				"start with o.id='"+orgId+"' connect by o.orgparentid=prior id) o " +
				"on e.orgid=o.id";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	// 试用[2]->待入职[3]->入职审批中[4]->已入职[5]、试用[2]
	// 查出录用表和流程表相关联的数据，以及录用表中待入职的数据，并按流程开始时间降序排列
	public Page findRuZhiByOrgId(String orgId, int pageIndex, int pageSize)
			throws HibernateException {
//		String sql = "select * from ( "
//				+ "select e.*, ae.id aid, ae.status astatus, ae.starttime astarttime "
//				+ "from (select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from TBL_employ e "
//				+ "left join Tbl_Dicdata d "
//				+ "on e.education=d.id "
//				+ "left join Tbl_Dicdata d1 "
//				+ "on e.position=d1.id "
//				+ "inner join "
//				+ "(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1  start with o.id='"
//				+ orgId
//				+ "' "
//				+ "connect by o.orgparentid=prior id) o "
//				+ "on e.orgid=o.id) e "
//				+ "inner join TBL_APPROVE_EXEC ae "
//				+ "on e.id=ae.contentid "
//				+ "union "
//				+ "select e.*, null aid, 0 astatus, null astarttime "
//				+ "from (select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from TBL_employ e "
//				+ "left join Tbl_Dicdata d "
//				+ "on e.education=d.id "
//				+ "left join Tbl_Dicdata d1 "
//				+ "on e.position=d1.id "
//				+ "inner join "
//				+ "(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 start with o.id='"
//				+ orgId + "' " + "connect by o.orgparentid=prior id) o "
//				+ "on e.orgid=o.id) e " + "where e.employstatus=3) t "
//				+ "order by t.astarttime desc";
		String sql = "select * from ( " +
				"select e.*, ae.id aid, ae.status astatus, ae.starttime astarttime, ae.isoverandsave " +
				"from (select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from " +
				"(select * from (select * from Tbl_Employee e) e " +
				"inner join (select e2p.employee_id, e2p.org_id, e2p.position_id position from Tbl_Employee2position e2p where e2p.positiontype=1 and e2p.positionstatus=1) e2p " +
				"on e.id=e2p.employee_id) e " +
				"left join Tbl_Dicdata d " +
				"on e.education=d.id " +
				"left join Tbl_Dicdata d1 " +
				"on e.position=d1.id " +
				"inner join " +
				"(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 " +
				"start with o.id='"+orgId+"' connect by o.orgparentid=prior id) o " +
				"on e.orgid=o.id) e " +
				"inner join TBL_APPROVE_EXEC ae " +
				"on e.id=ae.contentid " +
				"union " +
				"select e.*, null aid, 0 astatus, null astarttime, 0 isoverandsave " +
				"from (select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from " +
				"(select * from (select * from Tbl_Employee e where e.employeestatus=3) e " +
				"inner join (select e2p.employee_id, e2p.org_id, e2p.position_id position from Tbl_Employee2position e2p where e2p.positiontype=1 and e2p.positionstatus=1) e2p " +
				"on e.id=e2p.employee_id) e " +
				"left join Tbl_Dicdata d " +
				"on e.education=d.id " +
				"left join Tbl_Dicdata d1 " +
				"on e.position=d1.id " +
				"inner join " +
				"(select o.id,o.orgshortname,o.orgcode from TBL_ORG o where o.orgstatus=1 " +
				"start with o.id='"+orgId+"' connect by o.orgparentid=prior id) o " +
				"on e.orgid=o.id) e) t " +
				"order by t.astarttime desc";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public List findRuZhiById(String id) throws HibernateException {
//		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from TBL_employ e "
//				+ "left join Tbl_Dicdata d "
//				+ "on e.education=d.id "
//				+ "left join Tbl_Dicdata d1 "
//				+ "on e.position=d1.id "
//				+ "inner join TBL_ORG o "
//				+ "on e.orgid=o.id "
//				+ "where e.id='"
//				+ id + "'";
		String sql = "select o.orgshortname, e.*, d.dicdata_name edu, d1.dicdata_name po from " +
				"(select * from (select * from Tbl_Employee e where e.id='"+id+"') e " +
				"inner join (select e2p.employee_id, e2p.org_id, e2p.position_id position from Tbl_Employee2position e2p where e2p.positiontype=1 and e2p.positionstatus=1) e2p " +
				"on e.id=e2p.employee_id) e " +
				"left join Tbl_Dicdata d " +
				"on e.education=d.id " +
				"left join Tbl_Dicdata d1 " +
				"on e.position=d1.id " +
				"inner join TBL_ORG o " +
				"on e.orgid=o.id";
		return findSQL2MapList(sql);
	}

}
