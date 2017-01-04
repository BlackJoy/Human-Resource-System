package com.sys.hr.empwage.action;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.empwage.CmpnMonthAll;
import com.sys.hr.empwage.CmpnWageInfo;
import com.sys.hr.kaoqin2.Kaoqinmonth;
import com.sys.hr.org.Org;
import com.sys.hr.wageitem.WageItem;
import com.sys.hr.zhangtao.ZhangTao;

public class EmpWageAction extends ActionSupport {

	private File excel;// �����excel�ļ�
	private int pageSize = 10;
	private String orgId;
	private int pageIndex;
	private List<ZhangTao> ztList = new ArrayList<ZhangTao>();// ������е�����
	// ��map��ʱû�õ�
	private Map<String, List<WageItem>> zt2wageItemMap = new HashMap<String, List<WageItem>>();// ������׶�Ӧ�Ĺ�����
	private IBaseBIZ commonBiz;

	private String empId;// ���ѡ��Ա����id
	private String zt;// ���ѡ�������id��

	// chooseEmpWageMap�������ѡ�е�Ա�����еĹ������Լ�ֵ��keyΪ���������֣�valueΪ��Ա����������ֵ
	private Map<String, Double> chooseEmpWageMap = new HashMap<String, Double>();

	private String chooseWageTime;// ¼��Ĺ����·�
	private String calcWageTime;// н�ʼ�����·�

	public String getCalcWageTime() {
		return calcWageTime;
	}

	public void setCalcWageTime(String calcWageTime) {
		this.calcWageTime = calcWageTime;
	}

	public String getChooseWageTime() {
		return chooseWageTime;
	}

