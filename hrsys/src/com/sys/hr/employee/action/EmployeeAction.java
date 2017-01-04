package com.sys.hr.employee.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sys.approve.Approve;
import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlow;
import com.sys.approve.biz.IApproveBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employ.Employ;
import com.sys.hr.employ.biz.IEmployBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.employee.TblDiaodong;
import com.sys.hr.employee.TblInfoFamily1;
import com.sys.hr.employee.TblInfoHealthy;
import com.sys.hr.employee.TblInfoJiangcheng;
import com.sys.hr.employee.biz.IEmployeeBIZ;
import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;

public class EmployeeAction {
	private IBaseBIZ commonBiz;
    private IEmployeeBIZ empBiz;
	
	private String orgId;
	private int pageIndex;
	private Date qingjiaTime;
	private String apId;
	private String apprName;
	private String id;
	private Employee emp;
	private TblDiaodong diaodong;  //调动信息
	private TblInfoFamily1 member;   //添加的成员信息
	private TblInfoHealthy healthy;//添加的健康信息
	private TblInfoJiangcheng jiangcheng;//添加的奖惩信息
	private String employeeId;//选中的员工
	
	

	public IEmployeeBIZ getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(IEmployeeBIZ empBiz) {
		this.empBiz = empBiz;
	}

	public TblDiaodong getDiaodong() {
		return diaodong;
	}

	public void setDiaodong(TblDiaodong diaodong) {
		this.diaodong = diaodong;
	}

	public TblInfoFamily1 getMember() {
		return member;
	}

	public void setMember(TblInfoFamily1 member) {
		this.member = member;
	}

	public TblInfoHealthy getHealthy() {
		return healthy;
	}

	public void setHealthy(TblInfoHealthy healthy) {
		this.healthy = healthy;
	}

	public TblInfoJiangcheng getJiangcheng() {
		return jiangcheng;
	}

