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

	private File excel;// 导入的excel文件
	private int pageSize = 10;
	private String orgId;
	private int pageIndex;
	private List<ZhangTao> ztList = new ArrayList<ZhangTao>();// 存放所有的帐套
	// 该map暂时没用到
	private Map<String, List<WageItem>> zt2wageItemMap = new HashMap<String, List<WageItem>>();// 存放帐套对应的工资项
	private IBaseBIZ commonBiz;

	private String empId;// 存放选中员工的id
	private String zt;// 存放选择的帐套id；

	// chooseEmpWageMap用来存放选中的员工所有的工资项以及值，key为工资项名字，value为该员工该项工资项的值
	private Map<String, Double> chooseEmpWageMap = new HashMap<String, Double>();

	private String chooseWageTime;// 录入的工资月份
	private String calcWageTime;// 薪资计算的月份

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
	 * 岗位列表
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
	 * 跳转账户增加页面
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
		jsonConfig1.setExcludes(new String[] { "wageItemSet" });// 没有这两句会报懒加载异常
		String str1 = JSONArray.fromObject(ztList, jsonConfig1).toString();
		JsonConfig jsonConfig2 = new JsonConfig();
		jsonConfig2.setExcludes(new String[] { "zhangtaoSet" });// 没有这两句会报懒加载异常
		String str2 = JSONArray.fromObject(zt2wageItemMap, jsonConfig2)
				.toString();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("str1", str1);
		request.setAttribute("str2", str2);

		return "toAdd";
	}

	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		ztList = commonBiz.findALl2EntityList("wage_type", ZhangTao.class);
		int ztId = Integer.valueOf(zt);
		ZhangTao chooseZt = ztList.get(ztId);// 选中的帐套
		Employee chooseEmp = (Employee) commonBiz.getEntityById(empId,
				Employee.class);// 选中的员工

		// -----------------------日期 start------------------
		chooseWageTime = chooseWageTime.substring(0, 7);
		boolean isWageItemHasRec = false;// 该员工对应的工资项中是否有该月记录
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

		// -----------------------日期 end------------------

		// ----------------------维护员工工资档案表start--------------------------------
		/*
		 * 注意：在填写工资档案的时候，如果是第一次录入，需要插入到工资档案表中同时将对应的工资插入对应的工资项表中
		 * 如果员工工资档案表中已经存在了该员工，那么就不能插入到工资档案表中。。。。对应的工资需要插入到对应的表中
		 * 如果档案存在，并且对应的工资表中已经有该月工资则alert
		 */
		CmpnWageInfo isExist = (CmpnWageInfo) commonBiz.getEntityById(empId,
				CmpnWageInfo.class);
		if (isExist != null) {// 该员工档案已经存在,
			if (isWageItemHasRec) {//
				ActionContext.getContext().getSession().put("isCwiExist", 1);
				return "listAction";// 重新执行main方法，在empwagelist.jsp中先判断是否为1，是就alert，接着讲它的值置为0；
			} else {// 这种情况仅仅将工资项值插入
					// --------------------获得选择帐套的工资项的值,并插入数据库start-------------------------
				HttpServletRequest request = ServletActionContext.getRequest();
				Map<String, Integer> map = new HashMap<String, Integer>();// key:wageNo,value:对应的值
				Set<WageItem> set = chooseZt.getWageItemSet();
				for (WageItem w : set) {
					String s = w.getWageName();
					String k = "code_" + w.getWageNo();// 对应的工资项表名
					String v = request.getParameter(s);
					int value = Integer.valueOf(v);
					if (w.getOperNam().equals("-")) {
						value = -value;
					}
					map.put(k, value);

					// commonBiz.insertIntoWageItem(k, chooseEmp.getId(),
					// value);//
					// 插入工资项表中 insert into ** values(id,value,time)
					commonBiz.excuteByDmlSql("insert into " + k + " values("
							+ chooseEmp.getId() + "," + value + ",'"
							+ chooseWageTime + "')");

				}

				// --------------------获得选择帐套的工资项的值，并插入数据库end-------------------------
			}
		} else {// 该员工档案不存在并且该员工该月工资项表中无记录（第一次录入）
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
			commonBiz.save(cwi);// 插入员工档案表中

			// ----------------------维护员工工资档案表start--------------------------------

			// --------------------获得选择帐套的工资项的值,并插入数据库start-------------------------
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Integer> map = new HashMap<String, Integer>();// key:wageNo,value:对应的值
			Set<WageItem> set = chooseZt.getWageItemSet();
			for (WageItem w : set) {
				String s = w.getWageName();
				String k = "code_" + w.getWageNo();// 对应的工资项表名
				String v = request.getParameter(s);
				int value = Integer.valueOf(v);
				if (w.getOperNam().equals("-")) {
					value = -value;
				}
				map.put(k, value);

				// commonBiz.insertIntoWageItem(k, chooseEmp.getId(), value);//
				// 插入工资项表中
				commonBiz.excuteByDmlSql("insert into " + k + " values("
						+ chooseEmp.getId() + "," + value + ",'"
						+ chooseWageTime + "')");

			}

			// --------------------获得选择帐套的工资项的值，并插入数据库end-------------------------
		}
		return "listAction";
	}

	public String calcWage() throws Exception {
		// 1.通过empId去cmpn_wage_info中查找该员工对应的帐套
		CmpnWageInfo cwi = (CmpnWageInfo) commonBiz.getEntityById(empId,
				CmpnWageInfo.class);
		String wageTypName = cwi.getWageType();
		// 2.找到该帐套对应的工资项
		List list = commonBiz.findByCondition("WAGE_TYPE", "WAGE_TYPE_NAME='"
				+ wageTypName + "'", ZhangTao.class);
		ZhangTao empZt = (ZhangTao) list.get(0);
		Set<WageItem> wageItems = empZt.getWageItemSet();

		// 3.从工资项表中查找到该员工对应的值,并计算实发工资和应发工资
		double shouldGetWage = 0;// 应发工资
		double actualGetWage = 0;// 实发工资
		double absenceWage = 0;// 考勤扣除项
		Class clz = CmpnMonthAll.class;
		Object obj = clz.newInstance();// 月度工资表实例
		calcWageTime = calcWageTime.substring(0, 7);// 格式为2014-04
		for (WageItem w : wageItems) {
			String wageItemName = w.getWageName();
			String tableName = "code_" + w.getWageNo();// 工资项的表名
			String sql = "select * from " + tableName + " where wagetime='"
					+ calcWageTime + "' and id='" + empId + "'";// 通过选择的月份进行薪资计算
			List l = commonBiz.findSQL2MapList(sql);
			if (l.size() == 0) {
				ActionContext.getContext().getSession()
						.put("isMonthWageHas", 0);// 没有该月份的记录，需要提示一下
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

				// ------------------将工资插入到月度表中start----------------------------
				String wageItemId = w.getWageId();// 工资项id，通过该id在cmpn_type_relation找到对应的列名，以存到月度工资表中
				String cmpnTypeRelationSql = "select wage_sum_column_name from cmpn_wage_relation where wage_id='"
						+ wageItemId + "'";
				List cmpnTypeRelationList = commonBiz
						.findSQL2MapList(cmpnTypeRelationSql);
				Map cmpnTypeRelationMap = (Map) cmpnTypeRelationList.get(0);
				String colName = (String) cmpnTypeRelationMap
						.get("WAGE_SUM_COLUMN_NAME");// 工资项对应的列名

				Method[] methods = clz.getDeclaredMethods();
				StringBuffer sb = new StringBuffer();// 拼接set方法
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
		// -----------------计算王晓考勤部分扣除工资start--------------------------------

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
		if (km == null) {// 未对该人员进行考勤，默认全勤
			absentNoReasonNum = 0;
			absentHasReasonNum = 0;
			lateNum = 0;
		} else {
			// 得到缺勤天数
			String s1 = km.getStateQq();
			absentNoReasonNum = Integer.parseInt(s1);
			// 得到请假天数
			String s2 = km.getStateQj();
			absentHasReasonNum = Integer.parseInt(s2);
			// 得到迟到天数
			String s3 = km.getStateCd();
			lateNum = Integer.parseInt(s3);
			// 得到考勤的额月份
			String absenceMonth = km.getKaoqinTime();
		}
		// 考勤扣除工资：缺勤：20元/每天 |迟到：10元/每天|请假：5元/天
		absenceWage = -(20 * absentNoReasonNum + 10 * lateNum + 5 * absentHasReasonNum);
		chooseEmpWageMap.put("考勤", absenceWage);
		// 实发工资中扣除考勤项
		actualGetWage += absenceWage;
		// 插入到考勤工资表中（code_absence）
		// 如果考勤工资表（code_absence）中没有该员工该月的记录，就插入。。否则就不插入
		String findCodeAbsenceSql = "select * from code_absence where empid='"
				+ empId + "'" + " and wagetime='" + calcWageTime + "'";
		List findCodeAbsenceList = commonBiz
				.findSQL2MapList(findCodeAbsenceSql);
		if (findCodeAbsenceList.size() == 0) {
			commonBiz.excuteByDmlSql("insert into hrsys.code_absence values ("
					+ empId + "," + absenceWage + ",'" + calcWageTime + "')");
		}
		// -----------------计算王晓考勤部分扣除工资end-------------------------------

		CmpnMonthAll cma = (CmpnMonthAll) obj;
		cma.setAbsenceWage(absenceWage);
		cma.setEmpId(empId);
		cma.setMonthtime(calcWageTime);

		// 如果月度工资表中已经有该员工该月的记录，则不插入
		List findCmpnMonthList = commonBiz
				.findSQL2MapList("select * from cmpn_month_all where emp_id='"
						+ empId + "' and monthtime='" + calcWageTime + "'");
		if (findCmpnMonthList.size() == 0) {
			commonBiz.save(cma);// 插入********
		}
		// ------------------将工资插入到月度表中end----------------------------
		ActionContext.getContext().put("shouldGetWage", shouldGetWage);
		ActionContext.getContext().put("actualGetWage", actualGetWage);
		return "calcwage";
	}

	// 跳转到导入界面
	public String toImport() {

		return "toImport";
	}

	// 导入
	public String importExcel() {

		return "importExcel";
	}

}
