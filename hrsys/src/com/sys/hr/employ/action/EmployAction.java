package com.sys.hr.employ.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.biz.IApproveBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.hr.employ.Employ;
import com.sys.hr.employ.biz.IEmployBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;

public class EmployAction extends ActionSupport {

	private static final long serialVersionUID = -1363724273273955032L;
	private IEmployBIZ employBiz;
	private IApproveBIZ approveBiz;
	private IOrgBIZ orgBiz;
	private String orgId;
	private int pageIndex;
	private Employ employ;
	private String employId;
	private Date shiyongTime;
	private Date ruzhiTime;
	private String apId;
	private String apprName;
	private String id;
	private Employee emp;

	public IEmployBIZ getEmployBiz() {
		return employBiz;
	}

	public void setEmployBiz(IEmployBIZ employBiz) {
		this.employBiz = employBiz;
	}

	public IApproveBIZ getApproveBiz() {
		return approveBiz;
	}

	public void setApproveBiz(IApproveBIZ approveBiz) {
		this.approveBiz = approveBiz;
	}

	public IOrgBIZ getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(IOrgBIZ orgBiz) {
		this.orgBiz = orgBiz;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Employ getEmploy() {
		return employ;
	}

	public void setEmploy(Employ employ) {
		this.employ = employ;
	}

	public String getEmployId() {
		return employId;
	}

	public void setEmployId(String employId) {
		this.employId = employId;
	}

	public Date getShiyongTime() {
		return shiyongTime;
	}

	public void setShiyongTime(Date shiyongTime) {
		this.shiyongTime = shiyongTime;
	}

	public Date getRuzhiTime() {
		return ruzhiTime;
	}

	public void setRuzhiTime(Date ruzhiTime) {
		this.ruzhiTime = ruzhiTime;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getApprName() {
		return apprName;
	}

	public void setApprName(String apprName) {
		this.apprName = apprName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	/**
	 * 跳转应聘信息管理页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yingpin() throws Exception {
		return "yingpin";
	}

	/**
	 * 应聘信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yingpinlist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findYingPinByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("yingpin_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "yingpinlist";
	}

	/**
	 * 跳转应聘信息录入页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAddYingPin() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一个组织");
			return "yingpinlistAction";
		}
		Org org = employBiz.findOrgById(orgId);
		ActionContext.getContext().put("curr_org", org);
		return "toAddYingPin";
	}

	/**
	 * 新增应聘信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddYingPin() throws Exception {
		employBiz.saveYingPin(employ);
		ActionContext.getContext().put("employMsg", "保存成功");
		return "toAddYingPinAction";
	}

	/**
	 * 跳转应聘信息详细页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDetailYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条应聘信息");
			return "yingpinlistAction";
		}
		Map yp = employBiz.findYingPinById(employId);
		ActionContext.getContext().put("curr_yingpin", yp);
		return "toYingPinDetail";
	}

	/**
	 * 跳转应聘信息修改页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条应聘信息");
			return "yingpinlistAction";
		}
		Map yp = employBiz.findYingPinById(employId);
		List<Map> orgs = orgBiz.findOrgsIncludFullName();
		ActionContext.getContext().put("curr_yingpin", yp);
		ActionContext.getContext().put("orglist", orgs);
		return "toUpdate";
	}

	/**
	 * 修改应聘信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdateYingPin() throws Exception {
		employBiz.updateYingPin(employ);
		ActionContext.getContext().put("employMsg", "修改成功");
		return "yingpinlistAction";
	}

	/**
	 * 删除应聘信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDeleteYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条应聘信息");
			return "yingpinlistAction";
		}
		employBiz.deleteYingPin(employId);
		ActionContext.getContext().put("employMsg", "删除成功");
		return "yingpinlistAction";
	}

	/**
	 * 转入录用考核
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doShiYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条应聘信息");
			return "yingpinlistAction";
		}
		boolean b = employBiz.updateToShiYong(employId, shiyongTime);
		if (b) {
			ActionContext.getContext().put("employMsg", "已转入录用考核");
		} else {
			ActionContext.getContext().put("employMsg", "操作失败，请重试");
		}
		return "yingpinlistAction";
	}

	/**
	 * 跳转录用考核管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String luyong() throws Exception {
		return "luyong";
	}

	/**
	 * 录用管理列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String luyonglist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findLuYongByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("luyong_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "luyonglist";
	}

	/**
	 * 跳转录用修改页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdateLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条录用信息");
			return "luyonglistAction";
		}
		Map ly = employBiz.findLuYongById(employId);
		List<Map> orgs = orgBiz.findOrgsIncludFullName();
		ActionContext.getContext().put("curr_luyong", ly);
		ActionContext.getContext().put("orglist", orgs);
		return "toUpdateLuYong";
	}

	/**
	 * 修改录用信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdateLuYong() throws Exception {
		employBiz.updateLuYong(emp);
		ActionContext.getContext().put("employMsg", "修改成功");
		return "luyonglistAction";
	}

	/**
	 * 删除录用信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDeleteLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条录用信息");
			return "luyonglistAction";
		}
		employBiz.deleteYingPin(employId);
		ActionContext.getContext().put("employMsg", "删除成功");
		return "luyonglistAction";
	}

	/**
	 * 转入入职
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doRuZhi() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条录用信息");
			return "luyonglistAction";
		}
		boolean b = employBiz.updateToRuZhi(employId, ruzhiTime);
		if (b) {
			ActionContext.getContext().put("employMsg", "已转入入职管理");
		} else {
			ActionContext.getContext().put("employMsg", "操作失败，请重试");
		}
		return "luyonglistAction";
	}

	/**
	 * 转入应聘
	 * @return
	 * @throws Exception
	 */
	public String doYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条录用信息");
			return "luyonglistAction";
		}
		boolean b = employBiz.updateToYingPin(employId);
		if (b) {
			ActionContext.getContext().put("employMsg", "已转入应聘管理");
		} else {
			ActionContext.getContext().put("employMsg", "操作失败，请重试");
		}
		return "luyonglistAction";
	}
	
	/**
	 * 跳转录用信息详细页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDetailLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条录用信息");
			return "luyonglistAction";
		}
		Map ly = employBiz.findLuYongById(employId);
		ActionContext.getContext().put("curr_luyong", ly);
		return "toLuYongDetail";
	}
	
	/**
	 * 跳转入职管理主页
	 * @return
	 * @throws Exception
	 */
	public String ruzhi() throws Exception {
		return "ruzhi";
	}
	
	/**
	 * 入职管理列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ruzhilist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findRuZhiByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("ruzhi_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "ruzhilist";
	}
	
	/**
	 * 转入录用
	 * @return
	 * @throws Exception
	 */
	public String doLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条入职信息");
			return "ruzhilistAction";
		}
		boolean b = employBiz.updateToLuYong(employId);
		if (b) {
			ActionContext.getContext().put("employMsg", "已转入录用管理");
		} else {
			ActionContext.getContext().put("employMsg", "操作失败，请重试");
		}
		return "ruzhilistAction";
	}
	
	/**
	 * 转入发起流程页
	 * @return
	 * @throws Exception
	 */
	public String doFlow() throws Exception {
		try {
			if (employId == null || employId.equals("")) {
				ActionContext.getContext().put("employMsg", "请选择一条入职信息");
				return "ruzhilistAction";
			}
			List e = employBiz.findRuZhiById(employId);
			List ap = approveBiz.getAllApprove();
			System.out.println();
			if (e == null && e.size() == 0) {
				ActionContext.getContext().put("employMsg", "待入职信息不存在，请确认");
				return "ruzhilistAction";
			}
			ActionContext.getContext().put("curr_ruzhi", e);
			ActionContext.getContext().put("aplist", ap);
			ActionContext.getContext().put("employId", employId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toFlow";
	}
	
	/**
	 * 加载审批流程
	 * @return
	 * @throws Exception
	 */
	public String loadAppFlow() throws Exception {
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一个审批项目");
			return "toFlowAction";
		}
		Approve apr = approveBiz.getApprove(apId);
		if (apr == null) {
			ActionContext.getContext().put("employMsg", "审批项目不存在，请重新选择");
			return "toFlowAction";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(apId);
		if (flowList == null) {
			ActionContext.getContext().put("employMsg", "审批项目不存在，请重新选择");
			return "toFlowAction";
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
		return "toFlowDetail";
	}
	
	/**
	 * 保存审批流程
	 * @return
	 * @throws Exception
	 */
	public String saveAppFlow() throws Exception {
		try {
			if (apId == null || apId.equals("")) {
				ActionContext.getContext().put("employMsg", "请选择一个审批项目");
				return "toFlowAction";
			}
			Approve apr = approveBiz.getApprove(apId);
			if (apr == null) {
				ActionContext.getContext().put("employMsg", "审批项目不存在，请重新选择");
				return "toFlowAction";
			}
			ApproveExec ae = new ApproveExec();
			ae.setApproveId(apr.getId());
			ae.setApproveName(apprName);
			ae.setStartTime(new Date());
			ae.setStatus(1);
			ae.setContentURL(apr.getApproveContent());
			ae.setContentID(employId);
			ae.setIsoverandsave(1);
			approveBiz.saveApproveExecAndFlowExec(ae);
			employBiz.updateToRuZhiApprove(employId);
			ActionContext.getContext().put("employMsg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ruzhilistAction";
	}
	
	/**
	 * 在审批流程中显示入职信息
	 * @return
	 * @throws Exception
	 */
	public String showDetail() throws Exception{
		List e = employBiz.findRuZhiById(id);
		ActionContext.getContext().put("curr_ruzhi", e);
		return "showDetail";
	}
	
	/**
	 * 审批结束后的操作
	 * @return
	 * @throws Exception
	 */
	public String flowover() throws Exception {
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("employMsg", "请选择一条审批结束的信息");
			return "ruzhilistAction";
		}
		boolean b = employBiz.flowOverAndSaveData(apId);
		if (b) {
			ActionContext.getContext().put("employMsg", "数据已存档");
			return "ruzhilistAction";
		} else {
			ActionContext.getContext().put("employMsg", "操作失败，请重试");
			return "ruzhilistAction";
		}
	}
}