	public void setJiangcheng(TblInfoJiangcheng jiangcheng) {
		this.jiangcheng = jiangcheng;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public Date getQingjiaTime() {
		return qingjiaTime;
	}

	public void setQingjiaTime(Date qingjiaTime) {
		this.qingjiaTime = qingjiaTime;
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

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	// -----------------------------------------

	/**
	 * 跳转请假信息管理页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String qingjia() throws Exception {
		return "qingjia";
	}

	/**
	 * 请假信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String qingjialist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		// Page page = employBiz.findqingjiaByOrgId(orgId, pageIndex,
		// IConstant.PAGINATION_PAGESIZE);
		// String sql = "select * from tbl_employee where orgid='" + this.orgId
		// + "'";
		// Page page = commonBiz.findPageBySql_MapList(sql, pageIndex,
		// IConstant.PAGINATION_PAGESIZE);

		Page page = commonBiz
				.findObjectByOrgId_page(orgId, pageIndex,
						IConstant.PAGINATION_PAGESIZE, "TBL_QINGJIA");
		ActionContext.getContext().put("qingjia_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "qingjialist";
	}

	// -----------------------------------------
	
	
	
	public String doFlow() throws Exception {
		try {
			if (employeeId == null || employeeId.equals("")) {
				ActionContext.getContext().put("employMsg", "请选择一条入职信息");
				return "ruzhilistAction";
			}
//			List e = employBiz.findRuZhiById(employId);
//			List ap = approveBiz.getAllApprove();
			Employee e=(Employee) commonBiz.getEntityById(employeeId, Employee.class);
			List ap=commonBiz.findALl2EntityList("TBL_APPROVE", Approve.class);
//			System.out.println(e);
//			System.out.println(ap);
			if (e == null) {
				ActionContext.getContext().put("employeeMsg", "待入职信息不存在，请确认");
				return "qingjialistAction";
			}
//			ServletActionContext.getRequest().setAttribute("curr_qingjia", e);
//			ServletActionContext.getRequest().setAttribute("aplistforqingjia", ap);
//			ServletActionContext.getRequest().setAttribute("employeeId", employeeId);
			ActionContext.getContext().put("curr_qingjia", e);
			ActionContext.getContext().put("aplistforqingjia", ap);
			ActionContext.getContext().put("employeeId", employeeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toFlow";
	}
	// -------员工信息----------------------------------
	/**
	 * 跳转员工主页
	 * @return
	 * @throws Exception
	 */
	public String main()
	{
		return "tomain";
	}
	/**
	 * 跳转员工列表页
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		String sql="select tbl_employee.*,tbl_org.ORGSHORTNAME from tbl_employee,tbl_org where orgid='"+this.orgId+"' and tbl_employee.orgid=tbl_org.ID";		
		Page page=commonBiz.findPageBySql_MapList(sql, pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("employee_list_page", page);
		//ActionContext.getContext().put("orgId", orgId);
		return "list";
	}
	/**
	 * 跳转账户增加页面
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		
		return "toAdd";
	}
	
	/**
	 * 增加账户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
	
		emp.setOrgid(orgId);
		commonBiz.save(emp);
		return "listAction";
	}
	
	/**
	 * 跳转修改页面
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (employeeId == null || employeeId.equals("")) {
			ActionContext.getContext().put("orgMsg", "请勾选一个员工");
			return "listAction";
		}
		Employee emp = (Employee) commonBiz.getEntityById(employeeId, Employee.class);
		if (emp == null) {
			ActionContext.getContext().put("orgMsg", "你选择的员工不存在，请重新选择");
			return "listAction";
		}
		Org org =(Org) commonBiz.getEntityById(emp.getOrgid(), Org.class);
		ActionContext.getContext().put("p_emp", emp);
		ActionContext.getContext().put("p_orgName", org.getOrgShortName());
		return "toUpdate";
	}
	
	/**
	 * 更新账户
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//commonBiz.save(emp);
		commonBiz.update(emp);
		return "listAction";
	}
	
	/**
	 * 删除账号
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		Employee emp=(Employee)commonBiz.getEntityById(employeeId,Employee.class);
		commonBiz.delete(emp);
		return "listAction";
	}
	public String toXiangQing() throws Exception
	{
		//-------学习情况(培训)----------
		String sql_peixun="select * from TBL_TRAIN_SCORE where employeeid='"+employeeId+"'";	
		Page page_peixun=commonBiz.findPageBySql_MapList(sql_peixun, pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("train_score_list_page", page_peixun);
		//-------学习情况----------
		//-------奖惩情况----------
		String sql_jiangcheng="select * from TBL_INFO_JIANGCHENG where empid='"+employeeId+"'";	
		Page page_jiangcheng=commonBiz.findPageBySql_MapList(sql_jiangcheng, pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("jiangcheng_list_page", page_jiangcheng);
		
		//-------奖惩情况----------
		//-------健康情况----------
		
		String sql_healthy="select * from TBL_INFO_HEALTHY where empid='"+employeeId+"'";	
		Page page_healthy=commonBiz.findPageBySql_MapList(sql_healthy, pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("healthy_list_page", page_healthy);
	
		//-------健康情况----------
		//-------家庭情况----------
		String sql_family="select * from TBL_INFO_FAMILY1 where empid='"+employeeId+"'";		
		Page page_family=commonBiz.findPageBySql_MapList(sql_family, pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("family_member_list_page", page_family);		
		//-------家庭情况----------
		return "toxiangqing";
	}
	//--------员工详情添加-----------
	public String toAddFamily()
	{
		return "tofamily";
	}
	public String AddFamily() throws Exception
	{
		commonBiz.save(member);
		return "addxiangqing";
	}
	public String toAddHealthy()
	{
		return "tohealthy";
	}
	public String AddHealthy() throws Exception
	{
		commonBiz.save(healthy);
		return "addxiangqing";
	}
	public String toAddJiangcheng() throws Exception
	{
		emp=empBiz.findEmployeeById(employeeId);
		return "tojiang";
	}
	public String AddJiangcheng() throws Exception
	{
		commonBiz.save(jiangcheng);
		return "addxiangqing";
	}
	
	//--------详情添加-----------
	public String toDiaoDong()
	{
		emp=(Employee) commonBiz.getEntityById(employeeId, Employee.class);
		return "todiaodong";
	}
	public String diaoDong() throws Exception
	{
		try {
			emp=(Employee) commonBiz.getEntityById(employeeId, Employee.class);
			emp.setOrgid(diaodong.getToorgid());
			commonBiz.update(emp);
			Calendar cal=Calendar.getInstance();//获得当前时间
			Date time=cal.getTime();
			diaodong.setTime(time);
			commonBiz.save(diaodong);

		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return "listAction";
	}
	// -------员工信息----------------------------------
	
}
