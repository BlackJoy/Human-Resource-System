package com.sys.hr.employ.action;

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
import com.sys.common.IConstant;
import com.sys.common.Page;
import com.sys.hr.employ.Employ;
import com.sys.hr.employ.biz.IEmployBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;

public class EmployAction extends ActionSupport {

	private static final long serialVersionUID = -1363724273273955032L;
	private IEmployBIZ employBiz;
	private IApproveBIZ approveBiz;
	private IOrgBIZ orgBiz;
	private String orgId;
	private int pageIndex;
	private Employ employ;
	private String employId;
	private Date shiyongTime;
	private Date ruzhiTime;
	private String apId;
	private String apprName;
	private String id;
	private Employee emp;

	public IEmployBIZ getEmployBiz() {
		return employBiz;
	}

	public void setEmployBiz(IEmployBIZ employBiz) {
		this.employBiz = employBiz;
	}

	public IApproveBIZ getApproveBiz() {
		return approveBiz;
	}

	public void setApproveBiz(IApproveBIZ approveBiz) {
		this.approveBiz = approveBiz;
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Employ getEmploy() {
		return employ;
	}

	public void setEmploy(Employ employ) {
		this.employ = employ;
	}

	public String getEmployId() {
		return employId;
	}

	public void setEmployId(String employId) {
		this.employId = employId;
	}

	public Date getShiyongTime() {
		return shiyongTime;
	}

	public void setShiyongTime(Date shiyongTime) {
		this.shiyongTime = shiyongTime;
	}

	public Date getRuzhiTime() {
		return ruzhiTime;
	}

	public void setRuzhiTime(Date ruzhiTime) {
		this.ruzhiTime = ruzhiTime;
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

	/**
	 * ��תӦƸ��Ϣ����ҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yingpin() throws Exception {
		return "yingpin";
	}

	/**
	 * ӦƸ��Ϣ�б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String yingpinlist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findYingPinByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("yingpin_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "yingpinlist";
	}

	/**
	 * ��תӦƸ��Ϣ¼��ҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAddYingPin() throws Exception {
		if (orgId == null || orgId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ����֯");
			return "yingpinlistAction";
		}
		Org org = employBiz.findOrgById(orgId);
		ActionContext.getContext().put("curr_org", org);
		return "toAddYingPin";
	}

	/**
	 * ����ӦƸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doAddYingPin() throws Exception {
		employBiz.saveYingPin(employ);
		ActionContext.getContext().put("employMsg", "����ɹ�");
		return "toAddYingPinAction";
	}

	/**
	 * ��תӦƸ��Ϣ��ϸҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDetailYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��ӦƸ��Ϣ");
			return "yingpinlistAction";
		}
		Map yp = employBiz.findYingPinById(employId);
		ActionContext.getContext().put("curr_yingpin", yp);
		return "toYingPinDetail";
	}

	/**
	 * ��תӦƸ��Ϣ�޸�ҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��ӦƸ��Ϣ");
			return "yingpinlistAction";
		}
		Map yp = employBiz.findYingPinById(employId);
		List<Map> orgs = orgBiz.findOrgsIncludFullName();
		ActionContext.getContext().put("curr_yingpin", yp);
		ActionContext.getContext().put("orglist", orgs);
		return "toUpdate";
	}

	/**
	 * �޸�ӦƸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdateYingPin() throws Exception {
		employBiz.updateYingPin(employ);
		ActionContext.getContext().put("employMsg", "�޸ĳɹ�");
		return "yingpinlistAction";
	}

	/**
	 * ɾ��ӦƸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDeleteYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��ӦƸ��Ϣ");
			return "yingpinlistAction";
		}
		employBiz.deleteYingPin(employId);
		ActionContext.getContext().put("employMsg", "ɾ���ɹ�");
		return "yingpinlistAction";
	}

	/**
	 * ת��¼�ÿ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doShiYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��ӦƸ��Ϣ");
			return "yingpinlistAction";
		}
		boolean b = employBiz.updateToShiYong(employId, shiyongTime);
		if (b) {
			ActionContext.getContext().put("employMsg", "��ת��¼�ÿ���");
		} else {
			ActionContext.getContext().put("employMsg", "����ʧ�ܣ�������");
		}
		return "yingpinlistAction";
	}

	/**
	 * ��ת¼�ÿ��˹���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String luyong() throws Exception {
		return "luyong";
	}

	/**
	 * ¼�ù����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String luyonglist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findLuYongByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("luyong_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "luyonglist";
	}

	/**
	 * ��ת¼���޸�ҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdateLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��¼����Ϣ");
			return "luyonglistAction";
		}
		Map ly = employBiz.findLuYongById(employId);
		List<Map> orgs = orgBiz.findOrgsIncludFullName();
		ActionContext.getContext().put("curr_luyong", ly);
		ActionContext.getContext().put("orglist", orgs);
		return "toUpdateLuYong";
	}

	/**
	 * �޸�¼����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doUpdateLuYong() throws Exception {
		employBiz.updateLuYong(emp);
		ActionContext.getContext().put("employMsg", "�޸ĳɹ�");
		return "luyonglistAction";
	}

	/**
	 * ɾ��¼����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDeleteLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��¼����Ϣ");
			return "luyonglistAction";
		}
		employBiz.deleteYingPin(employId);
		ActionContext.getContext().put("employMsg", "ɾ���ɹ�");
		return "luyonglistAction";
	}

	/**
	 * ת����ְ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doRuZhi() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��¼����Ϣ");
			return "luyonglistAction";
		}
		boolean b = employBiz.updateToRuZhi(employId, ruzhiTime);
		if (b) {
			ActionContext.getContext().put("employMsg", "��ת����ְ����");
		} else {
			ActionContext.getContext().put("employMsg", "����ʧ�ܣ�������");
		}
		return "luyonglistAction";
	}

	/**
	 * ת��ӦƸ
	 * @return
	 * @throws Exception
	 */
	public String doYingPin() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��¼����Ϣ");
			return "luyonglistAction";
		}
		boolean b = employBiz.updateToYingPin(employId);
		if (b) {
			ActionContext.getContext().put("employMsg", "��ת��ӦƸ����");
		} else {
			ActionContext.getContext().put("employMsg", "����ʧ�ܣ�������");
		}
		return "luyonglistAction";
	}
	
