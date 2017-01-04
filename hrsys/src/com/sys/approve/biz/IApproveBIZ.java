package com.sys.approve.biz;

import java.util.List;

import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.ApproveFlowExec;
import com.sys.common.Page;

/**
 * ����ҵ��ӿ�
 * @author Administrator
 *
 */
public interface IApproveBIZ {

	/**
	 * ��ѯ�����б�
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getApproveList(int pageIndex, int pageSize) throws Exception;
	
	/**
	 * �����֯��Ż��Ա��
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getEmpByOrgId(String orgId, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ��������
	 * @throws Exception
	 */
	public void saveApprove(Approve approve, List<ApproveFlow> flowList) throws Exception;
	
	/**
	 * ���ID��������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Approve getApprove(String id) throws Exception;
	
	/**
	 * �������ID��������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ApproveFlow> getFlowsByApproveId(String id) throws Exception;
	
	/**
	 * �޸�����
	 * @throws Exception
	 */
	public void updateApprove(Approve approve, List<ApproveFlow> flowList) throws Exception;
	
	/**
	 * ɾ������
	 * @param id
	 * @throws Exception
	 */
	public void deleteApprove(String id) throws Exception;
	
	/**
	 * ��ȡ��������
	 * @return
	 * @throws Exception
	 */
	public List getAllApprove() throws Exception;
	
	/**
	 * ����ִ�������ִ������
	 * @param ae
	 * @throws Exception
	 */
	public void saveApproveExecAndFlowExec(ApproveExec ae) throws Exception;
	
	/**
	 * ���Ա��ID��ô���������
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Page getBacklogApproveList(String eid, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ���ID���Ҵ�������Ŀ
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ApproveExec getBacklogApproveById(String id) throws Exception;
	
	/**
	 * ��ݴ�������ĿID���Ҵ���������
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ApproveFlowExec> getBacklogApproveFlowsByApproveId(String id) throws Exception;
	
	/**
	 * ��õ�ǰ�����������
	 * @param approveId
	 * @param approverId
	 * @return
	 * @throws Exception
	 */
	public List<ApproveFlowExec> getCurrentBacklogFlows(String approveId, String approverId) throws Exception;
	
	/**
	 * ������������
	 * @param id
	 * @param approveStatus
	 * @param approveComment
	 * @return
	 * @throws Exception
	 */
	public int updateFlowApprove(String id, int approveStatus, String approveComment) throws Exception;
	
	/**
	 * ���Ա��ID��ô�������
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Page getReviewApproveList(String eid, int pageIndex, int pageSize) throws Exception;
	
	/**
	 * ���Ա��ID�������������
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Page getCompletedApproveList(String eid, int pageIndex, int pageSize) throws Exception;
}
