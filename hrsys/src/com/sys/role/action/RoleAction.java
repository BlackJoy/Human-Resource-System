package com.sys.role.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.authority.Authority;
import com.sys.authority.biz.IAuthorityBIZ;
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.hr.org.biz.IOrgBIZ;
import com.sys.login.ILoginConstant;
import com.sys.login.User;
import com.sys.role.Role;
import com.sys.role.RoleAuthority;
import com.sys.role.RoleEmployee;
import com.sys.role.biz.IRoleAuthorityBIZ;
import com.sys.role.biz.IRoleBIZ;
import com.sys.role.biz.IRoleEmployeeBIZ;

public class RoleAction extends ActionSupport {

	private IRoleEmployeeBIZ reBiz;
	
	private IRoleAuthorityBIZ roleAuthorityBiz;
	
	private IAuthorityBIZ authorityBiz;
	
	private IRoleBIZ roleBiz;
	
	private IOrgBIZ orgBiz;
	
	private String menuId;
	
	private int pageIndex;
	
	private Role role;
	
	private String roleId;
	
	private List authId;
	
	private String emps;

	public IRoleEmployeeBIZ getReBiz() {
		return reBiz;
	}

	public void setReBiz(IRoleEmployeeBIZ reBiz) {
		this.reBiz = reBiz;
	}

	public IRoleAuthorityBIZ getRoleAuthorityBiz() {
		return roleAuthorityBiz;
	}

	public void setRoleAuthorityBiz(IRoleAuthorityBIZ roleAuthorityBiz) {
		this.roleAuthorityBiz = roleAuthorityBiz;
	}

	public IAuthorityBIZ getAuthorityBiz() {
		return authorityBiz;
	}

	public void setAuthorityBiz(IAuthorityBIZ authorityBiz) {
		this.authorityBiz = authorityBiz;
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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List getAuthId() {
		return authId;
	}

	public void setAuthId(List authId) {
		this.authId = authId;
	}

	public String getEmps() {
		return emps;
	}

	public void setEmps(String emps) {
		this.emps = emps;
	}

	/**
	 * ��¼�ɹ�����ص�ǰ�û���Ȩ��
	 * @return
	 * @throws Exception
	 */
	public String loadCurrUserAuthority()throws Exception {
		User curr_user = (User)ActionContext.getContext().getSession().get(ILoginConstant.CURRENT_USER);
		List<RoleEmployee> res = reBiz.findRoleByEmployeeId(curr_user.getEmployeeId());
		List<Authority> authorityList = authorityBiz.getAuthoritiesByRoles(res);
		ActionContext.getContext().getSession().put(ILoginConstant.CURRENT_USER_AUTHORITY, authorityList);
		return SUCCESS;
	}
	
	/**
	 * ��ȡ��ɫ�б�
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = roleBiz.getRolePage(pageIndex, IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().getSession().put("list_page", page);
		return "list";
	}
	
	/**
	 * ����½�ɫ
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		roleBiz.saveRole(role);
		ActionContext.getContext().put("roleAdd", true);
		return "add";
	}
	
	/**
	 * ��ȡ��ɫ
	 * @return
	 * @throws Exception
	 */
	public String getById() throws Exception {
		if (roleId == null || roleId.equals("")) {
			ActionContext.getContext().put("roleGet", "��ѡ���¼");
			return "roleListAction";
		}
		role = roleBiz.getRoleById(roleId);
		if (role == null) {
			ActionContext.getContext().put("roleGet", "��ѡ��ļ�¼�����ڣ�������ѡ��");
			return "roleListAction";
		}
		return "toUpdatePage";
	}
	
	/**
	 * ���½�ɫ
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		roleBiz.updateRoel(role);
		ActionContext.getContext().put("roleUpdate", true);
		return "update";
	}
	
	/**
	 * ɾ����ɫ
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if (roleId == null || roleId.equals("")) {
			ActionContext.getContext().put("roleGet", "��ѡ���¼");
			return "roleListAction";
		}
		role = roleBiz.getRoleById(roleId);
		if (role == null) {
			ActionContext.getContext().put("roleGet", "��ѡ��ļ�¼�����ڣ�������ѡ��");
			return "roleListAction";
		}
		roleBiz.deleteRole(roleId);
		ActionContext.getContext().put("roleDelete", "ɾ���ɹ�");
		return "roleListAction";
	}
	
	/**
	 * ��ת����Ȩ��ҳ��
	 * @return
	 * @throws Exception
	 */
	public String goRoleAuthorityPage() throws Exception {
		return "roleauthority";
	}
	
	/**
	 * ����Ȩ��
	 * @return
	 * @throws Exception
	 */
	public String authorization() throws Exception {
		if (roleId != null && !roleId.equals("")) {
			List<RoleAuthority> raList = new ArrayList<RoleAuthority>();
			for (Iterator it = authId.iterator(); it.hasNext();) {
				String authorityId = (String) it.next();
				RoleAuthority ra = new RoleAuthority();
				ra.setRoleId(roleId);
				ra.setAuthorityId(authorityId);
				raList.add(ra);
			}
			roleAuthorityBiz.saveAuthorization(roleId, raList);
			ActionContext.getContext().put("authorization", "��Ȩ�ɹ�");
		} else {
			ActionContext.getContext().put("authorization", "��Ȩʧ�ܣ���ѡ���ɫ");
		}
		return "roleauthority";
	}
	
	/**
	 * ��ת������Աҳ��
	 * @return
	 * @throws Exception
	 */
	public String goAssignEmp() throws Exception {
		if (roleId == null || roleId.equals("")) {
			ActionContext.getContext().put("roleGet", "��ѡ���¼һ����ɫ");
			return "roleListAction";
		}
		List orgs = orgBiz.findOrgsIncludFullName();
		List emps = roleBiz.getEmployeeByRoleId(roleId);
		ActionContext.getContext().put("orgList", orgs);
		ActionContext.getContext().put("empList", emps);
		return "toassignemp";
	}
	
	public String assignemp() throws Exception {
		if (roleId == null || roleId.equals("")) {
			ActionContext.getContext().put("roleGet", "��ѡ���¼һ����ɫ");
			return "roleListAction";
		}
		if (emps != null && !emps.equals("")) {
			List<RoleEmployee> relist = new ArrayList<RoleEmployee>();
			String[] empIds = emps.split(",");
			for (int i = 0; i < empIds.length; i++) {
				RoleEmployee re = new RoleEmployee();
				re.setRoleId(roleId);
				re.setEmployeeId(empIds[i]);
				relist.add(re);
			}
			reBiz.updateRoleEmployees(roleId, relist);
		} else {
			reBiz.deleteByRoleId(roleId);
		}
		List orgs = orgBiz.findOrgsIncludFullName();
		List emps = roleBiz.getEmployeeByRoleId(roleId);
		ActionContext.getContext().put("orgList", orgs);
		ActionContext.getContext().put("empList", emps);
		ActionContext.getContext().put("assignMsg", "����ɹ�");
		return "toassignemp";
	}
}
