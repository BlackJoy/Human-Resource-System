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

	private String id1;// ��Ҫ����ת����֯��id
	private String id2;// ��ת����֯��id

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
	 * ��ת��֯�б�ҳ
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

	// -------------------����dtreeʵ����֯�б���������¼�δ��ʾ��λstart---------------------

	public String listByDtree() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'��֯�ṹ');");
		str.append(createOrgStr(orgTree));
		request.setAttribute("orgDTree", str.toString());

		return "listByDtree";
	}

	public String createOrgStr(Org org) {
		StringBuilder str = new StringBuilder();
		// �õ���org���ַ���
		if (org.getOrgParentId() == null) {// ���û�и���֯��˵�����ڶ���
			str.append("d.add(" + org.getId() + ",0,'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'post_list.action?orgId="
					+ org.getId() + "\\\')');");
		} else {
			str.append("d.add(" + org.getId() + "," + org.getOrgParentId()
					+ ",'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'post_list.action?orgId="
					+ org.getId() + "\\\')');");

		}
		// �õ�menu�ĺ��ӵ��ַ���
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStr(mm));
			}
		}
		return str.toString();
	}

	// -------------------����dtreeʵ����֯�б���������¼�Ϊ��ʾ��λend---------------------

	// -------------------����dtreeʵ����֯�б���������¼�Ϊ��ʾԱ��start---------------------

	public String listByDtreeShowEmp() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'��֯�ṹ');");
		str.append(createOrgStr2(orgTree));
		request.setAttribute("orgDTreeShowEmp", str.toString());

		return "listByDtreeShowEmp";
	}
	//�籣�˺�listByDtreeShowEmptoShebao
	public String listByDtreeShowEmptoShebao() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'��֯�ṹ');");
		str.append(createOrgStr2(orgTree));
		request.setAttribute("orgDTreeShowEmptoShebao", str.toString());

		return "listByDtreeShowEmptoShebao";
	}
	public String createOrgStr2(Org org) {
		StringBuilder str = new StringBuilder();
		// �õ���org���ַ���
		if (org.getOrgParentId() == null) {// ���û�и���֯��˵�����ڶ���
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
		// �õ�menu�ĺ��ӵ��ַ���
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStr2(mm));
			}
		}
		return str.toString();
	}

	// -------------------����dtreeʵ����֯�б���������¼�Ϊ��ʾԱ��end---------------------

	// --------------------����dtreeʵ����֯�б���������¼�ͨ��requeset��ò�������start-----------------------------

	public String listByDtreeShowPara() throws Exception {
		Org orgTree = orgBiz.getOrgTree();

		HttpServletRequest request = ServletActionContext.getRequest();
		String s=request.getParameter("para1");
		StringBuilder str = new StringBuilder();
		str.append("d.add(0,-1,'��֯�ṹ');");
		str.append(createOrgStrShowPara(orgTree,s));
		request.setAttribute("orgDTreeShowPara", str.toString());

		return "listByDtreeShowPara";
	}

	public String createOrgStrShowPara(Org org,String s) {
		StringBuilder str = new StringBuilder();
		
		// �õ���org���ַ���
		if (org.getOrgParentId() == null) {// ���û�и���֯��˵�����ڶ���
			str.append("d.add(" + org.getId() + ",0,'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'" + s + ".action?orgId="
					+ org.getId() + "\\\')');");
		} else {
			str.append("d.add(" + org.getId() + "," + org.getOrgParentId()
					+ ",'" + org.getOrgShortName()
					+ "','javascript:selectCate(\\\'" + s + ".action?orgId="
					+ org.getId() + "\\\')');");

		}
		// �õ�menu�ĺ��ӵ��ַ���
		if (org.getOrgList().size() > 0) {
			for (Org mm : org.getOrgList()) {
				str.append(createOrgStrShowPara(mm,s));
			}
		}
		return str.toString();
	}

	// --------------------����dtreeʵ����֯�б���������¼�ͨ����������end-----------------------------

	// -------------------����ztreeʵ����֯��ת�ϲ�ʱ�����֯�������и�ѡ��start��������������������������

	public String listByZtree() throws Exception {
		List<Org> orgList = orgBiz.findAll();

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "orgPosts" });// û��������ᱨ�������쳣

		String str = JSONArray.fromObject(orgList, jsonConfig).toString();
		String jsonStr = str.replaceAll("orgFullName", "name");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orgZTree", jsonStr);
		return "listByZtree";
	}

	// -------------------����ztreeʵ����֯��ת�ϲ�ʱ�����֯�������и�ѡ�򣩡�����������������������
	
	
	
	//----����ztree��ʾ��֯����Ϊ��ѡ�����е�һ����֯���������׸���ʱ������֯��start-------------
	
	public String listByZtreeForChoose() throws Exception {
		List<Org> orgList = orgBiz.findAll();

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "orgPosts" });// û��������ᱨ�������쳣

		String str = JSONArray.fromObject(orgList, jsonConfig).toString();
		String jsonStr = str.replaceAll("orgFullName", "name");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("orgZTree", jsonStr);
		return "listByZtreeForChoose";
	}

	
	
	
	
	
	//----����ztree��ʾ��֯����Ϊ��ѡ�����е�һ����֯���������׸���ʱ������֯��end-------------
	
	
	

	// ---------------------��֯��תstart---------------------------------

	public String transfer() {

		Org org1 = (Org) commonBiz.getEntityById(id1, Org.class);// ����ת����֯
		Org org2 = (Org) commonBiz.getEntityById(id2, Org.class);// ��ת������֯

		return "transfer";
	}

	// ---------------------��֯��תend---------------------------------

	/**
	 * ��ת����ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "�빴ѡһ������֯");
			return "listAction";
		}
		Org org = orgBiz.findById(orgId);
		if (org == null) {
			ActionContext.getContext().put("orgMsg", "��ѡ�����֯�����ڣ�������ѡ��");
			return "listAction";
		}
		if (org.getOrgStatus() == 0) {
			ActionContext.getContext().put("orgMsg", "��ע������֯������������֯");
			return "listAction";
		}
		ActionContext.getContext().put("p_org", org);
		return "toAdd";
	}

	/**
	 * ��������֯
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (parentCode == null || parentCode.equals("")) {
			ActionContext.getContext().put("orgMsg", "�빴ѡһ������֯");
			return "listAction";
		}
		org.setOrgCode(parentCode + "-" + org.getOrgCode());
		orgBiz.saveOrg(org);
		ActionContext.getContext().put("doAdd", "����ɹ�");
		return "listAction";
	}

	/**
	 * ��ת�޸�ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "�빴ѡһ������֯");
			return "listAction";
		}
		Org org = orgBiz.findById(orgId);
		if (org == null) {
			ActionContext.getContext().put("orgMsg", "��ѡ�����֯�����ڣ�������ѡ��");
			return "listAction";
		}
		ActionContext.getContext().put("p_org", org);
		return "toUpdate";
	}

	/**
	 * �޸���֯
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
		ActionContext.getContext().put("orgMsg", "����ɹ�");
		return "listAction";
	}

	public String delete() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("orgMsg", "�빴ѡһ������֯");
			return "listAction";
		}
		List<Org> orgs = orgBiz.findByParentId(orgId);
		if (orgs != null && orgs.size() > 0) {
			ActionContext.getContext().put("orgMsg", "����֯��������֯������ɾ��");
			return "listAction";
		}
		Org o = orgBiz.findById(orgId);
		if (o.getOrgParentId() == null || o.getOrgParentId().equals("")) {
			ActionContext.getContext().put("orgMsg", "���ڵ㲻��ɾ��");
			return "listAction";
		}
		orgBiz.deleteOrg(orgId);
		ActionContext.getContext().put("orgMsg", "ɾ���ɹ�");
		return "listAction";
	}
}