	/**
	 * ��ת¼����Ϣ��ϸҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toDetailLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��¼����Ϣ");
			return "luyonglistAction";
		}
		Map ly = employBiz.findLuYongById(employId);
		ActionContext.getContext().put("curr_luyong", ly);
		return "toLuYongDetail";
	}
	
	/**
	 * ��ת��ְ������ҳ
	 * @return
	 * @throws Exception
	 */
	public String ruzhi() throws Exception {
		return "ruzhi";
	}
	
	/**
	 * ��ְ�����б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ruzhilist() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		Page page = employBiz.findRuZhiByOrgId(orgId, pageIndex,
				IConstant.PAGINATION_PAGESIZE);
		ActionContext.getContext().put("ruzhi_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "ruzhilist";
	}
	
	/**
	 * ת��¼��
	 * @return
	 * @throws Exception
	 */
	public String doLuYong() throws Exception {
		if (employId == null || employId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ����ְ��Ϣ");
			return "ruzhilistAction";
		}
		boolean b = employBiz.updateToLuYong(employId);
		if (b) {
			ActionContext.getContext().put("employMsg", "��ת��¼�ù���");
		} else {
			ActionContext.getContext().put("employMsg", "����ʧ�ܣ�������");
		}
		return "ruzhilistAction";
	}
	
