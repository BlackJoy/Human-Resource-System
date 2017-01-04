package com.sys.hr.employplan.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.biz.IApproveBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employplan.EmployPlan;
import com.sys.hr.employplan.biz.EmployPlanBIZ;
import com.sys.hr.org.Org;
import com.sys.hr.org.Post;
import com.sys.util.ExcelUtil;



public class EmployPlanAction {
	private IBaseBIZ commonBiz;
	private int pageIndex;
	private String orgId;
	private EmployPlan eplyPlan;
	private String[] planId;
	private IApproveBIZ approveBiz;
	private String apId;
	private String apprName;
	private EmployPlanBIZ planBiz;
	private String id;
	private File excel;
	//跳转到招聘计划书主页
	public String employPlan(){
		return "employPlan";
	}
	//招聘计划书列表
	public String employPlanList() throws Exception{
		if(orgId==null||orgId.equals("")){
		orgId=(String) ActionContext.getContext().getSession().get("orgId");
		}
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		
		//---------------------------------------------
		//判断org是否是父org
		List<Org> orgList=planBiz.findOrg(orgId);
		
		if(orgList!=null && orgList.size()!=0){

			Page page=planBiz.findPlanList(orgList,orgId, pageIndex, IConstant.PAGINATION_PAGESIZE);
			ActionContext.getContext().put("employPlan_list_page", page);
		}else{
		//-----------------------------------------
			Page page=planBiz.findEmployPlanByPlanId(orgId, pageIndex,  IConstant.PAGINATION_PAGESIZE);
			ActionContext.getContext().put("employPlan_list_page", page);
		}
		ActionContext.getContext().put("orgId", orgId);
		return "employPlanList";
	}
	//跳转招聘计划书添加页面
	public String employPlanAdd(){
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("planMsg", "请选择一个组织");
			return "planlistAction";
		}
		String sql = "orgid='" + this.orgId + "'";
		List<Post> post=commonBiz.findByCondition("tbl_post", sql, Post.class);
		Org org = (Org)commonBiz.getEntityById(orgId, Org.class);
		ActionContext.getContext().put("curr_org", org);
		ActionContext.getContext().put("curr_post", post);
		return "toAddPlan";
	}
	//招聘计划书添加
	public String doAddPlan() throws Exception{
		commonBiz.save(eplyPlan);
		ActionContext.getContext().put("planMsg", "保存成功");
		return "toAddPlanAction";
	}
	//招聘计划书信息页
	public String toDetailPlan(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		EmployPlan eplan=new EmployPlan();
		eplan=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		ActionContext.getContext().put("planDetial", eplan);
		return "infoPlan";
	}
	//跳往招聘计划书修改页面
	public String toUpdate(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		String sql = "orgid='" + this.orgId + "'";
		List<Post> post=commonBiz.findByCondition("tbl_post", sql, Post.class);
		EmployPlan eplan=new EmployPlan();
		eplan=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		ActionContext.getContext().put("planDetail", eplan);
		ActionContext.getContext().put("curr_post", post);
		return "updatePlan";
	
	}
	public String updatePlan() throws Exception{
		
		commonBiz.update(eplyPlan);
	
		return "planlistAction";
	}
	/**
	 * 删除招聘计划书
	 * @return
	 * @throws Exception 
	 */
	public String doDeletePlan() throws Exception{
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		EmployPlan eplan=new EmployPlan();
		eplan=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		commonBiz.delete(eplan);
		
		return "planlistAction";
	}
	
	/**
	 * 导出到Excel
	 * @return
	 * @throws Exception
	 */
	public String toExcel() throws Exception{
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		List pList=planBiz.findEmployPlanByList(planId);
		List<String> PlanListName=planBiz.PlanListName();
		//调用打印Excel
		ExcelUtil util=new ExcelUtil();

		//*********************后台数据集end*************************
				HttpServletResponse response=ServletActionContext.getResponse();
				//获取输出流
				OutputStream out = response.getOutputStream();
				//重置输出流
				response.reset();
				//设置默认文件名
				response.setHeader( "Content-disposition" ,  "attachment;filename=test" );
				//设置导出Excel报表的导出形式
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				ExcelUtil.exportExcel(PlanListName, pList,out);
				//设置输出形式
				System.setOut(new PrintStream(out));
				//刷新输出流
				out.flush();
				//关闭输出流
				if(out!=null){
					out.close();
				}
				ActionContext.getContext().put("planMsg","**文件打印成功**");
				return "planlistAction";
	}
	//-----------------------------------------
	/**
	 * 跳转到选择文件夹的页面
	 * @return
	 */
	public String toExcelFile(){
		ActionContext.getContext().put("orgId", orgId);
		return "toPage";
	}
	/**
	 * 通过Excel批量添加招聘计划书
	 * @return
	 * @throws Exception 
	 */
	public String fromExcel() throws Exception{
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("planMsg", "请选择一个组织");
			return "planlistAction";
		}
		ExcelUtil util=new ExcelUtil();
		List pList=util.readExcelByFile(excel);
		Boolean b=planBiz.addAll(pList,orgId);
		if(b){
			ActionContext.getContext().put("planMsg","**文件导入成功**");
		}else{
			ActionContext.getContext().put("planMsg","**文件导入失败**");
		}
		ActionContext.getContext().getSession().put("orgId", orgId);
		return "planlistAction";
	}
	
	//提交计划书进行审批
	public String toApprove(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		EmployPlan ep=new EmployPlan();
		ep=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		ep.setStatus(1);
		try {
			commonBiz.update(ep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "planlistAction";
		
	}
	/**
	 * 将招聘计划书修改为发布状态
	 * @return
	 */
	public String doPublish(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","至少选择一条信息");
			return "employPlanList";
		}
		EmployPlan ep=new EmployPlan();
		ep=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		ep.setStatus(0);
		try {
			commonBiz.update(ep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "planMangList";
	}
	/**
	 * 跳转到招聘计划书管理主页
	 * @return
	 */
	public String planMang(){
		return "planMang";
	}
	/**
	 * 跳转到招聘计划书审批管理列表
	 * @return
	 * @throws Exception 
	 */
	public String planMangList() throws Exception{
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		List<Org> orgList=planBiz.findOrg(orgId);
		
		if(orgList!=null && orgList.size()!=0){

			Page page=planBiz.findPlanMangList(orgList,orgId, pageIndex, IConstant.PAGINATION_PAGESIZE);
			ActionContext.getContext().put("planMang_list_page", page);
		}else{
			Page page=planBiz.findEmployPlanMangByPlanId(orgId, pageIndex, IConstant.PAGINATION_PAGESIZE);
			ActionContext.getContext().put("planMang_list_page", page);
		}
		return "planMangList";
	}
	/**
	 * 发起审批流程
	 * @return
	 */
	public String doFlow(){
		try {
			if (planId == null || planId.equals("")) {
				ActionContext.getContext().put("planMangMsg", "请选择一条招聘计划书信息");
				return "planManglistAction";
			}
			EmployPlan e =(EmployPlan) commonBiz.getEntityById(planId[0], EmployPlan.class);
			List ap = approveBiz.getAllApprove();

			ActionContext.getContext().put("curr_planApprove", e);
			ActionContext.getContext().put("aplist", ap);
			ActionContext.getContext().put("planId", planId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toFlow";
	}
	/**
	 * 保存审批流程
	 * @return
	 * @throws Exception
	 */
	public String saveAppFlow() throws Exception {
		try {
			if (apId == null || apId.equals("")) {
				ActionContext.getContext().put("planMangMsg", "请选择一个审批项目");
				return "toFlowAction";
			}
			Approve apr = approveBiz.getApprove(apId);
			if (apr == null) {
				ActionContext.getContext().put("planMangMsg", "审批项目不存在，请重新选择");
				return "toFlowAction";
			}
			ApproveExec ae = new ApproveExec();
			ae.setApproveId(apr.getId());
			ae.setApproveName(apprName);
			ae.setStartTime(new Date());
			ae.setStatus(1);
			ae.setContentURL(apr.getApproveContent());
			ae.setContentID(planId[0]);
			ae.setIsoverandsave(1);
			approveBiz.saveApproveExecAndFlowExec(ae);
			EmployPlan ep=new EmployPlan();
			
			ep=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
			ep.setStatus(2);
			commonBiz.update(ep);
			String orgId=ep.getOrg();
			
			ActionContext.getContext().put("planMangMsg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planManglistAction";
	}
	/**
	 * 加载审批流程
	 * @return
	 * @throws Exception 
	 */
	public String loadAppFlow() throws Exception{
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("planMangMsg", "请选择一个审批项目");
			return "toFlowAction";
		}
		Approve apr = approveBiz.getApprove(apId);
		if (apr == null) {
			ActionContext.getContext().put("planMangMsg", "审批项目不存在，请重新选择");
			return "toFlowAction";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(apId);
		if (flowList == null) {
			ActionContext.getContext().put("planMangMsg", "审批项目不存在，请重新选择");
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
	public String showDetail(){
		EmployPlan eplan=(EmployPlan)commonBiz.getEntityById(id, EmployPlan.class);
		ActionContext.getContext().put("curr_plan", eplan);
		return "showDetail";
	}
	
	
	public String[] getPlanId() {
		return planId;
	}
	public void setPlanId(String[] planId) {
		this.planId = planId;
	}
	public IApproveBIZ getApproveBiz() {
		return approveBiz;
	}
	public void setApproveBiz(IApproveBIZ approveBiz) {
		this.approveBiz = approveBiz;
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
	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}
	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public EmployPlan getEplyPlan() {
		return eplyPlan;
	}
	public void setEplyPlan(EmployPlan eplyPlan) {
		this.eplyPlan = eplyPlan;
	}
	public EmployPlanBIZ getPlanBiz() {
		return planBiz;
	}
	public void setPlanBiz(EmployPlanBIZ planBiz) {
		this.planBiz = planBiz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
	
}
