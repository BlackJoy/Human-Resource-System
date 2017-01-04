package com.sys.hr.yonggong.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.employee.biz.IEmployeeBIZ;
import com.sys.hr.yonggong.TblBorrowin;
import com.sys.hr.yonggong.TblBorrowout;
import com.sys.hr.yonggong.TblNeiborrow;

public class YonggongAction {
	private IBaseBIZ commonBiz;
	private IEmployeeBIZ empBiz;
	
	private int pageIndex;
	private String orgId;
	private String employeeId;
	private String borrowId;
	private TblNeiborrow neiborrow;
	private TblBorrowin  borrowin;
	private TblBorrowout borrowout;
	private Employee emp;


	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public TblBorrowin getBorrowin() {
		return borrowin;
	}

	public void setBorrowin(TblBorrowin borrowin) {
		this.borrowin = borrowin;
	}

	public TblBorrowout getBorrowout() {
		return borrowout;
	}

	public void setBorrowout(TblBorrowout borrowout) {
		this.borrowout = borrowout;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public IEmployeeBIZ getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(IEmployeeBIZ empBiz) {
		this.empBiz = empBiz;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public TblNeiborrow getNeiborrow() {
		return neiborrow;
	}

	public void setNeiborrow(TblNeiborrow neiborrow) {
		this.neiborrow = neiborrow;
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

	/**
	 * 跳转调动申请主页
	 * @return
	 * @throws Exception
	 */
	public String shenQing()
	{
		
		return "shenqingmain";
	}

	public String toNeiBorrow() throws Exception
	{
		emp=empBiz.findEmployeeById(employeeId);

		return "toneiborrow";
	}
	public String NeiBorrow() throws Exception
	{
		try {
			emp=empBiz.findEmployeeById(employeeId);
			emp.setOrgid(neiborrow.getToorgid());
			commonBiz.update(emp);
			Calendar cal=Calendar.getInstance();//获得当前时间
			Date time=cal.getTime();
			neiborrow.setTime(time);
			commonBiz.save(neiborrow);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "listAction";
	}
	public String toBorrowOut() throws Exception
	{
		emp=empBiz.findEmployeeById(employeeId);
		return "toborrowout";
	}
	public String BorrowOut() throws Exception
	{
		try {
			emp=empBiz.findEmployeeById(employeeId);
			emp.setEmployeeStatus(6);
			commonBiz.update(emp);
			Calendar cal=Calendar.getInstance();//获得当前时间
			Date time=cal.getTime();
			borrowout.setTime(time);
			commonBiz.save(borrowout);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "listAction";
	}
	public String toBorrowIn() throws Exception
	{		
		return "toborrowin";
	}
	public String BorrowIn()
	{
		try {			
			Calendar cal=Calendar.getInstance();//获得当前时间
			Date time=cal.getTime();
			borrowin.setTime(time);
			commonBiz.save(borrowin);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "listAction";
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
	public String chaXun() throws Exception
	{
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		//-------内部借调查询----------
		String sql_neiborrow="select b.*,tbl_org.ORGSHORTNAME " +
				"fromorgname,systime from (select a.*,tbl_org.ORGSHORTNAME " +
				"toorgname from (select * from TBL_NEIBORROW)a,tbl_org where " +
				"a.toorgid=tbl_org.ID)b,tbl_org,(select sysdate systime from dual) " +
				"where b.fromorgid=tbl_org.ID";	
				//String sql="select tbl_employee.*,tbl_org.ORGSHORTNAME from tbl_employee,tbl_org where orgid='"+this.orgId+"' and tbl_employee.orgid=tbl_org.ID";		

				Page page_neiborrow=commonBiz.findPageBySql_MapList(sql_neiborrow, pageIndex, IConstant.PAGINATION_PAGESIZE);			
				ActionContext.getContext().put("neiborrow_list_page", page_neiborrow);
				//-------内部借调查询----------
				//-------外部借出查询----------
				String sql_borrowout="select TBL_BORROWOUT.*,TBL_ORG.ORGSHORTNAME FROMORGNAME,systime from TBL_BORROWOUT,tbl_org,(select sysdate systime from dual) where TBL_BORROWOUT.fromorgid=tbl_org.ID";	
				Page page_borrowout=commonBiz.findPageBySql_MapList(sql_borrowout, pageIndex, IConstant.PAGINATION_PAGESIZE);
				ActionContext.getContext().put("borrowout_list_page", page_borrowout);
				
				//-------外部借出查询----------
				//-------外部借入查询----------
				
				String sql_borrowin="select TBL_BORROWIN.*,TBL_ORG.ORGSHORTNAME TOORGNAME,systime from TBL_BORROWIN,tbl_org,(select sysdate systime from dual) where TBL_BORROWIN.TOorgid=tbl_org.ID";	
				Page page_borrowin=commonBiz.findPageBySql_MapList(sql_borrowin, pageIndex, IConstant.PAGINATION_PAGESIZE);
				ActionContext.getContext().put("borrowin_list_page", page_borrowin);
				
				//-------外部借入查询----------		
		return "chaxun";
	}
	public String neiBorrowDel() throws Exception
	{		
		//------更新相关借调表表------
		neiborrow=(TblNeiborrow) commonBiz.findNeiBorrowById(borrowId);
		//neiborrow=(TblNeiborrow) commonBiz.findBorrowById(neiborrow.getClass(), neiborrowId);
		neiborrow.setIsfanhui("已返回");		
		commonBiz.update(neiborrow);
		//------更新相关借调表表------
		//------更新员工表------
		emp=empBiz.findEmployeeById(neiborrow.getEmpid());
		emp.setOrgid(neiborrow.getFromorgid());
		commonBiz.update(emp);
		//------更新员工表------
		return "borrowlist";
	}
	public String borrowInDel() throws Exception
	{		
		//------更新相关借调表表------
		borrowin=(TblBorrowin) commonBiz.findBorrowInById(borrowId);
		borrowin.setIsfanhui("已返回");
		commonBiz.update(borrowin);
		//------更新相关借调表表------
		return "borrowlist";
	}
	public String borrowOutDel() throws Exception
	{
	
		//------更新相关借调表表------
		borrowout=(TblBorrowout) commonBiz.findBorrowOutById(borrowId);
		borrowout.setIsfanhui("已返回");
		commonBiz.update(borrowout);
		//------更新相关借调表表------
		//------更新员工表------
		emp=empBiz.findEmployeeById(borrowout.getEmpid());
		emp.setEmployeeStatus(5);
		commonBiz.update(emp);
		//------更新员工表------
		return "borrowlist";
	}
	
}