	/**
	 * ת�뷢������ҳ
	 * @return
	 * @throws Exception
	 */
	public String doFlow() throws Exception {
		try {
			if (employId == null || employId.equals("")) {
				ActionContext.getContext().put("employMsg", "��ѡ��һ����ְ��Ϣ");
				return "ruzhilistAction";
			}
			List e = employBiz.findRuZhiById(employId);
			List ap = approveBiz.getAllApprove();
			System.out.println();
			if (e == null && e.size() == 0) {
				ActionContext.getContext().put("employMsg", "����ְ��Ϣ�����ڣ���ȷ��");
				return "ruzhilistAction";
			}
			ActionContext.getContext().put("curr_ruzhi", e);
			ActionContext.getContext().put("aplist", ap);
			ActionContext.getContext().put("employId", employId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toFlow";
	}
	
	/**
	 * ������������
	 * @return
	 * @throws Exception
	 */
	public String loadAppFlow() throws Exception {
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��������Ŀ");
			return "toFlowAction";
		}
		Approve apr = approveBiz.getApprove(apId);
		if (apr == null) {
			ActionContext.getContext().put("employMsg", "������Ŀ�����ڣ�������ѡ��");
			return "toFlowAction";
		}
		List<ApproveFlow> flowList = approveBiz.getFlowsByApproveId(apId);
		if (flowList == null) {
			ActionContext.getContext().put("employMsg", "������Ŀ�����ڣ�������ѡ��");
			return "toFlowAction";
		}
		Map<Integer, List<ApproveFlow>> flowMap = new TreeMap<Integer, List<ApproveFlow>>();
		for (ApproveFlow flow : flowList) {
			if (flowMap.containsKey(flow.getOrderId())) {
				flowMap.get(flow.getOrderId()).add(flow);
			} else {
				List<ApproveFlow> fl = new ArrayList<ApproveFlow>();
				fl.add(flow);
				flowMap.put(flow.getOrderId(), fl);
			}
		}
		ActionContext.getContext().put("selFlow", flowMap);
		return "toFlowDetail";
	}
	
	/**
	 * ������������
	 * @return
	 * @throws Exception
	 */
	public String saveAppFlow() throws Exception {
		try {
			if (apId == null || apId.equals("")) {
				ActionContext.getContext().put("employMsg", "��ѡ��һ��������Ŀ");
				return "toFlowAction";
			}
			Approve apr = approveBiz.getApprove(apId);
			if (apr == null) {
				ActionContext.getContext().put("employMsg", "������Ŀ�����ڣ�������ѡ��");
				return "toFlowAction";
			}
			ApproveExec ae = new ApproveExec();
			ae.setApproveId(apr.getId());
			ae.setApproveName(apprName);
			ae.setStartTime(new Date());
			ae.setStatus(1);
			ae.setContentURL(apr.getApproveContent());
			ae.setContentID(employId);
			ae.setIsoverandsave(1);
			approveBiz.saveApproveExecAndFlowExec(ae);
			employBiz.updateToRuZhiApprove(employId);
			ActionContext.getContext().put("employMsg", "����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ruzhilistAction";
	}
	
	/**
	 * ��������������ʾ��ְ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String showDetail() throws Exception{
		List e = employBiz.findRuZhiById(id);
		ActionContext.getContext().put("curr_ruzhi", e);
		return "showDetail";
	}
	
	/**
	 * ����������Ĳ���
	 * @return
	 * @throws Exception
	 */
	public String flowover() throws Exception {
		if (apId == null || apId.equals("")) {
			ActionContext.getContext().put("employMsg", "��ѡ��һ��������������Ϣ");
			return "ruzhilistAction";
		}
		boolean b = employBiz.flowOverAndSaveData(apId);
		if (b) {
			ActionContext.getContext().put("employMsg", "�����Ѵ浵");
			return "ruzhilistAction";
		} else {
			ActionContext.getContext().put("employMsg", "����ʧ�ܣ�������");
			return "ruzhilistAction";
		}
	}
}
