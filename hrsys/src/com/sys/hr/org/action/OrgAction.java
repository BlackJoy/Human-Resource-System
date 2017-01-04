package com.sys.hr.org.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;
import com.sys.menu.Menu;

public class OrgAction extends ActionSupport {

	private IOrgBIZ orgBiz;

	private String orgId;

	private Org org;

	private String parentCode;
	private IBaseBIZ commonBiz;

	private String id1;// 将要被划转的组织的id
	private String id2;// 划转到组织的id

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public IOrgBIZ getOrgBiz() {
		return orgBiz;
	}

	public void setOrgBiz(IOrgBIZ orgBiz) {
		this.orgBiz = orgBiz;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * 跳转组织列表页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		try {
			Org orgTree = orgBiz.getOrgTree();
			ActionContext.getContext().put("orgTree", orgTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";

	}

	// -------------------利用dtree实现组织列表树，点击事件未显示岗位start---------------------

	public String listByDtree() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'组织结构');");
		str.append(createOrgStr(orgTree));
		request.setAttribute("orgDTree", str.toString());

		return "listByDtree";
	}

	public String createOrgStr(Org org) {
		StringBuilder str = new StringBuilder();
		// 得到该org的字符串
		if (org.getOrgParentId() == null) {// 如果没有父组织，说明处在顶层
			str.append("d.add(" + org.getId() + ",0,'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'post_list.action?orgId="
					+ org.getId() + "\\\')');");
		} else {
			str.append("d.add(" + org.getId() + "," + org.getOrgParentId()
					+ ",'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'post_list.action?orgId="
					+ org.getId() + "\\\')');");

		}
		// 得到menu的孩子的字符串
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStr(mm));
			}
		}
		return str.toString();
	}

	// -------------------利用dtree实现组织列表树，点击事件为显示岗位end---------------------

	// -------------------利用dtree实现组织列表树，点击事件为显示员工start---------------------

	public String listByDtreeShowEmp() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'组织结构');");
		str.append(createOrgStr2(orgTree));
		request.setAttribute("orgDTreeShowEmp", str.toString());

		return "listByDtreeShowEmp";
	}
	//社保账号listByDtreeShowEmptoShebao
	public String listByDtreeShowEmptoShebao() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'组织结构');");
		str.append(createOrgStr2(orgTree));
		request.setAttribute("orgDTreeShowEmptoShebao", str.toString());

		return "listByDtreeShowEmptoShebao";
	}
	public String createOrgStr2(Org org) {
		StringBuilder str = new StringBuilder();
		// 得到该org的字符串
		if (org.getOrgParentId() == null) {// 如果没有父组织，说明处在顶层
			str.append("d.add("
					+ org.getId()
					+ ",0,'"
					+ org.getOrgShortName()
					+ "','javascript:selectCate(\\\'employee_qingjialist.action?orgId="
					+ org.getId() + "\\\')');");
		} else {
			str.append("d.add("
					+ org.getId()
					+ ","
					+ org.getOrgParentId()
					+ ",'"
					+ org.getOrgShortName()
					+ "','javascript:selectCate(\\\'employee_qingjialist.action?orgId="
					+ org.getId() + "\\\')');");

		}
		// 得到menu的孩子的字符串
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStr2(mm));
			}
		}
		return str.toString();
	}

	// -------------------利用dtree实现组织列表树，点击事件为显示员工end---------------------

	// --------------------利用dtree实现组织列表树，点击事件通过requeset获得参数传入start-----------------------------

	public String listByDtreeShowPara() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		String s=request.getParameter("para1");
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'组织结构');");
		str.append(createOrgStrShowPara(orgTree,s));
		request.setAttribute("orgDTreeShowPara", str.toString());

		return "listByDtreeShowPara";
	}

	public String createOrgStrShowPara(Org org,String s) {
		StringBuilder str = new StringBuilder();
		
		// 得到该org的字符串
		if (org.getOrgParentId() == null) {// 如果没有父组织，说明处在顶层
			str.append("d.add(" + org.getId() + ",0,'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'" + s + ".action?orgId="
					+ org.getId() + "\\\')');");
		} else {
			str.append("d.add(" + org.getId() + "," + org.getOrgParentId()
					+ ",'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'" + s + ".action?orgId="
					+ org.getId() + "\\\')');");

		}
		// 得到menu的孩子的字符串
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStrShowPara(mm,s));
			}
		}
		return str.toString();
	}

	// --------------------利用dtree实现组织列表树，点击事件通过参数传入end-----------------------------

	// -------------------利用ztree实现组织划转合并时候的组织树（带有复选框start）————————————

	public String listByZtree() throws Exception {
		List<Org> orgList = orgBiz.findAll();

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "orgPosts" });// 没有这两句会报懒加载异常

		String str = JSONArray.fromObject(orgList, jsonConfig).toString();
		String jsonStr = str.replaceAll("orgFullName", "name");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orgZTree", jsonStr);
		return "listByZtree";
	}

	// -------------------利用ztree实现组织划转合并时候的组织树（带有复选框）————————————
	
	
	
	//----利用ztree显示组织树（为了选择其中的一个组织，比如帐套更新时更改组织）start-------------
	
	public String listByZtreeForChoose() throws Exception {
		List<Org> orgList = orgBiz.findAll();

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "orgPosts" });// 没有这两句会报懒加载异常

		String str = JSONArray.fromObject(orgList, jsonConfig).toString();
		String jsonStr = str.replaceAll("orgFullName", "name");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orgZTree", jsonStr);
		return "listByZtreeForChoose";
	}

	
	
	
	
	
	//----利用ztree显示组织树（为了选择其中的一个组织，比如帐套更新时更改组织）end-------------
	
	
	

	// ---------------------组织划转start---------------------------------

	public String transfer() {

		Org org1 = (Org) commonBiz.getEntityById(id1, Org.class);// 被划转的组织
		Org org2 = (Org) commonBiz.getEntityById(id2, Org.class);// 划转到的组织

		return "transfer";
	}

	// ---------------------组织划转end---------------------------------

	/**
	 * 跳转增加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "请勾选一个父组织");
			return "listAction";
		}
		Org org = orgBiz.findById(orgId);
		if (org == null) {
			ActionContext.getContext().put("orgMsg", "你选择的组织不存在，请重新选择");
			return "listAction";
		}
		if (org.getOrgStatus() == 0) {
			ActionContext.getContext().put("orgMsg", "被注销的组织不能增加子组织");
			return "listAction";
		}
		ActionContext.getContext().put("p_org", org);
		return "toAdd";
	}

	/**
	 * 增加子组织
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (parentCode == null || parentCode.equals("")) {
			ActionContext.getContext().put("orgMsg", "请勾选一个父组织");
			return "listAction";
		}
		org.setOrgCode(parentCode + "-" + org.getOrgCode());
		orgBiz.saveOrg(org);
		ActionContext.getContext().put("doAdd", "保存成功");
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "请勾选一个父组织");
			return "listAction";
		}
		Org org = orgBiz.findById(orgId);
		if (org == null) {
			ActionContext.getContext().put("orgMsg", "你选择的组织不存在，请重新选择");
			return "listAction";
		}
		ActionContext.getContext().put("p_org", org);
		return "toUpdate";
	}

	/**
	 * 修改组织
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if (parentCode != null && !parentCode.equals("")) {
			org.setOrgCode(parentCode + "-" + org.getOrgCode());
		}
		try {
			orgBiz.updateOrg(org);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActionContext.getContext().put("orgMsg", "保存成功");
		return "listAction";
	}

	public String delete() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "请勾选一个父组织");
			return "listAction";
		}
		List<Org> orgs = orgBiz.findByParentId(orgId);
		if (orgs != null && orgs.size() > 0) {
			ActionContext.getContext().put("orgMsg", "该组织下有子组织，不能删除");
			return "listAction";
		}
		Org o = orgBiz.findById(orgId);
		if (o.getOrgParentId() == null || o.getOrgParentId().equals("")) {
			ActionContext.getContext().put("orgMsg", "根节点不能删除");
			return "listAction";
		}
		orgBiz.deleteOrg(orgId);
		ActionContext.getContext().put("orgMsg", "删除成功");
		return "listAction";
	}
}
