package com.sys.approve.dao.impl;

import org.hibernate.HibernateException;

import com.sys.approve.dao.IApproveDAO;
import com.sys.common.Page;
import com.sys.common.dao.BaseDAO;

/**
 * 审批数据访问类
 * @author Administrator
 *
 */
public class ApproveDAOImpl extends BaseDAO implements IApproveDAO {

	public Page getApproveList(String hql, int pageIndex, int pageSize)
			throws HibernateException {
		return findPaginationByHQL_EntityList(hql, pageIndex, pageSize);
	}

	public Page getEmpByOrgId(String sql, int pageIndex, int pageSize)
			throws HibernateException {
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public Page getBacklogApproveList(String eid, int pageIndex, int pageSize)
			throws HibernateException {
		String sql = "select * from TBL_APPROVE_EXEC ae " +
				"where ae.status=1 and ae.id in" +
				"(select afe.approveexecid from TBL_APPROVE_FLOW_EXEC afe " +
				"where afe.approverid='"+eid+"' and afe.status=0 and afe.approvetype=1)";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public Page getReviewApproveList(String eid, int pageIndex, int pageSize)
			throws HibernateException {
		String sql = "select * from Tbl_Approve_Exec ae " +
				"where ae.status=1 and ae.id in" +
				"(select afe.approveexecid from Tbl_Approve_Flow_Exec afe " +
				"where afe.approverid='"+eid+"' and afe.status=0 and afe.approvetype=2)";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	public Page getCompletedApproveList(String eid, int pageIndex, int pageSize)
			throws HibernateException {
		String sql = "select * from Tbl_Approve_Exec ae " +
				"where ae.id in" +
				"(select afe.approveexecid from Tbl_Approve_Flow_Exec afe " +
				"where afe.approverid='"+eid+"' and afe.status<>0)";
		return findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

}
