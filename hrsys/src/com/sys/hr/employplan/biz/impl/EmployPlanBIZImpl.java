package com.sys.hr.employplan.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;
import com.sys.hr.employplan.EmployPlan;
import com.sys.hr.employplan.biz.EmployPlanBIZ;
import com.sys.hr.employplan.dao.EmployPlanDao;
import com.sys.hr.org.Org;

public class EmployPlanBIZImpl implements EmployPlanBIZ{
	private EmployPlanDao planDao;
	private IBaseDAO commonDao;
 
	public Boolean addAll(List pList,String orgId) throws Exception{
		if(pList==null||pList.size()==0){
			return false;
		}
		for(int i=0;i<pList.size();i++){ //遍历循环每一行内容
			EmployPlan p=new EmployPlan();
			List cellist=(ArrayList)pList.get(i);
			p.setOrg(orgId);//部门
			p.setStatus(0);//状态
			String cell0=(String)cellist.get(0);//标题
			p.setName(cell0);
			String cell1=(String)cellist.get(1);//人数
			p.setNum(Integer.parseInt(cell1));
			String cell2=(String)cellist.get(2);//职位
			p.setPosition(cell2);
			String cell3=(String)cellist.get(3);//英语水平
			if(cell3=="四级"){
				p.setEnglishDegree("0");
			}else if(cell3=="六级"){
				p.setEnglishDegree("1");
			}else{
				p.setEnglishDegree("2");
			}
			String cell4=(String)cellist.get(4);//是否岗前培训
			if(cell4=="是"){
				p.setIsTrain("0");
			}else{
				p.setIsTrain("1");
			}
			String cell5=(String)cellist.get(5);//薪水
			p.setForSalary(cell5);
			String cell6=(String)cellist.get(6);//要求
			p.setPositonAsk(cell5);
			String cell7=(String)cellist.get(7);//时间
			Date d=new Date(cell7);
			p.setWwT(d);
			String cell8=(String)cellist.get(8);//专业
			p.setMajor(cell8);
			String cell9=(String)cellist.get(9);//性别
			if(cell9.equals("女")){
				p.setSex("0");
			}else if(cell9.equals("男")){
				p.setSex("1");
			}else{
				p.setSex("2");
			}
			
			commonDao.save(p);
			
		}
		return true;
	};
	public List PlanListName(){
		List<String> titleList=new ArrayList<String>();
		titleList.add("招聘计划书标题");
		titleList.add("招聘人数");
		titleList.add("招聘专业");
		titleList.add("性别");
		titleList.add("英语水平");
		titleList.add("是否岗前培训");
		titleList.add("职位要求");
		titleList.add("资薪水平");

		return titleList;
	};
	public Page findEmployPlanByPlanId(String orgId, int pageIndex, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub

		return planDao.findEmployPlanByOrgId(orgId, pageIndex, pageSize);
	}

	public EmployPlanDao getPlanDao() {
		return planDao;
	}

	public void setPlanDao(EmployPlanDao planDao) {
		this.planDao = planDao;
	}

	public Page findEmployPlanMangByPlanId(String orgId, int pageIndex,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return planDao.findEmployPlanMangByOrgId(orgId, pageIndex, pageSize);
	}

	public List findEmployPlanByList(String[] planId) {
		// TODO Auto-generated method stub
		List planList=new ArrayList();
		for(String str:planId){
			EmployPlan e=(EmployPlan) commonDao.findById(EmployPlan.class, str);
			List cell=new ArrayList();
			cell.add(e.getName());//得到名字
			cell.add(e.getNum());//得到招聘人数
			cell.add(e.getMajor());//得到招聘专业
			String sex=e.getSex();//得到性别
			if(sex=="0"){
				cell.add("女");
			}else{
				cell.add("男");
			}
			String degree=e.getEnglishDegree();		//英语水平	

			if(degree=="0"){
				cell.add("四级");
			}else if(degree=="1"){
				cell.add("六级");
			}else{
				cell.add("无要求");
			}
			String train=e.getIsTrain();//是否岗前培训
			if(train=="0"){
				cell.add("是");}else{
					cell.add("否");
				}
			
			cell.add(e.getPositonAsk());//职位要求
			
			//资薪水平
			cell.add(e.getForSalary());
			planList.add(cell);
		}
		return planList;
	}

	public IBaseDAO getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(IBaseDAO commonDao) {
		this.commonDao = commonDao;
	}
	public List findOrg(String orgId) {
		// TODO Auto-generated method stub
		return commonDao.findSQL2EntityList("select * from tbl_org start with id = '"+orgId+"' connect by prior id = orgparentid", Org.class);
	}
	public Page findPlanList(List<Org> list,String orgId,int pageIndex,int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder child_id=new StringBuilder();
		child_id.append("(");
		for(Org org:list){
			child_id.append("'");
			child_id.append(org.getId());
			child_id.append("'");
			child_id.append(",");
		}
		child_id.append("'");
		child_id.append(orgId);
		child_id.append("'");
		child_id.append(")");
		System.out.println(child_id);
		
		String sql="select * from TBL_EMPLOYPLAN where org in "+child_id.toString()+" and status=0";
		return commonDao.findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	
	}
	public Page findPlanMangList(List<Org> list, String orgId, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder child_id=new StringBuilder();
		child_id.append("(");
		for(Org org:list){
			child_id.append("'");
			child_id.append(org.getId());
			child_id.append("'");
			child_id.append(",");
		}
		child_id.append("'");
		child_id.append(orgId);
		child_id.append("'");
		child_id.append(")");
		System.out.println(child_id);
		
		String sql="select * from TBL_EMPLOYPLAN where org in "+child_id.toString()+" and status in(1,2,3)";
		return commonDao.findPaginationBySQL_MapList(sql, pageIndex, pageSize);
	}

	

}
