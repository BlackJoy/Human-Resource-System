package com.sys.hr.kaoqin2.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.kaoqin.Kaoqin;
import com.sys.hr.kaoqin2.Kaoqinmonth;
import com.sys.hr.kaoqin2.Kaoqinyear;
import com.sys.hr.org.Post;
import com.sys.login.User;
import com.sys.util.ExcelUtil;

public class KaoqinyearAction extends ActionSupport {

	private int pageSize=10;
	
	private String[] kaoqinId;
	private int pageIndex;
	private IBaseBIZ commonBiz;
	
	private String employeeName;
	private String kaoqinTime;
	private String stateQq;
	private String stateCq;
	private String stateQj;
	private String stateCd;
	private String isaddJb;
	private String orgId;
	private String postname;
	private Kaoqinyear Kaoqinyear;
	
	private Date kqTime;
	private String have;
	private String have_bumen;
	private String have_bumen1;
	private String have_bumen2;
	private String excel;
	
	
	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public String getHave_bumen() {
		return have_bumen;
	}

	public void setHave_bumen(String have_bumen) {
		this.have_bumen = have_bumen;
	}

	public Date getKqTime() {
		return kqTime;
	}

	public void setKqTime(Date kqTime) {
		this.kqTime = kqTime;
	}

	public String getHave() {
		return have;
	}

