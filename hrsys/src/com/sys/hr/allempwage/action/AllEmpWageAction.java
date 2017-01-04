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
	
	private String allEmpWageListJson;//��ʾԱ�����ʽ��洫�����Ĺ������ݼ���json��
	private String wageItemListJson;//��ʾԱ�����ʽ��洫�����Ĺ��������ƣ�json��
	
	

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
	 * ��ת����ʾ���׽��棬ѡ��һ�����ף������������е�Ա��������Ϣ��ʾ����
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

	
	//��ʾԱ������
	public String toShow() throws Exception{
		ZhangTao chooseZt=(ZhangTao) commonBiz.getEntityById(chooseZtId, ZhangTao.class);
    	Set<CmpnWageInfo> empInfoSet=chooseZt.getEmpSet();//��ø������µ�Ա�����ʵ���
    	Set<WageItem> wageItemSet =chooseZt.getWageItemSet();//��ø������µ����й�����
    	
    	List allEmpWageList = new ArrayList();//������Ÿ�����������Ա���Ĺ�����Ϣ
    	
    	List wageItemList = new ArrayList();//������Ÿ����������й���������֣�������allEmpWageList��ȡ�ö�Ӧ��ֵ
    	
    	for(CmpnWageInfo cwi:empInfoSet){
    		Map empMap = new LinkedHashMap();
    		empMap.put("Ա�����", cwi.getEmpNo());//����Ա�����
    		//**********************��ѯԱ�����ڿ۳�������start*************************
    		List empListForAbsence= commonBiz.findSQL2MapList("select * from tbl_employee where employeecode='"+cwi.getEmpNo()+"'");
    		Map map1=(Map) empListForAbsence.get(0);
    		String absenceEmpId=(String) map1.get("ID");
    		List codeAbsenceList =  commonBiz.findSQL2MapList("select * from code_absence where empid='"+absenceEmpId+"' and wagetime='2014-04'");
    		Map codeAbsenceMap=(Map) codeAbsenceList.get(0);
    		BigDecimal absenceBd=(BigDecimal) codeAbsenceMap.get("VALUE");
    		double absenceWage=absenceBd.doubleValue();
    		//**********************��ѯԱ�����ڿ۳�������end*************************
    		empMap.put("Ա������", cwi.getEmpNm());//����Ա������
    		double shouldGetWage =0;//Ӧ������
    		double actualGetWage=0;//ʵ������
    		for(WageItem wi : wageItemSet){
    			String wageItemName = wi.getWageName();//Ա���Ĺ�����
    			wageItemList.add(wageItemName);
    			String sql="select * from code_"+wi.getWageNo()+" where id='"+cwi.getEmpId()+"' and wagetime='2014-04'";
    			List<Map> list=commonBiz.findSQL2MapList(sql);
    			Map map = list.get(0);
    			BigDecimal bigValue=(BigDecimal) map.get("SALARY");//�������Ӧ��ֵ
    			double v = bigValue.doubleValue();
    			empMap.put(wageItemName, v); //�������еĹ�����	
    			if(wi.getOperNam().equalsIgnoreCase("+")){
    				shouldGetWage+=v;
    			}
    			actualGetWage+=v;
    		}
    		actualGetWage+=absenceWage;//ʵ�������п۳�ȱ��
    		empMap.put("����", absenceWage);
    		empMap.put("Ӧ������", shouldGetWage);
    		empMap.put("ʵ������", actualGetWage);
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
	//***********************�������ݼ�start**********************************	
		List fieldName = new ArrayList();
//		fieldName.add("name");
//		fieldName.add("id");
		List fieldData = new ArrayList();
	
//		list1.add("aaa");
//		list1.add("001");
//		fieldData.add(list1);
	//**********************�������ݼ�end********************************
		//**********************��̨���ݼ�start**************************
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
		//*********************��̨���ݼ�end*************************
		HttpServletResponse response=ServletActionContext.getResponse();
		//��ȡ�����
		OutputStream out = response.getOutputStream();
		//���������
		response.reset();
		//����Ĭ���ļ���
		response.setHeader( "Content-disposition" ,  "attachment;filename=test" );
		//���õ���Excel����ĵ�����ʽ
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		ExcelUtil.exportExcel(fieldName, fieldData, out);
		//���������ʽ
		System.setOut(new PrintStream(out));
		//ˢ�������
		out.flush();
		//�ر������
		if(out!=null){
			out.close();
		}
		
		return null;
	}

}
