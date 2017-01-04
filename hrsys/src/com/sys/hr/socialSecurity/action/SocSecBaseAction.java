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
import com.sys.hr.socialSecurity.SocSecBase;
import com.sys.hr.socialSecurity.SocSecBase;
import com.sys.hr.socialSecurity.SocSecrityInfo;
import com.sys.hr.socialSecurity.baoXianItem;
import com.sys.login.User;

public class SocSecBaseAction extends ActionSupport {

	private int pageSize = 10;
	private String orgId;
	private String employeeCode;
	private int pageIndex;
	private String Id;
	private List<baoXianItem> bXList;
	private SocSecBase socSecBase;//存放新增加社保账号信息
	private SocSecrityInfo ssInfo;
	private IBaseBIZ commonBiz;

	
	private Employee emp;
	

	
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

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public List<baoXianItem> getbXList() {
		return bXList;
	}

	public void setbXList(List<baoXianItem> bXList) {
		this.bXList = bXList;
	}

	public SocSecBase getSocSecBase() {
		return socSecBase;
	}

	public void setSocSecBase(SocSecBase socSecBase) {
		this.socSecBase = socSecBase;
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

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
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

		String sql = "select * from SOCIAL_SECURITY_BASE where orgid='" + this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("socSecBase_list_page", page);
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
		String sql = "select * from SOC_SECRITY_INFO where orgid='" + this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);		
		ActionContext.getContext().put("ssInfo_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		System.out.println("*************+++++++++++"+pageIndex);
		System.out.println("*************+++++++++++"+orgId);
		return "toAdd";
	}
	
	public String toAdd01() throws Exception{
		ssInfo = (SocSecrityInfo) commonBiz.getEntityById(Id,SocSecrityInfo.class);
		bXList =  commonBiz.findALl2EntityList("BAOXIANITEM", baoXianItem.class);
		ActionContext.getContext().put("bXList", bXList);
		System.out.println();
		return "toAdd01";
	}
	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		baoXianItem bXItem = (baoXianItem)commonBiz.getEntityById(socSecBase.getTemplateId(), baoXianItem.class);
		socSecBase.setCurItem(bXItem.getCurItem());
		socSecBase.setOrgId(orgId);
		
		commonBiz.save(socSecBase);
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		socSecBase=(SocSecBase) commonBiz.getEntityById(Id, SocSecBase.class);
		return "toUpdate";
	}

	/**
	 * 更新账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	
		socSecBase.setOrgId(orgId);
		
		commonBiz.update(socSecBase);
		return "listAction";
	}

	/**
	 * 删除账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		SocSecBase socSecBase = (SocSecBase) commonBiz.getEntityById(Id, SocSecBase.class);
		commonBiz.delete(socSecBase);
		return "listAction";
	}
	
	


}
