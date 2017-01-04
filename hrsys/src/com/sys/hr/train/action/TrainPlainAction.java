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
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.biz.IOrgBIZ;
import com.sys.hr.org.biz.impl.OrgBIZImpl;
import com.sys.hr.train.TrainPlain;
import com.sys.hr.train.TrainScore;
import com.sys.role.RoleEmployee;
import com.sys.role.biz.IRoleBIZ;

public class TrainPlainAction extends ActionSupport {
	private int pageSize = 10;
	private String orgId;
	private int pageIndex;
	private String id;

	private String trainId;
	private IBaseBIZ commonBiz;
	private TrainPlain trainPlain;
	
	private IOrgBIZ orgBiz;
	private IRoleBIZ roleBiz;
	private String emps;
	public String approveManage(){
		return "approveManage";
	}
	public String trainPlainList() throws Exception{
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		String sql = "select * from tbl_emp_train_plain ";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("trainPlain_list_page", page);
		return "plainList";
	}
	
 public String doDeleteTrainPlain() throws Exception{
	    TrainPlain trainPlain = (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	    if(trainPlain.getTrainObject() != null && !trainPlain.getTrainObject().equals("")){
	    	  String[] empIds = trainPlain.getTrainObject().split("[,]");
	    	  for(String empId:empIds ){
	    		 //删除该trainPlain下的所有TrainScore中的记录
	    		  commonBiz.deleteSQL("delete from Tbl_Train_Score where trainId = '"+trainId+"'" +
	    		  		"and employeeId ='"+empId+"'");
	    	  }
	    }
	  
	    commonBiz.delete(trainPlain);
		ActionContext.getContext().put("trainPlainMsg", "删除成功！");
		return "trainPlainList";
 }
 
 /**
	 * 跳转分配人员页面
	 * @return
	 * @throws Exception
	 */
	public String goAssignEmp() throws Exception {
		if (trainId == null || trainId.equals("")) {
			ActionContext.getContext().put("trainGet", "请选择一个培训计划录入");
			return "trainPlainList";
		}
		List orgs = orgBiz.findOrgsIncludFullName();
		List emps = commonBiz.getEmployeeByTrainId(trainId);
		ActionContext.getContext().put("orgList", orgs);
		ActionContext.getContext().put("empList", emps);
		return "toassignemp";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String assignemp() throws Exception {
		if (trainId == null || trainId.equals("")) {
			ActionContext.getContext().put("trainGet", "请选择一个培训计划录入");
			return "approveManage";
		}
		if (emps != null && !emps.equals("")) {
			//trainPlain = new TrainPlain();
			
			TrainPlain trainPlain = (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
			
			trainPlain.setTrainObject(emps);
			commonBiz.update(trainPlain);  
			List<TrainScore> sclist = new ArrayList<TrainScore>();
			String[] empIds = emps.split(",");
			for (int i = 0; i < empIds.length; i++) {
				TrainScore sc = new TrainScore();
				Employee emp = (Employee) commonBiz.getEntityById(empIds[i], Employee.class);
				
				sc.setEmployeeid(empIds[i]);
				sc.setEmployeecode(emp.getEmployeeCode());
				sc.setEmployeename(emp.getEmployeeName());
				sc.setStatus("0");
				sc.setTrainid(trainId);
				sc.setTrainsubject(trainPlain.getTrainSubject());
				sc.setTraincode(trainPlain.getTrainCode());				
				sclist.add(sc);
				
			}
			commonBiz.saveOrUpdateScoreList(trainId, sclist);
		} else {
			commonBiz.deleteScoresByTrainId(trainId);
		}
		
		return "trainPlainList";
	}
  
    public String toDetailTrainPlain(){
    	trainPlain = (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
    	return "toDetail";
    }
  /**
   * 添加人员
   * @return
   */
   public String doEmpManage(){
	   
	   
	   
  	return "plainList";
  	 
   }
	
/**
 * 开始项目
 * @return
 * @throws Exception 
 */
	public String startPlain() throws Exception{

		ActionContext.getContext().put("trainPlainMsg", "成功！");
    	trainPlain = (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
    	trainPlain.setTrainStatus(Status.ENFORCING);
    	commonBiz.update(trainPlain);

		ActionContext.getContext().put("trainPlainMsg", "项目开始！！");
		return "plainList";
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
	public void setPageIndex(String pageIndex) {
		this.pageIndex = Integer.valueOf(pageIndex);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}






	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}






	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}






	public int getPageSize() {
		return pageSize;
	}






	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public TrainPlain getTrainPlain() {
		return trainPlain;
	}
	public void setTrainPlain(TrainPlain trainPlain) {
		this.trainPlain = trainPlain;
	}
	public IOrgBIZ getOrgBiz() {
		return orgBiz;
	}
	public void setOrgBiz(IOrgBIZ orgBiz) {
		this.orgBiz = orgBiz;
	}
	public IRoleBIZ getRoleBiz() {
		return roleBiz;
	}
	public void setRoleBiz(IRoleBIZ roleBiz) {
		this.roleBiz = roleBiz;
	}
	public String getEmps() {
		return emps;
	}
	public void setEmps(String emps) {
		this.emps = emps;
	}

	
}
