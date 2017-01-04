package com.sys.hr.kaoqin.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.kaoqin.Kaoqin;

public class KaoqinAction extends ActionSupport {

	private int pageIndex;
	private int pageSize = 10;

	private Date kaoqinTime;
	private String orgId;
	private String postName;
	private String[] kaoqinId;
	private String state;
	private String isadd;
	private Date kqTime;
	
	private String cq;
	private String qj;
	private String cd;
	private String qq;
	private String jb;

	public Date getKqTime() {
		return kqTime;
	}

	public void setKqTime(Date kqTime) {
		this.kqTime = kqTime;
	}

	private Kaoqin kaoqin;
	private IBaseBIZ commonBiz;
	

	public String[] getKaoqinId() {
		return kaoqinId;
	}

	public void setKaoqinId(String[] kaoqinId) {
		this.kaoqinId = kaoqinId;
	}

	public Kaoqin getKaoqin() {
		return kaoqin;
	}

	public void setKaoqin(Kaoqin kaoqin) {
		this.kaoqin = kaoqin;
	}

	public Date getKaoqinTime() {
		return kaoqinTime;
	}

	public void setKaoqinTime(Date kaoqinTime) {
		this.kaoqinTime = kaoqinTime;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsadd() {
		return isadd;
	}

	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
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
		
//		List ss =commonBiz.findSQL2MapList(sql);
//		for(int i=0;i<ss.size();i++){
//			Map p=(Map)ss.get(i);
//			String ls_orgid=(String)p.get("ORGID");
//			String ls_posname=(String)p.get("POSTNAME");
//			String ls_employeename=(String)p.get("EMPLOYEENAME");
//			String sql_1 = "select count(*) id from tbl_kaoqin";
//			List n = (List) commonBiz.findSQL2MapList(sql_1);
//			Map number = (Map) n.get(0);
//			int num = ((BigDecimal) number.get("ID")).intValue();
//			kaoqin = new Kaoqin();
//			kaoqin.setEmployeeName(ls_employeename);
//			kaoqin.setId( (num+1)+"" );
//			kaoqin.setOrgid(ls_orgid);
//			kaoqin.setPostname(ls_posname);
//			kaoqin.setIsadd("0");
//			kaoqin.setKaoqinTime(kaoqinTime);
//			commonBiz.save(kaoqin);
//		}
		
//		Iterator it=ss.iterator();
//		while(it.hasNext() && it.next()!=null){
//			Map m=(Map)it.next();
//			String ls_orgid=(String)m.get("ORGID");
//			String ls_employeename=(String)m.get("EMPLOYEENAME");
//			String ls_postname=(String)m.get("POSTNAME");
//			Ls aa=new Ls();
//			aa.setEmployeename(ls_employeename);
//			aa.setOrgid(ls_orgid);
//			aa.setPostname(ls_postname);
//			ls_list.add(aa);
//		}
		
		String sql = "select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.orgshortname as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"'";
		
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("kaoqin_list_page", page);
		ActionContext.getContext().put("orgId", orgId);

		cq=(String) ActionContext.getContext().get("cq");
		ActionContext.getContext().put("cq",cq);
		qj=(String) ActionContext.getContext().get("qj");
		ActionContext.getContext().put("qj",qj);
		cd=(String) ActionContext.getContext().get("cd");
		ActionContext.getContext().put("cd",cd);
		qq=(String) ActionContext.getContext().get("qq");
		ActionContext.getContext().put("qq",qq);
		jb=(String) ActionContext.getContext().get("jb");
		ActionContext.getContext().put("jb",jb);
		
		return "list";
	}

