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
	 * �˻��б�
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
	 * ��ת�˻�����ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u != null) {
			ActionContext.getContext().put("accountMsg", "��Ա������һ���˺ţ������ظ�����˺�");
			return "listAction";
		}
		return "toAdd";
	}

	/**
	 * �����˻�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u != null) {
			ActionContext.getContext().put("accountMsg", "��Ա������һ���˺ţ������ظ�����˺�");
			return "listAction";
		}
		User ur = accountBiz.findAccountByName(user.getUsername());
		if (ur != null) {
			ActionContext.getContext().put("accountMsg", "�˻��������ظ��������������˻���");
			return "toAdd";
		}
		user.setEmployeeId(empId);
		accountBiz.saveAccount(user);
		ActionContext.getContext().put("accountMsg", "����ɹ�");
		return "listAction";
	}

	/**
	 * ��ת�޸�ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		user = accountBiz.findAccountByEmpId(empId);
		if (user == null) {
			ActionContext.getContext().put("accountMsg", "��Ա����û���˺ţ��޷��޸�");
			return "listAction";
		}
		return "toUpdate";
	}

	/**
	 * �����˻�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		accountBiz.updateAccount(user);
		ActionContext.getContext().put("accountMsg", "�޸ĳɹ�");
		return "listAction";
	}

	/**
	 * ɾ���˺�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "��Ա����û���˺ţ��޷�ɾ���˺�");
			return "listAction";
		}
		accountBiz.deleteAccount(u);
		ActionContext.getContext().put("accountMsg", "ɾ���ɹ�");
		return "listAction";
	}

	/**
	 * ע���˺�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String disabled() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "��Ա����û���˺ţ��޷�ע��");
			return "listAction";
		}
		u.setEnable(0);
		accountBiz.updateAccount(u);
		ActionContext.getContext().put("accountMsg", "ע���ɹ�");
		return "listAction";
	}

	/**
	 * �����˺�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String enabled() throws Exception {
		if (empId == null || empId.equals("")) {
			ActionContext.getContext().put("accountMsg", "�빴ѡһ��Ա��");
			return "listAction";
		}
		User u = accountBiz.findAccountByEmpId(empId);
		if (u == null) {
			ActionContext.getContext().put("accountMsg", "��Ա����û���˺ţ��޷�����");
			return "listAction";
		}
		u.setEnable(1);
		accountBiz.updateAccount(u);
		ActionContext.getContext().put("accountMsg", "���óɹ�");
		return "listAction";
	}
}
