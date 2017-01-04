package com.sys.approve.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.ApproveFlowExec;
import com.sys.approve.biz.IApproveBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;

public class ApproveAction extends ActionSupport {

	private static final long serialVersionUID = -7011433942278232651L;
	private IApproveBIZ approveBiz;
	private IBaseBIZ commonBiz;
	private int pageIndex;
	private Approve approve;
	private String approveFlow;
	private String orgId;
	private String id;
	private String eid;
	private String approverId;
	private int approveStatus;
	private String approveComment;

	public IApproveBIZ getApproveBiz() {
		return approveBiz;
	}

	public void setApproveBiz(IApproveBIZ approveBiz) {
		this.approveBiz = approveBiz;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Approve getApprove() {
		return approve;
	}

	public void setApprove(Approve approve) {
		this.approve = approve;
	}

	public String getApproveFlow() {
		return approveFlow;
	}

	public void setApproveFlow(String approveFlow) {
		this.approveFlow = approveFlow;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public int getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveComment() {
		return approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

	/**
	 * 审批列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = approveBiz.getApproveList(pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("approve_list_page", page);
		return "list";
	}

	/**
	 * 获取员工列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String empList() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = approveBiz.getEmpByOrgId(orgId, pageIndex, -1);
		ActionContext.getContext().put("emplist_page", page);
		return "empList";
	}

	/**
	 * 新增审批流程
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String doAdd() throws Exception {
		List<ApproveFlow> appFlowList = null;
		if (approveFlow != null && approveFlow.length() > 0) {
			JSONArray json = JSONArray.fromObject(approveFlow);
			appFlowList = (List<ApproveFlow>) JSONArray.toCollection(json,
					ApproveFlow.class);
		}
		if (appFlowList != null && appFlowList.size() > 0) {
			approveBiz.saveApprove(approve, appFlowList);
			ActionContext.getContext().put("approveMsg", "保存成功");
		} else {
			ActionContext.getContext().put("approveMsg", "审批流程不能为空");
		}
		return "toAdd";
	}

	/**
	 * 根据ID加载审批项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findApprove() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个审批项目");
			return "toList";
		}
		Approve apr = approveBiz.getApprove(id);
		if (apr == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "toList";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(id);
		if (flowList == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "toList";
		}
		Map<Integer, List<ApproveFlow>> flowMap = new TreeMap<Integer, List<ApproveFlow>>();
		for (ApproveFlow flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlow> fl = new ArrayList<ApproveFlow>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		ActionContext.getContext().put("selApp", apr);
		ActionContext.getContext().put("selFlow", flowMap);
		return "toDetail";
	}

	/**
	 * 跳转修改页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个审批项目");
			return "toList";
		}
		approve = approveBiz.getApprove(id);
		if (approve == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "toList";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(id);
		if (flowList == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "toList";
		}
		Map<Integer, List<ApproveFlow>> flowMap = new TreeMap<Integer, List<ApproveFlow>>();
		for (ApproveFlow flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlow> fl = new ArrayList<ApproveFlow>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		ActionContext.getContext().put("selFlow", flowMap);
		return "toUpdate";
	}

	/**
	 * 修改审批
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdate() throws Exception {
		if (approve == null) {
			ActionContext.getContext().put("approveMsg", "请选择一个审批项目");
			return "toList";
		}
		List<ApproveFlow> appFlowList = null;
		if (approveFlow != null && approveFlow.length() > 0) {
			JSONArray json = JSONArray.fromObject(approveFlow);
			appFlowList = (List<ApproveFlow>) JSONArray.toCollection(json,
					ApproveFlow.class);
		}
		if (appFlowList != null && appFlowList.size() > 0) {
			approveBiz.updateApprove(approve, appFlowList);
			ActionContext.getContext().put("approveMsg", "保存成功");
		} else {
			ActionContext.getContext().put("approveMsg", "审批流程不能为空");
		}
		return "toList";
	}

	/**
	 * 删除审批
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDelete() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个审批项目");
			return "toList";
		}
		approveBiz.deleteApprove(id);
		ActionContext.getContext().put("approveMsg", "删除成功");
		return "toList";
	}

	/**
	 * 查找待审批项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String backlogApprove() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = approveBiz.getBacklogApproveList(eid, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("backlogApprove_page", page);
		return "tobacklog";
	}

	/**
	 * 跳转执行审批页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toApprove() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个待审批项目");
			return "tobacklogAction";
		}
		ApproveExec apr = approveBiz.getBacklogApproveById(id);
		if (apr == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "tobacklogAction";
		}// ...
		List<ApproveFlowExec> flowList = approveBiz
				.getBacklogApproveFlowsByApproveId(id);
		if (flowList == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "tobacklogAction";
		}
		Map<Integer, List<ApproveFlowExec>> flowMap = new TreeMap<Integer, List<ApproveFlowExec>>();
		for (ApproveFlowExec flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlowExec> fl = new ArrayList<ApproveFlowExec>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		List<ApproveFlowExec> currFlow = approveBiz.getCurrentBacklogFlows(id,
				approverId);
		ActionContext.getContext().put("selApp", apr);
		ActionContext.getContext().put("selFlow", flowMap);
		ActionContext.getContext().put("currFlow", currFlow.get(0));
		return "toApprove";
	}

	/**
	 * 保存审批
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFlowApprove() throws Exception {
		
		String status = 
				String.valueOf(approveBiz.updateFlowApprove(id, approveStatus, approveComment));
		commonBiz.saveFlowEntityById(id,status);
		ActionContext.getContext().put("approveMsg", "审批保存成功");
		return "tobacklogAction";
	}

	/**
	 * 查找待传阅项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reviewApprove() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = approveBiz.getReviewApproveList(eid, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("reviewApprove_page", page);
		return "toreview";
	}
	
	/**
	 * 跳转执行传阅页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toReviewApprove() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个待传阅项目");
			return "toreviewAction";
		}
		ApproveExec apr = approveBiz.getBacklogApproveById(id);
		if (apr == null) {
			ActionContext.getContext().put("approveMsg", "传阅项目不存在，请重新选择");
			return "toreviewAction";
		}// ...
		List<ApproveFlowExec> flowList = approveBiz
				.getBacklogApproveFlowsByApproveId(id);
		if (flowList == null) {
			ActionContext.getContext().put("approveMsg", "传阅项目不存在，请重新选择");
			return "toreviewAction";
		}
		Map<Integer, List<ApproveFlowExec>> flowMap = new TreeMap<Integer, List<ApproveFlowExec>>();
		for (ApproveFlowExec flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlowExec> fl = new ArrayList<ApproveFlowExec>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		List<ApproveFlowExec> currFlow = approveBiz.getCurrentBacklogFlows(id,
				approverId);
		ActionContext.getContext().put("selApp", apr);
		ActionContext.getContext().put("selFlow", flowMap);
		ActionContext.getContext().put("currFlow", currFlow.get(0));
		return "toexecutereview";
	}

	/**
	 * 查找待传阅项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public String completedApprove() throws Exception {
		try {
			if (pageIndex < 1) {
				pageIndex = 1;
			}
			Page page = approveBiz.getCompletedApproveList(eid, pageIndex,
					IConstant.PAGINATION_PAGESIZE);
			ActionContext.getContext().put("completedApprove_page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "tocompleted";
	}
	
	/**
	 * 跳转已批阅流程详细页
	 * @return
	 * @throws Exception
	 */
	public String completedApproveDetail() throws Exception {
		if (id == null || id.equals("")) {
			ActionContext.getContext().put("approveMsg", "请选择一个待审批项目");
			return "tocompletedAction";
		}
		ApproveExec apr = approveBiz.getBacklogApproveById(id);
		if (apr == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "tocompletedAction";
		}// ...
		List<ApproveFlowExec> flowList = approveBiz.getBacklogApproveFlowsByApproveId(id);
		if (flowList == null) {
			ActionContext.getContext().put("approveMsg", "审批项目不存在，请重新选择");
			return "tocompletedAction";
		}
		Map<Integer, List<ApproveFlowExec>> flowMap = new TreeMap<Integer, List<ApproveFlowExec>>();
		for (ApproveFlowExec flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlowExec> fl = new ArrayList<ApproveFlowExec>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		ActionContext.getContext().put("selApp", apr);
		ActionContext.getContext().put("selFlow", flowMap);
		return "tocompletedApproveDetail";
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}
}