	/**
	 * 跳转出勤页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Chuqin() throws Exception {
		cq="-1";
	    for(int i=0;i<kaoqinId.length;i++){
			String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"' and tbl_employee.id='"+kaoqinId[i]+"'  ";

			List it= commonBiz.findSQL2MapList(sql);
			Map p =(Map) it.get(0);
			String ls_employeename=(String)p.get("EMPLOYEENAME");
			String ls_orgid=(String)p.get("ORGID");
			String ls_postname=(String)p.get("POSTNAME");
			
			String sql_check=" select * from tbl_kaoqin,tbl_employee where  tbl_employee.employeename=tbl_kaoqin.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
			List it_check=commonBiz.findSQL2MapList(sql_check);
			for(int j=0;j<it_check.size();j++)
			{	
				Map p_check=(Map)it_check.get(j);
				String check_state=(String)p_check.get("STATE");
				String check_add=(String)p_check.get("ISADD");
				Date check_date=(Date)p_check.get("KAOQIN_TIME");
				
				if(!check_state.equalsIgnoreCase("-1") && check_date.compareTo(kqTime)==0 )
				{	
					cq="1";
					ActionContext.getContext().put("cq", cq);
					return "listAction";
				}else{
					continue;
				}
			
			}
			cq="-1";
			ActionContext.getContext().put("cq", cq);
			
			String sql_1 = "select count(*) id from tbl_kaoqin";
			List n = (List) commonBiz.findSQL2MapList(sql_1);
			Map number = (Map) n.get(0);
			int num = ((BigDecimal) number.get("ID")).intValue();
			String ls_id=(num+1)+"";
			kaoqin=new Kaoqin();
			kaoqin.setId(ls_id);
			kaoqin.setEmployeeName(ls_employeename);
			kaoqin.setIsadd(0+"");
			kaoqin.setKaoqinTime(kqTime);
			kaoqin.setOrgid(ls_orgid);
			kaoqin.setState(1+"");
			kaoqin.setPostname(ls_postname);
			commonBiz.save(kaoqin);
		}

		return "listAction";
	}

	
	public String Qingjia() throws Exception {
		qj="-1";
	    for(int i=0;i<kaoqinId.length;i++){
			String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"' and tbl_employee.id='"+kaoqinId[i]+"'  ";

			List it= commonBiz.findSQL2MapList(sql);
			Map p =(Map) it.get(0);
			String ls_employeename=(String)p.get("EMPLOYEENAME");
			String ls_orgid=(String)p.get("ORGID");
			String ls_postname=(String)p.get("POSTNAME");
			
			String sql_check=" select * from tbl_kaoqin,tbl_employee where  tbl_employee.employeename=tbl_kaoqin.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
			List it_check=commonBiz.findSQL2MapList(sql_check);
			for(int j=0;j<it_check.size();j++)
			{	
				Map p_check=(Map)it_check.get(j);
				String check_state=(String)p_check.get("STATE");
				String check_add=(String)p_check.get("ISADD");
				Date check_date=(Date)p_check.get("KAOQIN_TIME");
				
				if(!check_state.equalsIgnoreCase("-1") && check_date.compareTo(kqTime)==0 )
				{	
					qj="1";
					ActionContext.getContext().put("qj", qj);
					return "listAction";
				}else{
					continue;
				}
			
			}
			qj="-1";
			ActionContext.getContext().put("qj", qj);
			
			String sql_1 = "select count(*) id from tbl_kaoqin";
			List n = (List) commonBiz.findSQL2MapList(sql_1);
			Map number = (Map) n.get(0);
			int num = ((BigDecimal) number.get("ID")).intValue();
			String ls_id=(num+1)+"";
			kaoqin=new Kaoqin();
			kaoqin.setId(ls_id);
			kaoqin.setEmployeeName(ls_employeename);
			kaoqin.setIsadd(0+"");
			kaoqin.setKaoqinTime(kqTime);
			kaoqin.setOrgid(ls_orgid);
			kaoqin.setState(2+"");
			kaoqin.setPostname(ls_postname);
			commonBiz.save(kaoqin);
		    }
	    
	    return "listAction";
	}
	
	
	public String Queqin() throws Exception {
		qq="-1";
	    for(int i=0;i<kaoqinId.length;i++){
			String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"' and tbl_employee.id='"+kaoqinId[i]+"'  ";

			List it= commonBiz.findSQL2MapList(sql);
			Map p =(Map) it.get(0);
			String ls_employeename=(String)p.get("EMPLOYEENAME");
			String ls_orgid=(String)p.get("ORGID");
			String ls_postname=(String)p.get("POSTNAME");
			
			String sql_check=" select * from tbl_kaoqin,tbl_employee where  tbl_employee.employeename=tbl_kaoqin.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
			List it_check=commonBiz.findSQL2MapList(sql_check);
			for(int j=0;j<it_check.size();j++)
			{	
				Map p_check=(Map)it_check.get(j);
				String check_state=(String)p_check.get("STATE");
				String check_add=(String)p_check.get("ISADD");
				Date check_date=(Date)p_check.get("KAOQIN_TIME");
				
				if(!check_state.equalsIgnoreCase("-1") && check_date.compareTo(kqTime)==0 )
				{	
					qq="1";
					ActionContext.getContext().put("qq", qq);
					return "listAction";
				}else{
					continue;
				}
			
			}
			qq="-1";
			ActionContext.getContext().put("qq", qq);
			
			String sql_1 = "select count(*) id from tbl_kaoqin";
			List n = (List) commonBiz.findSQL2MapList(sql_1);
			Map number = (Map) n.get(0);
			int num = ((BigDecimal) number.get("ID")).intValue();
			String ls_id=(num+1)+"";
			kaoqin=new Kaoqin();
			kaoqin.setId(ls_id);
			kaoqin.setEmployeeName(ls_employeename);
			kaoqin.setIsadd(0+"");
			kaoqin.setKaoqinTime(kqTime);
			kaoqin.setOrgid(ls_orgid);
			kaoqin.setState(0+"");
			kaoqin.setPostname(ls_postname);
			commonBiz.save(kaoqin);
		    }
	    
	    return "listAction";
	}

	

	public String Chidao() throws Exception {
		cd="-1";
	    for(int i=0;i<kaoqinId.length;i++){
			String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"' and tbl_employee.id='"+kaoqinId[i]+"'  ";

			List it= commonBiz.findSQL2MapList(sql);
			Map p =(Map) it.get(0);
			String ls_employeename=(String)p.get("EMPLOYEENAME");
			String ls_orgid=(String)p.get("ORGID");
			String ls_postname=(String)p.get("POSTNAME");
			
			String sql_check=" select * from tbl_kaoqin,tbl_employee where  tbl_employee.employeename=tbl_kaoqin.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
			List it_check=commonBiz.findSQL2MapList(sql_check);
			for(int j=0;j<it_check.size();j++)
			{	
				Map p_check=(Map)it_check.get(j);
				String check_state=(String)p_check.get("STATE");
				String check_add=(String)p_check.get("ISADD");
				Date check_date=(Date)p_check.get("KAOQIN_TIME");
				
				if(!check_state.equalsIgnoreCase("-1") && check_date.compareTo(kqTime)==0 )
				{	
					cd="1";
					ActionContext.getContext().put("cd", cd);
					return "listAction";
				}else{
					continue;
				}
			
			}
			cd="-1";
			ActionContext.getContext().put("cd", cd);
			
			String sql_1 = "select count(*) id from tbl_kaoqin";
			List n = (List) commonBiz.findSQL2MapList(sql_1);
			Map number = (Map) n.get(0);
			int num = ((BigDecimal) number.get("ID")).intValue();
			String ls_id=(num+1)+"";
			kaoqin=new Kaoqin();
			kaoqin.setId(ls_id);
			kaoqin.setEmployeeName(ls_employeename);
			kaoqin.setIsadd(0+"");
			kaoqin.setKaoqinTime(kqTime);
			kaoqin.setOrgid(ls_orgid);
			kaoqin.setState(3+"");
			kaoqin.setPostname(ls_postname);
			commonBiz.save(kaoqin);
		    }
	    
	    return "listAction";
	}

	public String Jiaban() throws Exception {
		jb="0";
	    for(int i=0;i<kaoqinId.length;i++){
			String sql=" select tbl_employee.id as id, tbl_employee.employeename as employeeName,tbl_org.id as orgid,tbl_post.post_name as postName from tbl_employee,tbl_employee2position,tbl_post,tbl_org where tbl_employee.id=tbl_employee2position.employee_id and tbl_org.id=tbl_employee2position.org_id and tbl_post.orgid=tbl_employee2position.org_id and tbl_employee.orgid=tbl_employee2position.org_id and tbl_employee2position.position_id=tbl_post.id and tbl_post.orgid='"+this.orgId+"' and tbl_employee.id='"+kaoqinId[i]+"'  ";

			List it= commonBiz.findSQL2MapList(sql);
			Map p =(Map) it.get(0);
			String ls_employeename=(String)p.get("EMPLOYEENAME");
			String ls_orgid=(String)p.get("ORGID");
			String ls_postname=(String)p.get("POSTNAME");
			
			String sql_check=" select * from tbl_kaoqin,tbl_employee where  tbl_employee.employeename=tbl_kaoqin.employeename and tbl_employee.id='"+kaoqinId[i]+"'";
			List it_check=commonBiz.findSQL2MapList(sql_check);
			
			String fuzhi_id = null;	
			String fuzhi_state = null;
			String fuzhi_employeename=null;
			Date fuzhi_kaoqintime=new Date();
			String fuzhi_orgid = null;
			String fuzhi_postname = null;
			
			for(int j=0;j<it_check.size();j++)
			{	
				Map p_check=(Map)it_check.get(j);
				String check_state=(String)p_check.get("STATE");
				String check_add=(String)p_check.get("ISADD");
				Date check_date=(Date)p_check.get("KAOQIN_TIME");
				
				if(check_state.equalsIgnoreCase("1") && check_date.compareTo(kqTime)==0 && check_add.equalsIgnoreCase("1"))
				{	
					jb="1";
					
					ActionContext.getContext().put("jb", jb);
					return "listAction";
				}else if(check_state.equalsIgnoreCase("1") && check_date.compareTo(kqTime)==0 && check_add.equalsIgnoreCase("0")){
					
					jb="2";
					
					ActionContext.getContext().put("jb", jb);
					
					fuzhi_id=(String)p_check.get("ID");
					fuzhi_employeename=(String)p_check.get("EMPLOYEENAME");
					fuzhi_kaoqintime=(Date)p_check.get("KAOQIN_TIME");
					fuzhi_orgid=(String)p_check.get("ORGID");
					fuzhi_postname=(String)p_check.get("POSTNAME");
					
					String sql_1 = "select count(*) id from tbl_kaoqin";
					List n = (List) commonBiz.findSQL2MapList(sql_1);
					Map number = (Map) n.get(0);
					int num = ((BigDecimal) number.get("ID")).intValue();
					String ls_id=(num+1)+"";
					kaoqin=new Kaoqin();
					kaoqin.setId(fuzhi_id);
					kaoqin.setEmployeeName(fuzhi_employeename);
					kaoqin.setIsadd(1+"");
					kaoqin.setKaoqinTime(fuzhi_kaoqintime);
					kaoqin.setOrgid(fuzhi_orgid);
					kaoqin.setState(1+"");
					kaoqin.setPostname(fuzhi_postname);
					commonBiz.update(kaoqin);
					return "listAction";
					
				}else if( !check_state.equalsIgnoreCase("1") && check_date.compareTo(kqTime)==0 && check_add.equalsIgnoreCase("0")){
					jb="3";
					
					ActionContext.getContext().put("jb", jb);
					return "listAction";
				}else{
					continue;
				}
			
			}
			jb="0";
			ActionContext.getContext().put("jb", jb);
			
			
		    }
	    
	    return "listAction";
	}
	
	
}
