package com.sys.common.biz;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.sys.approve.ApproveExec;
import com.sys.approve.ApproveFlowExec;
import com.sys.common.Page;
import com.sys.common.dao.IBaseDAO;
import com.sys.hr.employee.Employee;
import com.sys.hr.employplan.EmployPlan;
import com.sys.hr.org.Org;
import com.sys.hr.org.Post;
import com.sys.hr.train.Train;
import com.sys.hr.train.TrainAuth;
import com.sys.hr.train.TrainPlain;
import com.sys.hr.train.TrainScore;
import com.sys.hr.yonggong.TblBorrowin;
import com.sys.hr.yonggong.TblBorrowout;
import com.sys.hr.yonggong.TblNeiborrow;

public class BaseBIZImpl implements IBaseBIZ {

	private IBaseDAO commonDao;

	public IBaseDAO getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(IBaseDAO commonDao) {
		this.commonDao = commonDao;
	}

	public Page findPageBySql_MapList(String sql, int pageIndex, int pageSize)
			throws Exception {

		Page page = commonDao.findPaginationBySQL_MapList(sql, pageIndex,
				pageSize);
		return page;
	}
   
	public void save(Object obj) throws Exception {
		commonDao.save(obj);
	}

	public void update(Object obj) throws Exception {
		commonDao.update(obj);
	}

	public void delete(Object obj) throws Exception {
		commonDao.delete(obj);
	}

