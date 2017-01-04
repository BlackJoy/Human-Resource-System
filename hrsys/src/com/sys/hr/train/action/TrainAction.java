package com.sys.hr.train.action;

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
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;
import com.sys.hr.train.Train;
import com.sys.hr.train.TrainPlain;
import com.sys.hr.train.util.ParseHtml;

public class TrainAction extends ActionSupport {
	private int pageSize = 10;
	private String orgId;
	private String postId;
	private int pageIndex;
    private String orgShortName;
	private Train train;
	private Org org;

	private double trainPrice;
	private Employee emp;
	private String trainId;
	private IBaseBIZ commonBiz;
	
    private String id;
	private String apId;
	private String apprName;
	private IApproveBIZ approveBiz;
	public String applyManage(){
		
		return "applyManage";
	}
	/**
	 * 转至培训申请添加页面
	 * @return
	 */
	public String toAddTrain(){

		//emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
	
		return "doAddTrain";
	}
	/**
	 * 添加培训申请，并跳至培训申请列表页面
	 * @return
	 * @throws Exception
	 */
	public String doAddTrain() throws Exception{
		emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
		train.setApplicantName(emp.getEmployeeName());
		train.setApplicantCode(emp.getEmployeeCode());
	    train.setTrainObject("");
	    train.setTrainCode("001");
	    if(train.getOrgFullName() == null){
	    	train.setOrgFullName("");
	    }
	 //   train.setTrainDetail(ParseHtml.htmlspecialchars(train.getTrainDetail()));
		train.setTrainPrice(trainPrice);
		train.setId("1233589");
		commonBiz.save(train);		
		return "trainList";
	}
	/**
	 * 删除培训申请
	 * @return
	 * @throws Exception
	 */
	public String doDeleteTrain() throws Exception{
		System.out.println(trainId);
		train = (Train) commonBiz.getEntityById(trainId, Train.class);
		commonBiz.delete(train);
		return "trainList";
	}
	/**
	 * 转至修改培训申请页面
	 * @return
	 * @throws Exception
	 */
	public String toUpdateTrain() throws Exception{

		train = (Train) commonBiz.getEntityById(trainId, Train.class);
	    return "toUpdateTrain";
	}
	public String doUpdateTrain() throws Exception{
		train.setOrgId(orgId);
		commonBiz.update(train);
	    return "trainList";
	}
	/**
	 * 跳至培训申请内容页面
	 * @return
	 * @throws Exception
	 */
	public String toDetailTrain(){
	//	System.out.println(pageIndex);
		train = (Train) commonBiz.getEntityById(trainId, Train.class);
		return "toDetailTrain";
	}
	public String main() throws Exception {
		return "tomain";
	}
	
	public String trainList() throws Exception{
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		/*if(orgId == null || orgId.equals(""))
			orgId = "1";*/
		String sql = "select * from tbl_emp_train where orgid='" + orgId + "' order by trainstart,trainstatus";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("train_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "list";
	}

public String doFlow(){
	try {
		if (trainId == null || trainId.equals("")) {
			ActionContext.getContext().put("trainMsg", "请选择一条培训信息");
			return "trainList";
		}
//		List e = employBiz.findRuZhiById(employId);
//		List ap = approveBiz.getAllApprove();
		train=(Train) commonBiz.getEntityById(trainId, Train.class);
		List ap=commonBiz.findALl2EntityList("TBL_APPROVE", Approve.class);
//		System.out.println(e);
//		System.out.println(ap);
		if (train == null) {
			ActionContext.getContext().put("trainMsg", "培训申请信息不存在，请确认");
			return "trainList";
		}
//		ServletActionContext.getRequest().setAttribute("curr_qingjia", e);
//		ServletActionContext.getRequest().setAttribute("aplistforqingjia", ap);
//		ServletActionContext.getRequest().setAttribute("employeeId", employeeId);
		ActionContext.getContext().put("curr_train", train);
		ActionContext.getContext().put("aplist", ap);
		ActionContext.getContext().put("trainId", trainId);
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
		ActionContext.getContext().put("trainMsg", "请选择一个审批项目");
		return "toFlowAction";
	}
	Approve apr = approveBiz.getApprove(apId);
	if (apr == null) {
		ActionContext.getContext().put("trainMsg", "审批项目不存在，请重新选择");
		return "toFlowAction";
	}
	List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(apId);
	if (flowList == null) {
		ActionContext.getContext().put("trainMsg", "审批项目不存在，请重新选择");
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
			ActionContext.getContext().put("trainMsg", "请选择一个审批项目");
			return "toFlowAction";
		}
		Approve apr = approveBiz.getApprove(apId);
		if (apr == null) {
			ActionContext.getContext().put("trainMsg", "审批项目不存在，请重新选择");
			return "toFlowAction";
		}
		ApproveExec ae = new ApproveExec();
		ae.setApproveId(apr.getId());
		ae.setApproveName(apprName);
		ae.setStartTime(new Date());
		ae.setStatus(1);
		ae.setContentURL(apr.getApproveContent());
		ae.setContentID(trainId);
		ae.setIsoverandsave(1);
		approveBiz.saveApproveExecAndFlowExec(ae);
		train=(Train) commonBiz.getEntityById(trainId, Train.class);
		train.setTrainStatus(Status.APPROVING);
		commonBiz.update(train);
		ActionContext.getContext().put("trainMsg", "保存成功");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "trainList";
}
	public String showDetail(){
		train=(Train) commonBiz.getEntityById(id, Train.class);
		return "showDetail";
	}
	


	public void changeData(Train train,TrainPlain trainPlain){
		trainPlain.setApplicantCode(train.getApplicantCode());
		trainPlain.setApplicantName(train.getApplicantName());
		trainPlain.setId(train.getId());
		trainPlain.setOrgCode(train.getOrgCode());
		trainPlain.setOrgFullName(train.getOrgFullName());
		trainPlain.setOrgId(train.getOrgId());
		trainPlain.setTrainCode(train.getTrainCode());
		trainPlain.setTrainDetail(train.getTrainDetail());
		trainPlain.setTrainEnd(train.getTrainEnd());
		trainPlain.setTrainManner(train.getTrainManner());
		trainPlain.setTrainObject(train.getTrainObject());
		trainPlain.setTrainPrice(train.getTrainPrice());
		trainPlain.setTrainRemarks(train.getTrainRemarks());
		trainPlain.setTrainStart(train.getTrainStart());
		trainPlain.setTrainStatus(train.getTrainStatus());
		trainPlain.setTrainSubject(train.getTrainSubject());
		trainPlain.setTrainType(train.getTrainType());
		
	}
	
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}
	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
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
	public IApproveBIZ getApproveBiz() {
		return approveBiz;
	}
	public void setApproveBiz(IApproveBIZ approveBiz) {
		this.approveBiz = approveBiz;
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
	public double getTrainPrice() {
		return trainPrice;
	}
	public void setTrainPrice(double trainPrice) {
		this.trainPrice = trainPrice;
	}
}