	public void setChooseWageTime(String chooseWageTime) {
		this.chooseWageTime = chooseWageTime;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public Map<String, Double> getChooseEmpWageMap() {
		return chooseEmpWageMap;
	}

	public void setChooseEmpWageMap(Map<String, Double> chooseEmpWageMap) {
		this.chooseEmpWageMap = chooseEmpWageMap;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<ZhangTao> getZtList() {
		return ztList;
	}

	public void setZtList(List<ZhangTao> ztList) {
		this.ztList = ztList;
	}

	public Map<String, List<WageItem>> getZt2wageItemMap() {
		return zt2wageItemMap;
	}

	public void setZt2wageItemMap(Map<String, List<WageItem>> zt2wageItemMap) {
		this.zt2wageItemMap = zt2wageItemMap;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
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

	public String main() throws Exception {
		return "tomain";
	}

	/**
	 * ��λ�б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		String sql = "select * from cmpn_wage_info where dept_id='"
				+ this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("empWage_list_page", page);
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
		ztList = commonBiz.findALl2EntityList("wage_type", ZhangTao.class);
		Integer i = 0;
		for (ZhangTao z : ztList) {
			List<WageItem> list = new ArrayList<WageItem>();
			Set<WageItem> set = z.getWageItemSet();
			for (WageItem w : set) {
				list.add(w);
			}
			zt2wageItemMap.put((i++).toString(), list);
		}
		JsonConfig jsonConfig1 = new JsonConfig();
		jsonConfig1.setExcludes(new String[] { "wageItemSet" });// û��������ᱨ�������쳣
		String str1 = JSONArray.fromObject(ztList, jsonConfig1).toString();
		JsonConfig jsonConfig2 = new JsonConfig();
		jsonConfig2.setExcludes(new String[] { "zhangtaoSet" });// û��������ᱨ�������쳣
		String str2 = JSONArray.fromObject(zt2wageItemMap, jsonConfig2)
				.toString();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("str1", str1);
		request.setAttribute("str2", str2);

		return "toAdd";
	}

	/**
	 * �����˻�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		ztList = commonBiz.findALl2EntityList("wage_type", ZhangTao.class);
		int ztId = Integer.valueOf(zt);
		ZhangTao chooseZt = ztList.get(ztId);// ѡ�е�����
		Employee chooseEmp = (Employee) commonBiz.getEntityById(empId,
				Employee.class);// ѡ�е�Ա��

		// -----------------------���� start------------------
		chooseWageTime = chooseWageTime.substring(0, 7);
		boolean isWageItemHasRec = false;// ��Ա����Ӧ�Ĺ��������Ƿ��и��¼�¼
		Set<WageItem> wiSet = chooseZt.getWageItemSet();
		for (WageItem wi : wiSet) {
			String tableName = "code_" + wi.getWageNo();
			String sql = "select * from " + tableName + " where wagetime='"
					+ chooseWageTime + "' and id='" + empId + "'";
			List list = commonBiz.findSQL2MapList(sql);
			if (list.size() > 0) {
				isWageItemHasRec = true;
			}
		}

		// -----------------------���� end------------------

		// ----------------------ά��Ա�����ʵ�����start--------------------------------
		/*
		 * ע�⣺����д���ʵ�����ʱ������ǵ�һ��¼�룬��Ҫ���뵽���ʵ�������ͬʱ����Ӧ�Ĺ��ʲ����Ӧ�Ĺ��������
		 * ���Ա�����ʵ��������Ѿ������˸�Ա������ô�Ͳ��ܲ��뵽���ʵ������С���������Ӧ�Ĺ�����Ҫ���뵽��Ӧ�ı���
		 * ����������ڣ����Ҷ�Ӧ�Ĺ��ʱ����Ѿ��и��¹�����alert
		 */
		CmpnWageInfo isExist = (CmpnWageInfo) commonBiz.getEntityById(empId,
				CmpnWageInfo.class);
		if (isExist != null) {// ��Ա�������Ѿ�����,
			if (isWageItemHasRec) {//
				ActionContext.getContext().getSession().put("isCwiExist", 1);
				return "listAction";// ����ִ��main��������empwagelist.jsp�����ж��Ƿ�Ϊ1���Ǿ�alert�����Ž�����ֵ��Ϊ0��
			} else {// �������������������ֵ����
					// --------------------���ѡ�����׵Ĺ������ֵ,���������ݿ�start-------------------------
				HttpServletRequest request = ServletActionContext.getRequest();
				Map<String, Integer> map = new HashMap<String, Integer>();// key:wageNo,value:��Ӧ��ֵ
				Set<WageItem> set = chooseZt.getWageItemSet();
				for (WageItem w : set) {
					String s = w.getWageName();
					String k = "code_" + w.getWageNo();// ��Ӧ�Ĺ��������
					String v = request.getParameter(s);
					int value = Integer.valueOf(v);
					if (w.getOperNam().equals("-")) {
						value = -value;
					}
					map.put(k, value);

					// commonBiz.insertIntoWageItem(k, chooseEmp.getId(),
					// value);//
					// ���빤������� insert into ** values(id,value,time)
					commonBiz.excuteByDmlSql("insert into " + k + " values("
							+ chooseEmp.getId() + "," + value + ",'"
							+ chooseWageTime + "')");

				}

				// --------------------���ѡ�����׵Ĺ������ֵ�����������ݿ�end-------------------------
			}
		} else {// ��Ա�����������ڲ��Ҹ�Ա�����¹���������޼�¼����һ��¼�룩
			CmpnWageInfo cwi = new CmpnWageInfo();
			cwi.setEmpId(empId);
			cwi.setEmpNo(chooseEmp.getEmployeeCode());
			cwi.setEmpNm(chooseEmp.getEmployeeName());
			cwi.setWageTypeId(chooseZt.getWageTypeId());
			cwi.setWageType(chooseZt.getWageTypeName());
			Org chooseOrg = (Org) commonBiz.getEntityById(chooseEmp.getOrgid(),
					Org.class);
			cwi.setDept(chooseOrg.getOrgShortName());
			cwi.setDeptId(chooseEmp.getOrgid());
			commonBiz.save(cwi);// ����Ա����������

			// ----------------------ά��Ա�����ʵ�����start--------------------------------

			// --------------------���ѡ�����׵Ĺ������ֵ,���������ݿ�start-------------------------
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Integer> map = new HashMap<String, Integer>();// key:wageNo,value:��Ӧ��ֵ
			Set<WageItem> set = chooseZt.getWageItemSet();
			for (WageItem w : set) {
				String s = w.getWageName();
				String k = "code_" + w.getWageNo();// ��Ӧ�Ĺ��������
				String v = request.getParameter(s);
				int value = Integer.valueOf(v);
				if (w.getOperNam().equals("-")) {
					value = -value;
				}
				map.put(k, value);

				// commonBiz.insertIntoWageItem(k, chooseEmp.getId(), value);//
				// ���빤�������
				commonBiz.excuteByDmlSql("insert into " + k + " values("
						+ chooseEmp.getId() + "," + value + ",'"
						+ chooseWageTime + "')");

			}

			// --------------------���ѡ�����׵Ĺ������ֵ�����������ݿ�end-------------------------
		}
		return "listAction";
	}

	public String calcWage() throws Exception {
		// 1.ͨ��empIdȥcmpn_wage_info�в��Ҹ�Ա����Ӧ������
		CmpnWageInfo cwi = (CmpnWageInfo) commonBiz.getEntityById(empId,
				CmpnWageInfo.class);
		String wageTypName = cwi.getWageType();
		// 2.�ҵ������׶�Ӧ�Ĺ�����
		List list = commonBiz.findByCondition("WAGE_TYPE", "WAGE_TYPE_NAME='"
				+ wageTypName + "'", ZhangTao.class);
		ZhangTao empZt = (ZhangTao) list.get(0);
		Set<WageItem> wageItems = empZt.getWageItemSet();

		// 3.�ӹ�������в��ҵ���Ա����Ӧ��ֵ,������ʵ�����ʺ�Ӧ������
		double shouldGetWage = 0;// Ӧ������
		double actualGetWage = 0;// ʵ������
		double absenceWage = 0;// ���ڿ۳���
		Class clz = CmpnMonthAll.class;
		Object obj = clz.newInstance();// �¶ȹ��ʱ�ʵ��
		calcWageTime = calcWageTime.substring(0, 7);// ��ʽΪ2014-04
		for (WageItem w : wageItems) {
			String wageItemName = w.getWageName();
			String tableName = "code_" + w.getWageNo();// ������ı���
			String sql = "select * from " + tableName + " where wagetime='"
					+ calcWageTime + "' and id='" + empId + "'";// ͨ��ѡ����·ݽ���н�ʼ���
			List l = commonBiz.findSQL2MapList(sql);
			if (l.size() == 0) {
				ActionContext.getContext().getSession()
						.put("isMonthWageHas", 0);// û�и��·ݵļ�¼����Ҫ��ʾһ��
				return "listAction";
			} else {
				Map map = (Map) l.get(0);
				BigDecimal bd = (BigDecimal) map.get("SALARY");
				Double v = bd.doubleValue();
				chooseEmpWageMap.put(wageItemName, v);
				actualGetWage += v;
				if (w.getOperNam().equals("+")) {
					shouldGetWage += v;
				}

				// ------------------�����ʲ��뵽�¶ȱ���start----------------------------
				String wageItemId = w.getWageId();// ������id��ͨ����id��cmpn_type_relation�ҵ���Ӧ���������Դ浽�¶ȹ��ʱ���
				String cmpnTypeRelationSql = "select wage_sum_column_name from cmpn_wage_relation where wage_id='"
						+ wageItemId + "'";
				List cmpnTypeRelationList = commonBiz
						.findSQL2MapList(cmpnTypeRelationSql);
				Map cmpnTypeRelationMap = (Map) cmpnTypeRelationList.get(0);
				String colName = (String) cmpnTypeRelationMap
						.get("WAGE_SUM_COLUMN_NAME");// �������Ӧ������

				Method[] methods = clz.getDeclaredMethods();
				StringBuffer sb = new StringBuffer();// ƴ��set����
				sb.append("set");
				sb.append(colName);
				String setMethodName = sb.toString();
				for (Method m : methods) {
					if (m.getName().equalsIgnoreCase(setMethodName)) {
						m.invoke(obj, v);
						break;
					}
				}
			}

		}
		// -----------------�����������ڲ��ֿ۳�����start--------------------------------

		// Kaoqinmonth km = (Kaoqinmonth) commonBiz.getEntityById(empId,
		// Kaoqinmonth.class);
		List kaoqinList = commonBiz.findByCondition("TBL_KAOQIN_MONTH", "id='"
				+ empId + "'and kaoqintime='" + calcWageTime + "'",
				Kaoqinmonth.class);
		Kaoqinmonth km =null;
		if (kaoqinList.size() > 0) {
			km = (Kaoqinmonth) kaoqinList.get(0);
		}
		int absentNoReasonNum;
		int absentHasReasonNum;
		int lateNum;
		if (km == null) {// δ�Ը���Ա���п��ڣ�Ĭ��ȫ��
			absentNoReasonNum = 0;
			absentHasReasonNum = 0;
			lateNum = 0;
		} else {
			// �õ�ȱ������
			String s1 = km.getStateQq();
			absentNoReasonNum = Integer.parseInt(s1);
			// �õ��������
			String s2 = km.getStateQj();
			absentHasReasonNum = Integer.parseInt(s2);
			// �õ��ٵ�����
			String s3 = km.getStateCd();
			lateNum = Integer.parseInt(s3);
			// �õ����ڵĶ��·�
			String absenceMonth = km.getKaoqinTime();
		}
		// ���ڿ۳����ʣ�ȱ�ڣ�20Ԫ/ÿ�� |�ٵ���10Ԫ/ÿ��|��٣�5Ԫ/��
		absenceWage = -(20 * absentNoReasonNum + 10 * lateNum + 5 * absentHasReasonNum);
		chooseEmpWageMap.put("����", absenceWage);
		// ʵ�������п۳�������
		actualGetWage += absenceWage;
		// ���뵽���ڹ��ʱ��У�code_absence��
		// ������ڹ��ʱ�code_absence����û�и�Ա�����µļ�¼���Ͳ��롣������Ͳ�����
		String findCodeAbsenceSql = "select * from code_absence where empid='"
				+ empId + "'" + " and wagetime='" + calcWageTime + "'";
		List findCodeAbsenceList = commonBiz
				.findSQL2MapList(findCodeAbsenceSql);
		if (findCodeAbsenceList.size() == 0) {
			commonBiz.excuteByDmlSql("insert into hrsys.code_absence values ("
					+ empId + "," + absenceWage + ",'" + calcWageTime + "')");
		}
		// -----------------�����������ڲ��ֿ۳�����end-------------------------------

		CmpnMonthAll cma = (CmpnMonthAll) obj;
		cma.setAbsenceWage(absenceWage);
		cma.setEmpId(empId);
		cma.setMonthtime(calcWageTime);

		// ����¶ȹ��ʱ����Ѿ��и�Ա�����µļ�¼���򲻲���
		List findCmpnMonthList = commonBiz
				.findSQL2MapList("select * from cmpn_month_all where emp_id='"
						+ empId + "' and monthtime='" + calcWageTime + "'");
		if (findCmpnMonthList.size() == 0) {
			commonBiz.save(cma);// ����********
		}
		// ------------------�����ʲ��뵽�¶ȱ���end----------------------------
		ActionContext.getContext().put("shouldGetWage", shouldGetWage);
		ActionContext.getContext().put("actualGetWage", actualGetWage);
		return "calcwage";
	}

	// ��ת���������
	public String toImport() {

		return "toImport";
	}

	// ����
	public String importExcel() {

		return "importExcel";
	}

}