	public Page findEntityByCondition(String sql, int pageIndex, int pageSize,
			Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

	public void saveAccount(Object obj) throws Exception {
		// TODO Auto-generated method stub

	}

	public Object getEntityById(String id, Class en) {
		// TODO Auto-generated method stub
		return this.commonDao.getEntityById(id, en);
	}

	public Page findObjectByOrgId_page(String id, int pageIndex, int pageSize,
			String tableName) {
		// TODO Auto-generated method stub

		// --------------------锟斤拷锟斤拷basedao锟斤拷锟斤拷------------------------------
//		List list1 = commonDao.findSQL2EntityList(
//				"select * from tbl_post", Post.class);
//		List list2=commonDao.findSQL2MapList("select * from tbl_post");
//		Post post=(Post)commonDao.getEntityById("aaa", Post.class);
//		Post post2=(Post) commonDao.findById(Post.class, "aaa");
//		Post post3=(Post) commonDao.findSQL2UniqueEntity("select * from tbl_post where id='aaa'", Post.class);
//		Page page1=commonDao.findPaginationByHQL_EntityList("from Employee e", 1, 2);
//		Page page2=commonDao.findPaginationBySQL_MapList("select * from tbl_employee", 1, 2);
//		System.out.println();
		// --------------------锟斤拷锟斤拷basedao锟斤拷锟斤拷------------------------------


		if (id == null || id.equals("")) {
			String sql = "select o.* from TBL_Org o where o.orgparentId is null";
			Org org = (Org) commonDao.findSQL2UniqueEntity(sql, Org.class);
			id = org.getId();
		}

		Page page = commonDao.findObjectByOrgId_page(id, pageIndex, pageSize,
				tableName);
		return page;
	}

	public List findALl2EntityList(String tableName,Class clz) {
		// TODO Auto-generated method stub
		String sql = "select * from "+ tableName;
		List list =commonDao.findSQL2EntityList(sql, clz);
		return list;
	}

	public List findByCondition(String tableName, String condition, Class clz) {
		// TODO Auto-generated method stub
		String sql = "select * from "+ tableName+" where "+condition;
		List list =commonDao.findSQL2EntityList(sql, clz);
		return list;
	}

	public void createByTableName(String tableName) throws Exception {
		// TODO Auto-generated method stub
		//create table hrsys.test1(id varchar(100),salary number,wagetime varchar2(10), PRIMARY KEY (ID, WAGETIME))
		String sql = "create table hrsys."+tableName+"(id varchar(100),salary number,wagetime varchar2(10), PRIMARY KEY (ID, WAGETIME))";
		commonDao.createBySql(sql);
		
	}

	public void insertIntoWageItem(String tableName, String id, double value)
			throws Exception {
		// TODO Auto-generated method stub
		//insert into testInsert values("dfsaf",1000)
		commonDao.executeByJdbcSql(tableName,id,value);
		
	}

	public List findSQL2MapList(String sql) throws Exception {
		// TODO Auto-generated method stub
		List list=commonDao.findSQL2MapList(sql);
		return list;
	}

	public void saveOrUpdate(Object obj) throws Exception {
		// TODO Auto-generated method stub
		commonDao.saveOrUpdate(obj);
	}

	public void saveFlowEntityById(String id,String status) {
		// TODO Auto-generated method stub
		ApproveFlowExec afe = (ApproveFlowExec) commonDao.findById(ApproveFlowExec.class, id);
		String execId = afe.getApproveExecId();
		ApproveExec ae = (ApproveExec)commonDao.findById(ApproveExec.class, execId);
		String contentId = ae.getContentID();//其他表的主键ID
		String table = ae.getContentURL().substring(0, 7);//判断属于哪一张表
		//审批成功
		if(status.trim().equals("3")){		
			if(table.trim().equals("train_s"))
			{//插入TrainPlain
				Train train = (Train) commonDao.findById(Train.class, contentId);
				TrainPlain trainPlain = new TrainPlain();
				
				trainPlain.setApplicantCode(train.getApplicantCode());
				trainPlain.setApplicantName(train.getApplicantName());
				trainPlain.setId(train.getId());
				trainPlain.setOrgCode(train.getOrgCode());
				trainPlain.setOrgFullName(train.getOrgFullName());
				trainPlain.setOrgId(train.getOrgId());
				trainPlain.setTrainCode(train.getTrainCode());
				trainPlain.setTrainDetail(train.getTrainDetail());
				trainPlain.setTrainEnd(train.getTrainEnd());
				trainPlain.setTrainManner(train.getTrainManner());
				trainPlain.setTrainObject(train.getTrainObject());
				trainPlain.setTrainPrice(train.getTrainPrice());
				trainPlain.setTrainRemarks(train.getTrainRemarks());
				trainPlain.setTrainStart(train.getTrainStart());
				trainPlain.setTrainStatus(train.getTrainStatus());
				trainPlain.setTrainSubject(train.getTrainSubject());
				trainPlain.setTrainType(train.getTrainType());
				train.setTrainStatus(status);
				trainPlain.setTrainStatus(status);

				commonDao.update(train);
				commonDao.save(trainPlain);
			//	train
			//	trainPlain.getOrgId();
				TrainAuth auth = new TrainAuth();
				auth.setTrainId(trainPlain.getId());
				auth.setStatus("0");
				auth.setTrainName(trainPlain.getTrainSubject());
				//人力资源 
				auth.setOrgId("6"); 
				auth.setWhichIdea("4");
				commonDao.save(auth);
				//实作部分 
				auth = new TrainAuth();
				auth.setTrainId(trainPlain.getId());
				auth.setStatus("0");
				auth.setTrainName(trainPlain.getTrainSubject());
				auth.setOrgId("15");
				auth.setWhichIdea("1");
				commonDao.save(auth);
				//实作
				auth = new TrainAuth();
				auth.setTrainId(trainPlain.getId());
				auth.setStatus("0");
				auth.setTrainName(trainPlain.getTrainSubject());				
				auth.setOrgId("15");
				auth.setWhichIdea("2");
				commonDao.save(auth);
				//现岗位
				auth = new TrainAuth();
				auth.setTrainId(trainPlain.getId());
				auth.setStatus("0");
				auth.setTrainName(trainPlain.getTrainSubject());
				auth.setOrgId(trainPlain.getOrgId());
				auth.setWhichIdea("3");
				commonDao.save(auth);
				//主管领导
				auth = new TrainAuth();
				auth.setTrainId(trainPlain.getId());
				auth.setStatus("0");
				auth.setTrainName(trainPlain.getTrainSubject());
				auth.setOrgId("3");
				auth.setWhichIdea("5");
				commonDao.save(auth);
			}else if(table.trim().equals("employP")){
				//锟睫革拷employPlan
				EmployPlan ep=(EmployPlan)commonDao.findById(EmployPlan.class, contentId);
				ep.setStatus(3);
				commonDao.update(ep);
			}else if(table.trim().equals("employ_")){
				Employee emp=(Employee)commonDao.findById(Employee.class, contentId);
				emp.setEmployeeStatus(5);
				commonDao.update(emp);
			}
		}else{//锟斤拷锟斤拷状锟斤拷----未通锟斤拷
			if(table.trim().equals("train_s"))
			{//更改Train
				if(table.trim().equals("train_s") && !status.trim().equals("2") && !status.trim().equals("3")){
				Train train = (Train) commonDao.findById(Train.class, contentId);
				train.setTrainStatus(status);
				commonDao.update(train);
				}
			}else if(table.trim().equals("employP")){
				//锟斤拷锟紼mployPaln
				EmployPlan ep=(EmployPlan)commonDao.findById(EmployPlan.class, contentId);
				ep.setStatus(4);
				commonDao.update(ep);
			}else if(table.trim().equals("employ_")){
//				Employee emp=(Employee)commonDao.findById(Employee.class, contentId);
//				emp.setEmployeeStatus(6);
			}
		}
		
	}

	public Object findBorrowInById(Serializable id) {
		// TODO Auto-generated method stub
		return commonDao.findById(TblBorrowin.class, id);
	}

	public Object findBorrowOutById(Serializable id) {
		// TODO Auto-generated method stub
		return commonDao.findById(TblBorrowout.class, id);
	}
	 public Object findNeiBorrowById(Serializable id)
	    {   	
	    	return commonDao.findById(TblNeiborrow.class, id);
	    }

	public void excuteByDmlSql(String sql) {
		// TODO Auto-generated method stub
		commonDao.executeByDmlSql(sql);
	}
//------------齐建鹏----------------start
	

	public List getEmployeeByTrainId(String trainPlainId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select e.id, e.employeecode||'-'||e.employeename empname" +
					 " from Tbl_Employee e inner join Tbl_Train_Score tr" +
					 " on e.id=tr.employeeid where tr.trainid='"+trainPlainId+"'";
		return commonDao.findSQL2MapList(sql);
	}

	public void saveOrUpdateScoreList(String trainId, List<TrainScore> scoreList) {
		String sql = "delete from Tbl_Train_Score tc where tc.trainid='"+trainId+"'";
		commonDao.deleteSQL(sql);
		for (Iterator it = scoreList.iterator(); it.hasNext();) {
			TrainScore ts = (TrainScore) it.next();
			commonDao.save(ts);
		}
		
	}

	public void deleteScoresByTrainId(String trainId) {
		String sql = "delete from  Tbl_Train_Score where trainId='"+trainId+"'";
		commonDao.deleteSQL(sql);		
	}

	public int deleteSQL(String sql) {
		// TODO Auto-generated method stub
		return commonDao.deleteSQL(sql);
	}

	public Page findPaginationBySQL_MapList(String sql, int pageIndex,
			int pageSize) throws HibernateException {
		// TODO Auto-generated method stub 
		return commonDao.findPaginationByHQL_EntityList(sql, pageIndex, pageSize);
	}

	public Object findSQL2UniqueEntity(String sql, Class clz) {
		// TODO Auto-generated method stub
		return commonDao.findSQL2UniqueEntity(sql, clz);
	}

	public List findSQL2EntityList(String sql, Class clz)
			throws HibernateException {
		// TODO Auto-generated method stub
		return commonDao.findSQL2EntityList(sql, clz);
	}
	//------------齐建鹏-----------------end
}
