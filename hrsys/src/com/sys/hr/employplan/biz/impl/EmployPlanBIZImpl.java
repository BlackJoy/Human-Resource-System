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
		for(int i=0;i<pList.size();i++){ //����ѭ��ÿһ������
			EmployPlan p=new EmployPlan();
			List cellist=(ArrayList)pList.get(i);
			p.setOrg(orgId);//����
			p.setStatus(0);//״̬
			String cell0=(String)cellist.get(0);//����
			p.setName(cell0);
			String cell1=(String)cellist.get(1);//����
			p.setNum(Integer.parseInt(cell1));
			String cell2=(String)cellist.get(2);//ְλ
			p.setPosition(cell2);
			String cell3=(String)cellist.get(3);//Ӣ��ˮƽ
			if(cell3=="�ļ�"){
				p.setEnglishDegree("0");
			}else if(cell3=="����"){
				p.setEnglishDegree("1");
			}else{
				p.setEnglishDegree("2");
			}
			String cell4=(String)cellist.get(4);//�Ƿ��ǰ��ѵ
			if(cell4=="��"){
				p.setIsTrain("0");
			}else{
				p.setIsTrain("1");
			}
			String cell5=(String)cellist.get(5);//нˮ
			p.setForSalary(cell5);
			String cell6=(String)cellist.get(6);//Ҫ��
			p.setPositonAsk(cell5);
			String cell7=(String)cellist.get(7);//ʱ��
			Date d=new Date(cell7);
			p.setWwT(d);
			String cell8=(String)cellist.get(8);//רҵ
			p.setMajor(cell8);
			String cell9=(String)cellist.get(9);//�Ա�
			if(cell9.equals("Ů")){
				p.setSex("0");
			}else if(cell9.equals("��")){
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
		titleList.add("��Ƹ�ƻ������");
		titleList.add("��Ƹ����");
		titleList.add("��Ƹרҵ");
		titleList.add("�Ա�");
		titleList.add("Ӣ��ˮƽ");
		titleList.add("�Ƿ��ǰ��ѵ");
		titleList.add("ְλҪ��");
		titleList.add("��нˮƽ");

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
			cell.add(e.getName());//�õ�����
			cell.add(e.getNum());//�õ���Ƹ����
			cell.add(e.getMajor());//�õ���Ƹרҵ
			String sex=e.getSex();//�õ��Ա�
			if(sex=="0"){
				cell.add("Ů");
			}else{
				cell.add("��");
			}
			String degree=e.getEnglishDegree();		//Ӣ��ˮƽ	

			if(degree=="0"){
				cell.add("�ļ�");
			}else if(degree=="1"){
				cell.add("����");
			}else{
				cell.add("��Ҫ��");
			}
			String train=e.getIsTrain();//�Ƿ��ǰ��ѵ
			if(train=="0"){
				cell.add("��");}else{
					cell.add("��");
				}
			
			cell.add(e.getPositonAsk());//ְλҪ��
			
			//��нˮƽ
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
