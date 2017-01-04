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
	//��ת����Ƹ�ƻ�����ҳ
	public String employPlan(){
		return "employPlan";
	}
	//��Ƹ�ƻ����б�
	public String employPlanList() throws Exception{
		if(orgId==null||orgId.equals("")){
		orgId=(String) ActionContext.getContext().getSession().get("orgId");
		}
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		
		//---------------------------------------------
		//�ж�org�Ƿ��Ǹ�org
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
	//��ת��Ƹ�ƻ������ҳ��
	public String employPlanAdd(){
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("planMsg", "��ѡ��һ����֯");
			return "planlistAction";
		}
		String sql = "orgid='" + this.orgId + "'";
		List<Post> post=commonBiz.findByCondition("tbl_post", sql, Post.class);
		Org org = (Org)commonBiz.getEntityById(orgId, Org.class);
		ActionContext.getContext().put("curr_org", org);
		ActionContext.getContext().put("curr_post", post);
		return "toAddPlan";
	}
	//��Ƹ�ƻ������
	public String doAddPlan() throws Exception{
		commonBiz.save(eplyPlan);
		ActionContext.getContext().put("planMsg", "����ɹ�");
		return "toAddPlanAction";
	}
	//��Ƹ�ƻ�����Ϣҳ
	public String toDetailPlan(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
			return "employPlanList";
		}
		EmployPlan eplan=new EmployPlan();
		eplan=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		ActionContext.getContext().put("planDetial", eplan);
		return "infoPlan";
	}
	//������Ƹ�ƻ����޸�ҳ��
	public String toUpdate(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
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
	 * ɾ����Ƹ�ƻ���
	 * @return
	 * @throws Exception 
	 */
	public String doDeletePlan() throws Exception{
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
			return "employPlanList";
		}
		EmployPlan eplan=new EmployPlan();
		eplan=(EmployPlan)commonBiz.getEntityById(planId[0], EmployPlan.class);
		commonBiz.delete(eplan);
		
		return "planlistAction";
	}
	
	/**
	 * ������Excel
	 * @return
	 * @throws Exception
	 */
	public String toExcel() throws Exception{
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
			return "employPlanList";
		}
		List pList=planBiz.findEmployPlanByList(planId);
		List<String> PlanListName=planBiz.PlanListName();
		//���ô�ӡExcel
		ExcelUtil util=new ExcelUtil();

		//*********************��̨���ݼ�end*************************
				HttpServletResponse response=ServletActionContext.getResponse();
				//��ȡ�����
				OutputStream out = response.getOutputStream();
				//���������
				response.reset();
				//����Ĭ���ļ���
				response.setHeader( "Content-disposition" ,  "attachment;filename=test" );
				//���õ���Excel����ĵ�����ʽ
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				ExcelUtil.exportExcel(PlanListName, pList,out);
				//���������ʽ
				System.setOut(new PrintStream(out));
				//ˢ�������
				out.flush();
				//�ر������
				if(out!=null){
					out.close();
				}
				ActionContext.getContext().put("planMsg","**�ļ���ӡ�ɹ�**");
				return "planlistAction";
	}
	//-----------------------------------------
	/**
	 * ��ת��ѡ���ļ��е�ҳ��
	 * @return
	 */
	public String toExcelFile(){
		ActionContext.getContext().put("orgId", orgId);
		return "toPage";
	}
	/**
	 * ͨ��Excel���������Ƹ�ƻ���
	 * @return
	 * @throws Exception 
	 */
	public String fromExcel() throws Exception{
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("planMsg", "��ѡ��һ����֯");
			return "planlistAction";
		}
		ExcelUtil util=new ExcelUtil();
		List pList=util.readExcelByFile(excel);
		Boolean b=planBiz.addAll(pList,orgId);
		if(b){
			ActionContext.getContext().put("planMsg","**�ļ�����ɹ�**");
		}else{
			ActionContext.getContext().put("planMsg","**�ļ�����ʧ��**");
		}
		ActionContext.getContext().getSession().put("orgId", orgId);
		return "planlistAction";
	}
	
	//�ύ�ƻ����������
	public String toApprove(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
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
	 * ����Ƹ�ƻ����޸�Ϊ����״̬
	 * @return
	 */
	public String doPublish(){
		if(planId==null || planId.equals("")){
			ActionContext.getContext().put("planMsg","����ѡ��һ����Ϣ");
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
	 * ��ת����Ƹ�ƻ��������ҳ
	 * @return
	 */
	public String planMang(){
		return "planMang";
	}
	/**
	 * ��ת����Ƹ�ƻ������������б�
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
	 * ������������
	 * @return
	 */
	public String doFlow(){
		try {
			if (planId == null || planId.equals("")) {
				ActionContext.getContext().put("planMangMsg", "��ѡ��һ����Ƹ�ƻ�����Ϣ");
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
	 * ������������
	 * @return
	 * @throws Exception
	 */
	public String saveAppFlow() throws Exception {
		try {
			if (apId == null || apId.equals("")) {
				ActionContext.getContext().put("planMangMsg", "��ѡ��һ��������Ŀ");
				return "toFlowAction";
			}
			Approve apr = approveBiz.getApprove(apId);
			if (apr == null) {
				ActionContext.getContext().put("planMangMsg", "������Ŀ�����ڣ�������ѡ��");
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
			
			ActionContext.getContext().put("planMangMsg", "����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "planManglistAction";
	}
	/**
	 * ������������
	 * @return
	 * @throws Exception 
	 */
	public String loadAppFlow() throws Exception{
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("planMangMsg", "��ѡ��һ��������Ŀ");
			return "toFlowAction";
		}
		Approve apr = approveBiz.getApprove(apId);
		if (apr == null) {
			ActionContext.getContext().put("planMangMsg", "������Ŀ�����ڣ�������ѡ��");
			return "toFlowAction";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(apId);
		if (flowList == null) {
			ActionContext.getContext().put("planMangMsg", "������Ŀ�����ڣ�������ѡ��");
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