	public void setHave(String have) {
		this.have = have;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public String[] getKaoqinId() {
		return kaoqinId;
	}

	public void setKaoqinId(String[] kaoqinId) {
		this.kaoqinId = kaoqinId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getKaoqinTime() {
		return kaoqinTime;
	}

	public void setKaoqinTime(String kaoqinTime) {
		this.kaoqinTime = kaoqinTime;
	}

	public String getStateQq() {
		return stateQq;
	}

	public void setStateQq(String stateQq) {
		this.stateQq = stateQq;
	}

	public String getStateCq() {
		return stateCq;
	}

	public void setStateCq(String stateCq) {
		this.stateCq = stateCq;
	}

	public String getStateQj() {
		return stateQj;
	}

	public void setStateQj(String stateQj) {
		this.stateQj = stateQj;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getIsaddJb() {
		return isaddJb;
	}

	public void setIsaddJb(String isaddJb) {
		this.isaddJb = isaddJb;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}



	public Kaoqinyear getKaoqinyear() {
		return Kaoqinyear;
	}

	public void setKaoqinyear(Kaoqinyear Kaoqinyear) {
		this.Kaoqinyear = Kaoqinyear;
	}

	public String main() throws Exception {
		return "tomain";
	}
	
	/**
	 * 岗位列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		
		String sql = "select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.orgshortname as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"'";
		Page page=commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("kaoqinyear_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		
		have=(String) ActionContext.getContext().get("have");
		ActionContext.getContext().put("have",have);
		have_bumen=(String) ActionContext.getContext().get("have_bumen");
		ActionContext.getContext().put("have_bumen",have_bumen);
		have_bumen1=(String) ActionContext.getContext().get("have_bumen1");
		ActionContext.getContext().put("have_bumen1",have_bumen1);
		have_bumen2=(String) ActionContext.getContext().get("have_bumen2");
		ActionContext.getContext().put("have_bumen2",have_bumen2);
		

		kqTime=(Date) ActionContext.getContext().get("kqTime");
		ActionContext.getContext().put("kqTime", kqTime);
		return "list";
	}

	public String renyuanhuizong() throws Exception {
		 have="-1";
				String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"'  ";
				
				List it= commonBiz.findSQL2MapList(sql);
				
				for(int i=0;i<it.size();i++){
					
				Map p =(Map) it.get(i);
				String ls_id=(String)p.get("ID");
				String ls_employeename=(String)p.get("EMPLOYEENAME");
				String ls_orgid=(String)p.get("ORGID");
				String ls_postname=(String)p.get("POSTNAME");
				//得到月份跟年份
				int ls_year=(kqTime.getYear())%100+2000;
				int ls_month=kqTime.getMonth()+1;
				String ls_month0;
				if( ls_month/10 <1) {ls_month0="0"+ls_month; }
				else {ls_month0 =ls_month+"";}
				
				//查出日常考勤表该月份和年份下缺勤的次数
				String sql_getstate0="select count(*) as qq  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and   extract(month from  tbl_kaoqin.kaoqin_time)='"+ls_month0+"' and state='0' ";
				List state0=commonBiz.findSQL2MapList(sql_getstate0);
				Map state_0= (Map)state0.get(0);
				int ls_state0= ((BigDecimal) state_0.get("QQ")).intValue();
				//出勤次数
				String sql_getstate1="select count(*) as cq  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and   extract(month from  tbl_kaoqin.kaoqin_time)='"+ls_month0+"' and state='1' ";
				List state1=commonBiz.findSQL2MapList(sql_getstate1);
				Map state_1= (Map)state1.get(0);
				int ls_state1= ((BigDecimal) state_1.get("CQ")).intValue();
				//请假
				String sql_getstate2="select count(*) as qj  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and   extract(month from  tbl_kaoqin.kaoqin_time)='"+ls_month0+"' and state='2' ";
				List state2=commonBiz.findSQL2MapList(sql_getstate2);
				Map state_2= (Map)state2.get(0);
				int ls_state2= ((BigDecimal) state_2.get("QJ")).intValue();
				//迟到
				String sql_getstate3="select count(*) as cd  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and   extract(month from  tbl_kaoqin.kaoqin_time)='"+ls_month0+"' and state='3' ";
				List state3=commonBiz.findSQL2MapList(sql_getstate3);
				Map state_3= (Map)state3.get(0);
				int ls_state3= ((BigDecimal) state_3.get("CD")).intValue();
				//加班
				String sql_getadd1="select count(*) as jb  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and   extract(month from  tbl_kaoqin.kaoqin_time)='"+ls_month0+"' and isadd='1' ";
				List add1=commonBiz.findSQL2MapList(sql_getadd1);
				Map add_1= (Map)add1.get(0);
				int ls_add1= ((BigDecimal) add_1.get("JB")).intValue();
				
				
				String sql_check=" select * from tbl_kaoqin_year,tbl_employee where  tbl_employee.employeename=tbl_kaoqin_year.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
				List it_check=commonBiz.findSQL2MapList(sql_check);
				for(int j=0;j<it_check.size();j++)
				{	
					Map p_check=(Map)it_check.get(j);
					String check_stateqq=(String)p_check.get("STATE_QQ");
					String check_statecq=(String)p_check.get("STATE_CQ");
					String check_stateqj=(String)p_check.get("STATE_QJ");
					String check_statecd=(String)p_check.get("STATE_CD");
					String check_addjb=(String)p_check.get("ISADD_JB");
					
					String check_date=(String)p_check.get("KAOQINTIME");
					String check_year=check_date.substring(0, 4);
					if(check_year.equalsIgnoreCase(ls_year+"")  &&  !(check_stateqq.equalsIgnoreCase("-1") && check_statecq.equalsIgnoreCase("-1") && check_stateqj.equalsIgnoreCase("-1") && check_statecd.equalsIgnoreCase("-1") &&  check_addjb.equalsIgnoreCase("-1") )  )
					{	
						have="1";
						ActionContext.getContext().put("have", have);
						return "listAction";
					}else{
						continue;
					}
				
				}
				have="-1";
				ActionContext.getContext().put("have", have);
				
				String sql_1 = "select count(*) id from tbl_kaoqin_year";
				List n = (List) commonBiz.findSQL2MapList(sql_1);
				Map number = (Map) n.get(0);
				int num = ((BigDecimal) number.get("ID")).intValue();
				String fin_id=(num+1)+"";
				Kaoqinyear=new Kaoqinyear();
				Kaoqinyear.setId(fin_id);
				Kaoqinyear.setEmployeeName(ls_employeename);
				Kaoqinyear.setKaoqinTime(ls_year+"");
				Kaoqinyear.setPostname(ls_postname);
				Kaoqinyear.setOrgid(ls_orgid);
				Kaoqinyear.setStateQq(ls_state0+"");
				Kaoqinyear.setStateCq(ls_state1+"");
				Kaoqinyear.setStateQj(ls_state2+"");
				Kaoqinyear.setStateCd(ls_state3+"");
				Kaoqinyear.setIsaddJb(ls_add1+"");
				commonBiz.save(Kaoqinyear);
			}
		
		return "listAction";
	}
	
	boolean panduan=false;
	public String bumenhuizong() throws Exception {
		 have_bumen="-1";
		 have_bumen1="-1";
		 have_bumen2="-1";
				String sql="select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"'";
				
				List it= commonBiz.findSQL2MapList(sql);
				
				for(int i=0;i<it.size();i++){
				Map p =(Map) it.get(i);
				String ls_id=(String)p.get("ID");
				String ls_employeename=(String)p.get("EMPLOYEENAME");
				String ls_orgid=(String)p.get("ORGID");
				String ls_postname=(String)p.get("POSTNAME");
				//得到月份跟年份
				int ls_year=(kqTime.getYear())%100+2000;
				
				
				//查出日常考勤表该月份和年份下缺勤的次数
				String sql_getstate0="select count(*) as qq  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"'  and state='0' ";
				List state0=commonBiz.findSQL2MapList(sql_getstate0);
				Map state_0= (Map)state0.get(0);
				int ls_state0= ((BigDecimal) state_0.get("QQ")).intValue();
				//出勤次数
				String sql_getstate1="select count(*) as cq  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"'  and state='1' ";
				List state1=commonBiz.findSQL2MapList(sql_getstate1);
				Map state_1= (Map)state1.get(0);
				int ls_state1= ((BigDecimal) state_1.get("CQ")).intValue();
				//请假
				String sql_getstate2="select count(*) as qj  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"'  and state='2' ";
				List state2=commonBiz.findSQL2MapList(sql_getstate2);
				Map state_2= (Map)state2.get(0);
				int ls_state2= ((BigDecimal) state_2.get("QJ")).intValue();
				//迟到
				String sql_getstate3="select count(*) as cd  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"' and state='3' ";
				List state3=commonBiz.findSQL2MapList(sql_getstate3);
				Map state_3= (Map)state3.get(0);
				int ls_state3= ((BigDecimal) state_3.get("CD")).intValue();
				//加班
				String sql_getadd1="select count(*) as jb  from tbl_kaoqin where orgid='"+ls_orgid+"' and employeename='"+ls_employeename+"'  and postname='"+ls_postname+"'  and extract(year from  tbl_kaoqin.kaoqin_time)='"+ls_year+"'  and isadd='1' ";
				List add1=commonBiz.findSQL2MapList(sql_getadd1);
				Map add_1= (Map)add1.get(0);
				int ls_add1= ((BigDecimal) add_1.get("JB")).intValue();
				
				
				String sql_check=" select * from tbl_kaoqin_year,tbl_employee where  tbl_employee.employeename=tbl_kaoqin_year.employeename and tbl_employee.id='"+ls_id+"'";
				List it_check=commonBiz.findSQL2MapList(sql_check);
				for(int j=0;j<it_check.size();j++)
				{	
					Map p_check=(Map)it_check.get(j);
					String check_stateqq=(String)p_check.get("STATE_QQ");
					String check_statecq=(String)p_check.get("STATE_CQ");
					String check_stateqj=(String)p_check.get("STATE_QJ");
					String check_statecd=(String)p_check.get("STATE_CD");
					String check_addjb=(String)p_check.get("ISADD_JB");
					
					String check_date=(String)p_check.get("KAOQINTIME");
					String check_year=check_date.substring(0, 4);
					if(check_year.equalsIgnoreCase(ls_year+"")   &&  !(check_stateqq.equalsIgnoreCase("-1") && check_statecq.equalsIgnoreCase("-1") && check_stateqj.equalsIgnoreCase("-1") && check_statecd.equalsIgnoreCase("-1") &&  check_addjb.equalsIgnoreCase("-1") )  )
					{	
						panduan=true;
						have_bumen="1";
						ActionContext.getContext().put("have_bumen", have_bumen);
						
						//更新操作，将原来的数据更新
						
						
						String check_kaoqinid=(String)p_check.get("ID");
						Kaoqinyear=new Kaoqinyear();
						Kaoqinyear.setId(check_kaoqinid);
						Kaoqinyear.setEmployeeName(ls_employeename);
						Kaoqinyear.setKaoqinTime(ls_year+"");
						Kaoqinyear.setPostname(ls_postname);
						Kaoqinyear.setOrgid(ls_orgid);
						Kaoqinyear.setStateQq(ls_state0+"");
						Kaoqinyear.setStateCq(ls_state1+"");
						Kaoqinyear.setStateQj(ls_state2+"");
						Kaoqinyear.setStateCd(ls_state3+"");
						Kaoqinyear.setIsaddJb(ls_add1+"");
						commonBiz.update(Kaoqinyear);

						
					}else{
						continue;
					}
				
				}
				
					if(it_check.size()==0 || panduan==false ) 
					{
						have_bumen2="2";
						ActionContext.getContext().put("have_bumen2", have_bumen2);
						
						String sql_1 = "select count(*) id from tbl_kaoqin_year";
						List n = (List) commonBiz.findSQL2MapList(sql_1);
						Map number = (Map) n.get(0);
						int num = ((BigDecimal) number.get("ID")).intValue();
						String fin_id=(num+1)+"";
					Kaoqinyear=new Kaoqinyear();
					Kaoqinyear.setId(fin_id);
					Kaoqinyear.setEmployeeName(ls_employeename);
					Kaoqinyear.setKaoqinTime(ls_year+"");
					Kaoqinyear.setPostname(ls_postname);
					Kaoqinyear.setOrgid(ls_orgid);
					Kaoqinyear.setStateQq(ls_state0+"");
					Kaoqinyear.setStateCq(ls_state1+"");
					Kaoqinyear.setStateQj(ls_state2+"");
					Kaoqinyear.setStateCd(ls_state3+"");
					Kaoqinyear.setIsaddJb(ls_add1+"");
					commonBiz.save(Kaoqinyear);
					
					
					}
				}
				
				if(panduan==false){
					have_bumen1="1";
					ActionContext.getContext().put("have_bumen1", have_bumen1);
				}
		
		return "listAction";
	}
	
	public String huizongchaxun() throws Exception {
		
		int ls_year=(kqTime.getYear())%100+2000;
		String ls_time=ls_year+"";
		
		String sql = "select * from tbl_kaoqin_year where orgid='"+orgId+"' and kaoqintime='"+ls_time+"'  ";
		Page page=commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("kaoqinyear_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		ActionContext.getContext().put("kqTime", kqTime);
		return "tochaxun";
	}
	
	
public String daochujilu() throws Exception {
		
		int ls_year=(kqTime.getYear())%100+2000;
		
		String ls_time=ls_year+"";
		
		String sql = "select * from tbl_kaoqin_year where orgid='"+orgId+"' and kaoqintime='"+ls_time+"' ";
		List titlelist=new ArrayList();

		titlelist.add("ID");
		titlelist.add("职员名称");
		titlelist.add("部门ID");
		titlelist.add("考勤时间");
		titlelist.add("职位名称");
		titlelist.add("缺勤次数");
		titlelist.add("出勤次数");
		titlelist.add("请假次数");
		titlelist.add("迟到次数");
		titlelist.add("加班次数");
		//从数据库得到的List集合，每一条记录都是Map集合
		List it=commonBiz.findSQL2MapList(sql);
		//将每条记录存为对象，放入List集合中，直接调用通用的Excel导出函数
		List it_new=new ArrayList();
		
		for(int i=0;i<it.size();i++){
			Map map=(Map)it.get(i);
			String id=(String)map.get("ID");
			String l_employeename=(String)map.get("EMPLOYEENAME");
			String kqtime=(String)map.get("KAOQINTIME");
			String state_qq=(String)map.get("STATE_QQ");
			String state_cq=(String)map.get("STATE_CQ");
			String state_qj=(String)map.get("STATE_QJ");
			String state_cd=(String)map.get("STATE_CD");
			String orgid=(String)map.get("ORGID");
			String l_postname=(String)map.get("POSTNAME");
			String isadd_jb=(String)map.get("ISADD_JB");
			
			List ls=new ArrayList();
			ls.add(id);
			ls.add(l_employeename);
			ls.add(orgid);
			ls.add(kqtime);
			ls.add(l_postname);
			ls.add(state_qq);
			ls.add(state_cq);
			ls.add(state_qj);
			ls.add(state_cd);
			ls.add(isadd_jb);
			
			it_new.add(ls);
		}
		//调用Excel通用方法直接导出
		
		
		HttpServletResponse response=ServletActionContext.getResponse();
		//获取输出流
		OutputStream out = response.getOutputStream();
		//重置输出流
		response.reset();
		//设置默认文件名
		response.setHeader( "Content-disposition" ,  "attachment;filename=test" );
		//设置导出Excel报表的导出形式
		response.setContentType("application/vnd.ms-excel");
		ExcelUtil.exportExcel(titlelist, it_new, out);
		//设置输出形式
		System.setOut(new PrintStream(out));
		//刷新输出流
		out.flush();
		//关闭输出流
		if(out!=null){
			out.close();
		}		
		
		return "listAction";
	}
	
}
