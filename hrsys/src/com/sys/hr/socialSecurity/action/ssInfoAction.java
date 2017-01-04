package com.sys.hr.socialSecurity.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;

import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;
import com.sys.hr.socialSecurity.SocSecrityInfo;
import com.sys.login.User;

public class ssInfoAction extends ActionSupport {

	private int pageSize = 10;
	private String orgId;
	private String employeeCode;
	private int pageIndex;
	private String Id;//存放社保账号表的ID

	private SocSecrityInfo ssInfo;//存放新增加社保账号信息

	private IBaseBIZ commonBiz;

	private String empId;//存放员工ID
	private Employee emp;
	private List<SocSecrityInfo> ssInfoList = new ArrayList<SocSecrityInfo>();
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public SocSecrityInfo getSsInfo() {
		return ssInfo;
	}

	public void setSsInfo(SocSecrityInfo ssInfo) {
		this.ssInfo = ssInfo;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public String main() throws Exception {
		return "tomain";
	}

	/**
	 * 岗位列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}

		String sql = "select * from SOC_SECRITY_INFO where orgid='" + this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("ssInfo_list_page", page);
		System.out.println(orgId);
		ActionContext.getContext().put("orgId", orgId);
		return "list";
	}

	/**
	 * 跳转账户增加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		// select * from TBL_EMPLOYEE where orgid='this.orgId' and employeeCode not in (select employeeCode from SOC_SECRITY_INFO where orgId='this.orgId')
		String sql = "select * from TBL_EMPLOYEE where orgid='" + this.orgId + "' and employeeCode not in (select employeeCode from SOC_SECRITY_INFO where orgId='"+this.orgId+"')";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		sql = "select * from SOC_SECRITY_INFO where orgid='" + this.orgId + "'";
		ssInfoList = commonBiz.findSQL2MapList(sql);
		
		ActionContext.getContext().put("account_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		System.out.println("*************+++++++++++"+pageIndex);
		System.out.println("*************+++++++++++"+orgId);
		return "toAdd";
	}
	
	public String toAdd01() throws Exception{
		System.out.println(empId);
		emp = (Employee) commonBiz.getEntityById(empId,Employee.class);
		ActionContext.getContext().put("emp", emp);
		
		return "toAdd01";
	}
	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		ssInfo.setOrgid(orgId);
		commonBiz.save(ssInfo);
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		ssInfo=(SocSecrityInfo) commonBiz.getEntityById(Id, SocSecrityInfo.class);
		return "toUpdate";
	}

	/**
	 * 更新账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	
		ssInfo.setOrgid(orgId);
		
		commonBiz.update(ssInfo);
		return "listAction";
	}

	/**
	 * 删除账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		SocSecrityInfo ssInfo = (SocSecrityInfo) commonBiz.getEntityById(Id, SocSecrityInfo.class);
		commonBiz.delete(ssInfo);
		return "listAction";
	}
	
	


}
