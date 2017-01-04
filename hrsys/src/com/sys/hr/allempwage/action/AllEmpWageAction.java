package com.sys.hr.allempwage.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.empwage.CmpnWageInfo;
import com.sys.hr.wageitem.WageItem;
import com.sys.hr.zhangtao.ZhangTao;
import com.sys.util.ExcelUtil;
public class AllEmpWageAction extends ActionSupport {

	private int pageSize = 10;
	private int pageIndex;
	private IBaseBIZ commonBiz;
	private String chooseZtId;
	
	private String allEmpWageListJson;//显示员工工资界面传过来的工资数据集，json串
	private String wageItemListJson;//显示员工工资界面传过来的工资项名称，json串
	
	

	public String getAllEmpWageListJson() {
		return allEmpWageListJson;
	}

	public void setAllEmpWageListJson(String allEmpWageListJson) {
		this.allEmpWageListJson = allEmpWageListJson;
	}

	public String getWageItemListJson() {
		return wageItemListJson;
	}

	public void setWageItemListJson(String wageItemListJson) {
		this.wageItemListJson = wageItemListJson;
	}

	public String getChooseZtId() {
		return chooseZtId;
	}

	public void setChooseZtId(String chooseZtId) {
		this.chooseZtId = chooseZtId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

//	public String main() throws Exception {	
//		ZhangTao test=(ZhangTao) commonBiz.getEntityById("297ee6cd45d632a10145d6334ee20000", ZhangTao.class);
//		Set testset=test.getEmpSet();
//		
//		return "tomain";
//	}

	/**
	 * 跳转到显示帐套界面，选择一个帐套，将该帐套所有的员工工资信息显示出来
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		String sql = "select * from wage_type";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("wageType_list_page", page);
		return "list";
	}

	
	//显示员工工资
	public String toShow() throws Exception{
		ZhangTao chooseZt=(ZhangTao) commonBiz.getEntityById(chooseZtId, ZhangTao.class);
    	Set<CmpnWageInfo> empInfoSet=chooseZt.getEmpSet();//获得该帐套下的员工工资档案
    	Set<WageItem> wageItemSet =chooseZt.getWageItemSet();//获得该帐套下的所有工资项
    	
    	List allEmpWageList = new ArrayList();//用来存放该帐套下所有员工的工资信息
    	
    	List wageItemList = new ArrayList();//用来存放该帐套下所有工资项的名字，用来存allEmpWageList中取得对应的值
    	
    	for(CmpnWageInfo cwi:empInfoSet){
    		Map empMap = new LinkedHashMap();
    		empMap.put("员工编号", cwi.getEmpNo());//加入员工编号
    		//**********************查询员工考勤扣除工资项start*************************
    		List empListForAbsence= commonBiz.findSQL2MapList("select * from tbl_employee where employeecode='"+cwi.getEmpNo()+"'");
    		Map map1=(Map) empListForAbsence.get(0);
    		String absenceEmpId=(String) map1.get("ID");
    		List codeAbsenceList =  commonBiz.findSQL2MapList("select * from code_absence where empid='"+absenceEmpId+"' and wagetime='2014-04'");
    		Map codeAbsenceMap=(Map) codeAbsenceList.get(0);
    		BigDecimal absenceBd=(BigDecimal) codeAbsenceMap.get("VALUE");
    		double absenceWage=absenceBd.doubleValue();
    		//**********************查询员工考勤扣除工资项end*************************
    		empMap.put("员工姓名", cwi.getEmpNm());//加入员工姓名
    		double shouldGetWage =0;//应发工资
    		double actualGetWage=0;//实发工资
    		for(WageItem wi : wageItemSet){
    			String wageItemName = wi.getWageName();//员工的工资项
    			wageItemList.add(wageItemName);
    			String sql="select * from code_"+wi.getWageNo()+" where id='"+cwi.getEmpId()+"' and wagetime='2014-04'";
    			List<Map> list=commonBiz.findSQL2MapList(sql);
    			Map map = list.get(0);
    			BigDecimal bigValue=(BigDecimal) map.get("SALARY");//工资项对应的值
    			double v = bigValue.doubleValue();
    			empMap.put(wageItemName, v); //加入所有的工资项	
    			if(wi.getOperNam().equalsIgnoreCase("+")){
    				shouldGetWage+=v;
    			}
    			actualGetWage+=v;
    		}
    		actualGetWage+=absenceWage;//实发工资中扣除缺勤
    		empMap.put("考勤", absenceWage);
    		empMap.put("应发工资", shouldGetWage);
    		empMap.put("实发工资", actualGetWage);
    		allEmpWageList.add(empMap);
    	}
    	String allEmpWageListJson=JSONArray.fromObject(allEmpWageList).toString();
    	String wageItemListJson=JSONArray.fromObject(wageItemList).toString();
    	ActionContext.getContext().put("allEmpWageListJson", allEmpWageListJson);
    	ActionContext.getContext().put("wageItemListJson", wageItemListJson);
    	ActionContext.getContext().put("allEmpWageList", allEmpWageList);
    	ActionContext.getContext().put("wageItemList", wageItemList);
		return "toShow";
	}
	
	@SuppressWarnings("deprecation")
	public String export() throws Exception{
	//***********************测试数据集start**********************************	
		List fieldName = new ArrayList();
//		fieldName.add("name");
//		fieldName.add("id");
		List fieldData = new ArrayList();
	
//		list1.add("aaa");
//		list1.add("001");
//		fieldData.add(list1);
	//**********************测试数据集end********************************
		//**********************后台数据集start**************************
		List<LinkedHashMap> list=JSONArray.toList(JSONArray.fromObject(allEmpWageListJson),LinkedHashMap.class);
		LinkedHashMap map0=list.get(0);
		Set<String> set0=map0.keySet();
		for(String str:set0){
			fieldName.add(str);
		}
		for(LinkedHashMap map :list){
			Set<String> set=map.keySet();
			List list1=new ArrayList();
			for(String s:set){
				list1.add(map.get(s));
			}
			fieldData.add(list1);
		}
		//*********************后台数据集end*************************
		HttpServletResponse response=ServletActionContext.getResponse();
		//获取输出流
		OutputStream out = response.getOutputStream();
		//重置输出流
		response.reset();
		//设置默认文件名
		response.setHeader( "Content-disposition" ,  "attachment;filename=test" );
		//设置导出Excel报表的导出形式
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		ExcelUtil.exportExcel(fieldName, fieldData, out);
		//设置输出形式
		System.setOut(new PrintStream(out));
		//刷新输出流
		out.flush();
		//关闭输出流
		if(out!=null){
			out.close();
		}
		
		return null;
	}

}
