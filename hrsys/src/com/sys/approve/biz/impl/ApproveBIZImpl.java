package com.sys.approve.biz.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.ApproveFlowExec;
import com.sys.approve.biz.IApproveBIZ;
import com.sys.approve.dao.IApproveDAO;
import com.sys.common.Page;
import com.sys.hr.org.Org;

/**
 * ����ҵ��ʵ����
 * @author Administrator
 *
 */
public class ApproveBIZImpl implements IApproveBIZ {

	private IApproveDAO approveDao;
	public IApproveDAO getApproveDao() {
		return approveDao;
	}
	public void setApproveDao(IApproveDAO approveDao) {
		this.approveDao = approveDao;
	}
	
	/**
	 * ��ȡ��������
	 */
	public Page getApproveList(int pageIndex, int pageSize) throws Exception {
		String hql = "from Approve a";
		return approveDao.getApproveList(hql, pageIndex, pageSize);
	}
	
	public Page getEmpByOrgId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		if (orgId == null || orgId.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org)approveDao.findSQL2UniqueEntity(sql, Org.class);
			orgId = org.getId();
		}
//		String sql = "select e.id, e.employeecode, e.employeename, o.orgshortname" +
//				" from Tbl_Employee e inner join " +
//				" (SELECT o.* FROM Tbl_Org o where o.orgstatus=1" +
//				" START WITH o.id = '"+orgId+"' CONNECT BY o.orgparentid = PRIOR id) o" +
//				" on e.orgid = o.id";
		
		String sql = "select ep.employee_id id,ep.employeecode,ep.employeename,o.orgshortname,ep.dicdata_name position from" +
				" (select e2p.id,e2p.employee_id,e.employeecode,e.employeename,e2p.org_id,dd.dicdata_name,dd.dicdata_code from TBL_EMPLOYEE2POSITION e2p" +
				" left join Tbl_Dicdata dd" +
				" on e2p.position_id=dd.id" +
				" left join Tbl_Employee e" +
				" on e2p.employee_id=e.id" +
				" where e2p.positionstatus=1) ep" +
				" inner join" +
				" (select o.id,o.orgshortname,o.orgcode from TBL_ORG o" +
				" where o.orgstatus=1 " +
				" start with o.id= '"+orgId+"' connect by o.orgparentid=prior id) o " +
				" on ep.org_id=o.id" +
				" order by o.orgcode,ep.dicdata_code";
		Page page = approveDao.findPaginationBySQL_MapList(sql, pageIndex, pageSize);
		return page;
	}
	
	public void saveApprove(Approve approve, List<ApproveFlow> flowList) throws Exception {
		String id = (String)approveDao.save(approve);
		for (ApproveFlow flow : flowList) {
			flow.setApproveId(id);
			approveDao.save(flow);
		}
	}
	
	public Approve getApprove(String id) throws Exception {
		return (Approve)approveDao.findById(Approve.class, id);
	}
	
	public List<ApproveFlow> getFlowsByApproveId(String id) throws Exception {
		String sql = "select * from TBL_APPROVE_FLOW af where af.approveid='"+id+"' order by af.orderid, af.approvetype";
		return approveDao.findSQL2EntityList(sql, ApproveFlow.class);
	}
	public void updateApprove(Approve approve, List<ApproveFlow> flowList)
			throws Exception {
		approveDao.update(approve);
		String sql = "delete TBL_APPROVE_FLOW af where af.approveid='"+approve.getId()+"'";
		approveDao.deleteSQL(sql);
		for (ApproveFlow flow : flowList) {
			flow.setApproveId(approve.getId());
			approveDao.save(flow);
		}
	}
	public void deleteApprove(String id) throws Exception {
		String sql = "delete TBL_APPROVE a where a.id='"+id+"'";
		approveDao.deleteSQL(sql);
		sql = "delete TBL_APPROVE_FLOW af where af.approveid='"+id+"'";
		approveDao.deleteSQL(sql);
	}
	public List getAllApprove() throws Exception {
		String sql = "select * from TBL_APPROVE";
		return approveDao.findSQL2MapList(sql);
	}
	public void saveApproveExecAndFlowExec(ApproveExec ae) throws Exception {
		String aeid = (String)approveDao.save(ae);
		String aid = ae.getApproveId();
		String sql = "select * from TBL_APPROVE_FLOW af where af.approveid='"+aid+"' order by af.orderid, af.approvetype";
		List<ApproveFlow> aflist = approveDao.findSQL2EntityList(sql, ApproveFlow.class);
		for (int i = 0; i < aflist.size(); i++) {
			ApproveFlow af = (ApproveFlow) aflist.get(i);
			ApproveFlowExec afe = new ApproveFlowExec();
			afe.setApproveExecId(aeid);
			afe.setApproveFlowId(af.getId());
			afe.setOrderId(af.getOrderId());
			afe.setApproverId(af.getApproverId());
			afe.setApproverName(af.getApproverName());
			afe.setApproveType(af.getApproveType());
			if (af.getOrderId() == 1) {
				afe.setStatus(0);
			}
			approveDao.save(afe);
		}
	}
	public Page getBacklogApproveList(String eid, int pageIndex, int pageSize) throws Exception {
		return approveDao.getBacklogApproveList(eid, pageIndex, pageSize);
	}
	public ApproveExec getBacklogApproveById(String id) throws Exception {
		return (ApproveExec)approveDao.findById(ApproveExec.class, id);
	}
	public List<ApproveFlowExec> getBacklogApproveFlowsByApproveId(String id)
			throws Exception {
		String sql = "select * from TBL_APPROVE_FLOW_EXEC af where af.approveexecid='"+id+"' order by af.orderid, af.approvetype";
		return (List<ApproveFlowExec>)approveDao.findSQL2EntityList(sql, ApproveFlowExec.class);
	}
	public List<ApproveFlowExec> getCurrentBacklogFlows(String approveId,
			String approverId) throws Exception {
		String sql = "select * from TBL_APPROVE_FLOW_EXEC afe where afe.approveexecid='"+approveId+"' and afe.approverid='"+approverId+"' and afe.status=0";
		return (List<ApproveFlowExec>)approveDao.findSQL2EntityList(sql, ApproveFlowExec.class);
	}
	public int updateFlowApprove(String id, int approveStatus,
			String approveComment) throws Exception {
		//修改当前流程状态、审批时间、审批意见
				ApproveFlowExec afe = (ApproveFlowExec)approveDao.findById(ApproveFlowExec.class, id);
				afe.setStatus(approveStatus);
				afe.setApproveTime(new Date());
				afe.setApproveComment(approveComment);
				approveDao.update(afe);
				
				if (approveStatus != 3) {
					//获得审批项目
					ApproveExec ae = (ApproveExec)approveDao.findById(ApproveExec.class, afe.getApproveExecId());
					
					//获得下一个流程
					String sql = "select * from TBL_APPROVE_FLOW_EXEC afe where afe.approveexecid='"+afe.getApproveExecId()+"' and afe.orderid="+(afe.getOrderId()+1);
					List<ApproveFlowExec> nextFlows = (List<ApproveFlowExec>)approveDao.findSQL2EntityList(sql, ApproveFlowExec.class);
					
					//审批项目状态（1.正在进行、2.未通过、3.通过）
					//审批流程状态（0.待审、1.同意、2.不同意、3.已阅）
					if (approveStatus == 2) {//如果当前审批流程状态为2：不同意，则直接设置审批项目状态为2：未通过
						ae.setStatus(2);
						ae.setEndTime(new Date());
						approveDao.update(ae);
						return 5;//审批失败
					} else if(approveStatus == 1 && (nextFlows == null || nextFlows.size() == 0)){//如果当前审批流程状态为1：同意，且为最后一个流程，则直接设置审批项目状态为3：通过
						ae.setStatus(3);
						ae.setEndTime(new Date());
						approveDao.update(ae);
						return 3;//审批成功
					} else if(approveStatus == 1 && (nextFlows != null && nextFlows.size() > 0)){//如果当前审批流程状态为1：同意，且不是最后一个流程，则设置下一审批流程状态为0：待审
						for (Iterator it = nextFlows.iterator(); it.hasNext();) {
							ApproveFlowExec nafe = (ApproveFlowExec) it.next();
							nafe.setStatus(0);
							approveDao.update(nafe);
						}
					}
				}
				return 2;
	}
	public Page getReviewApproveList(String eid, int pageIndex, int pageSize)
			throws Exception {
		return approveDao.getReviewApproveList(eid, pageIndex, pageSize);
	}
	public Page getCompletedApproveList(String eid, int pageIndex, int pageSize)
			throws Exception {
		return approveDao.getCompletedApproveList(eid, pageIndex, pageSize);
	}

}
