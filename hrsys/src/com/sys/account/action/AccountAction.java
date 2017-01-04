package com.sys.account.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.account.biz.IAccountBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.login.User;

public class AccountAction extends ActionSupport {

	private String orgId;

	private String empId;

	private int pageIndex;

	private User user;

	private IAccountBIZ accountBiz;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IAccountBIZ getAccountBiz() {
		return accountBiz;
	}

	public void setAccountBiz(IAccountBIZ accountBiz) {
		this.accountBiz = accountBiz;
	}

	public String main() throws Exception {
		return "tomain";
	}

	/**
	 * 账户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = accountBiz.findEmpAccountByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("account_list_page", page);
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
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u != null) {
			ActionContext.getContext().put("accountMsg", "该员工已有一个账号，不能重复添加账号");
			return "listAction";
		}
		return "toAdd";
	}

	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u != null) {
			ActionContext.getContext().put("accountMsg", "该员工已有一个账号，不能重复添加账号");
			return "listAction";
		}
		User ur = accountBiz.findAccountByName(user.getUsername());
		if (ur != null) {
			ActionContext.getContext().put("accountMsg", "账户名出现重复，请重新输入账户名");
			return "toAdd";
		}
		user.setEmployeeId(empId);
		accountBiz.saveAccount(user);
		ActionContext.getContext().put("accountMsg", "保存成功");
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		user = accountBiz.findAccountByEmpId(empId);
		if (user == null) {
			ActionContext.getContext().put("accountMsg", "该员工还没有账号，无法修改");
			return "listAction";
		}
		return "toUpdate";
	}

	/**
	 * 更新账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		accountBiz.updateAccount(user);
		ActionContext.getContext().put("accountMsg", "修改成功");
		return "listAction";
	}

	/**
	 * 删除账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "该员工还没有账号，无法删除账号");
			return "listAction";
		}
		accountBiz.deleteAccount(u);
		ActionContext.getContext().put("accountMsg", "删除成功");
		return "listAction";
	}

	/**
	 * 注销账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disabled() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "该员工还没有账号，无法注销");
			return "listAction";
		}
		u.setEnable(0);
		accountBiz.updateAccount(u);
		ActionContext.getContext().put("accountMsg", "注销成功");
		return "listAction";
	}

	/**
	 * 启用账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String enabled() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "请勾选一个员工");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "该员工还没有账号，无法启用");
			return "listAction";
		}
		u.setEnable(1);
		accountBiz.updateAccount(u);
		ActionContext.getContext().put("accountMsg", "启用成功");
		return "listAction";
	}
}
