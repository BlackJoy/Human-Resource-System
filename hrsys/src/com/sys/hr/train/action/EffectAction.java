package com.sys.hr.train.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.employee.Employee;
import com.sys.hr.train.TrainAuth;
import com.sys.hr.train.TrainPlain;
import com.sys.hr.train.TrainScore;
import com.sys.role.Role;
import com.sys.role.RoleEmployee;

public class EffectAction {
	private int pageSize = 10;
	private int pageIndex;
	private IBaseBIZ commonBiz;
	private Employee emp;
	private Role role;
	private TrainAuth trainAuth;
	private TrainPlain trainPlain;
	private String trainId;
	private String whichIdea;
	private String idea;
	private String empsScore;
	private String scoreId;
	private String evaluate;
	private TrainScore trainScore;
	
	private String analyseDesc;
	//查询某一段分数
	private double higherScore;
	private double lowerScore;
	/**
	 *效果评价项目列表
	 *
	 */
	
	public String effectList() throws Exception{
		
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
		String empOrgId = emp.getOrgid();
		String hql = "from TrainPlain where trainStatus=7";
		Page page = commonBiz.findPaginationBySQL_MapList(hql, pageIndex, pageSize);
		
		 ActionContext.getContext().put("effect_list_page", page);
		
		 ActionContext.getContext().put("empOrgId", empOrgId);
		
		return "effectlist";
	}
	public String toEvaluate(){
		 TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
		 trainPlain = tp;
		 
		emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
		return "toEvaluate";
	}
	/**
	 * 评价
	 * @return
	 * @throws Exception 
	 */
  public String doEvaluate() throws Exception{
	  //trainId
	
	  if(whichIdea != null && !whichIdea.trim().equals(""))
	  {
		  emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
		  String sql = "select * from TBL_TRAIN_VALUE where trainId='"+trainId+"' and whichIdea='"+whichIdea+"'";
		  TrainAuth ta =(TrainAuth)commonBiz.findSQL2UniqueEntity(sql, TrainAuth.class);
		  ta.setStatus("1");
		  commonBiz.update(ta);
		  TrainPlain tp = (TrainPlain) commonBiz.getEntityById(trainId, TrainPlain.class);
		  //1 理论 2 实作 3现岗位 4人力资源 5 主管领导
		  if(whichIdea.equals("1")){
			  tp.setTheoryEval(idea);
			  tp.setTeEmplyeeCode(emp.getEmployeeCode());
			  tp.setTeEmplyeeName(emp.getEmployeeName());
			  tp.setTeTime(new Date());
		  }else if(whichIdea.equals("2")){
			  tp.setPracticeEval(idea);
			  tp.setPeEmplyeeCode(emp.getEmployeeCode());
			  tp.setPeEmplyeeName(emp.getEmployeeName());
			  tp.setPeTime(new Date());
		  }else if(whichIdea.equals("3")){
			  tp.setCurPostIdea(idea);
			  tp.setCurPostIdeaTime(new Date());
			  tp.setCurPostManagerCode(emp.getEmployeeCode());
			  tp.setCurPostManagerName(emp.getEmployeeName());
		  }else if(whichIdea.equals("4")){
			  tp.setHrPostManagerCode(emp.getEmployeeCode());
			  tp.setHrPostIdea(idea);
			  tp.setHrPostIdeaTime(new Date());
		  }else if(whichIdea.equals("5")){
			  tp.setManagerCode(emp.getEmployeeCode());
			  tp.setManagerIdea(idea);
			  tp.setManagerIdeaTime(new Date());
		  }
		  commonBiz.update(tp);
		  ActionContext.getContext().put("EvalMsg", "评价成功");
		  
	  }
	 
	  return "effectListAction";
  }
  /**
   * 查看评价详情
   * @return
   */
  public String toViewEval(){
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  trainPlain = tp;
	  return "toViewEval";
  }
  /**
   * 跳转到培训人员输入成绩页面
   * @return
   */
  public String toInputEmpScore(){
	  if (pageIndex < 1) {
			pageIndex = 1;
		}
	  String hql = "from TrainScore where trainid = '"+trainId+"'";
	  Page empScorePage = commonBiz.findPaginationBySQL_MapList(hql, pageIndex, pageSize);
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  String trainPlainName = tp.getTrainSubject();
	  ActionContext.getContext().put("empScorePage", empScorePage);
	  ActionContext.getContext().put("trainPlainName", trainPlainName);
	  
	  return "toInputEmpScore";
  }
  /**
   * 保存学员成绩，并更新记录
   * @return
   * @throws Exception
   */
  public String saveEmpScores() throws Exception{
	  String empScoreArray ;
	  if(empsScore != null && !empsScore.trim().equals(""))
	  {
		  empScoreArray =  empsScore.substring(0, empsScore.length()-1);
		  String[] array = empScoreArray.split("[,]");
		  //对每条成绩进行遍历，并更新数据库
		  for(String es : array){
			  String[] empSc = es.split("[:]");
			  String employeeId = empSc[0];
			  String score = empSc[1];
			  TrainScore trainSc = (TrainScore) commonBiz.getEntityById(employeeId, TrainScore.class);
			  trainSc.setEmpscore(Double.valueOf(score));
			  commonBiz.update(trainSc);
		  }		  
	  }
	  return "effectListAction";
  }
  /**
   * 结束项目
   * @return
   * @throws Exception
   */
  public String endTrainPlain() throws Exception{
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  if(tp.getTrainStatus().equals("7"))
	  {
		  tp.setTrainStatus("8");
		  commonBiz.update(tp);
	      ActionContext.getContext().put("msg", "结束项目成功！");
	  }else
		  ActionContext.getContext().put("msg", "还未评价完毕，不能停止！");
	  return "effectListAction";
  }
  
  /**
   * 转入评价状态
   * @return
 * @throws Exception 
   */
  public String toEvalateStatus() throws Exception{
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  tp.setTrainStatus("7");
	  commonBiz.update(tp);
	  ActionContext.getContext().put("msg", "转入项目评价状态成功！");
	return "effectListAction";
  }
  /**
   * 个人培训项目评价
   */
  public String toIndividualEnval(){
	  if (pageIndex < 1) {
			pageIndex = 1;
	  }
	  emp =  (Employee) ActionContext.getContext().getSession().get("curr_employee");
	  String hql = "from TrainScore where employeeid = '"+emp.getId()+"'";
	  Page page = commonBiz.findPaginationBySQL_MapList(hql, pageIndex, pageSize);
	  if(page.getList()!=null && page.getList().size() >0)
	  ActionContext.getContext().put("person_plain_page_list", page);
	  else
		  ActionContext.getContext().put("msg", "暂无培训信息！");
	  return "toIndividualEnval";	  
  }  
  /**
   * 跳转至个人评价填写页面
   * @return
   */
  public String toIndivEnvalPage(){
	  trainScore = (TrainScore) commonBiz.getEntityById(scoreId, TrainScore.class);
	  if(trainScore == null){
		  ActionContext.getContext().put("msg", "暂无此信息！");
		  return "toIndividualEnval";	
	  }
	  return "inDivEnvalPage";
  }
/**
 * 保存个人体会
 * @return
 * @throws Exception
 */
  public String doIndividualEnval() throws Exception{
	  trainScore = (TrainScore) commonBiz.getEntityById(scoreId, TrainScore.class);
	  if(trainScore.getStatus().equals("0")){
		  trainScore.setEvaluate(evaluate);
		  trainScore.setStatus("2");
	  }else if(trainScore.getStatus().equals("1")){
		  trainScore.setEvaluate(evaluate);
		  trainScore.setStatus("3");
	  }	 
	  commonBiz.update(trainScore);
	  return "toIndividualEnvalAction";
  }
  
  public String toResultPage() throws Exception{
	  
	  
	  
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  trainPlain = tp;
	  String maxScoreSql = "select max(empscore) maxscore from tbl_train_score where trainId='"+
	  trainId+"'";
	  String maxScore =  ((Map)((Page)commonBiz.findPageBySql_MapList(maxScoreSql, 1, 1)).getList().get(0)).get("MAXSCORE").toString();
	  String minScoreSql = "select min(empscore) minScore from tbl_train_score where trainId='"+
			  trainId+"'";
	  String minScore = ((Map)((Page)commonBiz.findPageBySql_MapList(minScoreSql, 1, 1)).getList().get(0)).get("MINSCORE").toString();
			  
	  String avageScoreSql =  "select avg(empscore) avgscore from tbl_train_score where trainId='"+
			  trainId+"'";
	  String avgScore = ((Map)((Page)commonBiz.findPageBySql_MapList(avageScoreSql, 1, 1)).getList().get(0)).get("AVGSCORE").toString();
	  String scoreListSql = "select * from tbl_train_score where trainId='"+trainId+"'";
	  List scoreList = commonBiz.findSQL2EntityList(scoreListSql, TrainScore.class);
	  //将最高分最低分平均分放入request 并在页面显示
	  String empCountsSql =  "select count(*) empcount from tbl_train_score where trainId='"+ trainId+"'";
      String empCounts = ((Map)((Page)commonBiz.findPageBySql_MapList(empCountsSql, 1, 1)).getList().get(0)).get("EMPCOUNT").toString();
     
     analyseDesc = " 最高分: "+maxScore+", 最低分: "+minScore+", 平均分: "+avgScore;
	  ActionContext.getContext().put("empCounts", empCounts);
	  ActionContext.getContext().put("scoreList", scoreList);
	  
	  //生成成绩分析图
	  //放入request并在页面显示
	  
	  //页面显示具体培训信息评价
	  
	  
	  return "toresultpage";
  }
  
  public String analyseScore() throws Exception{
	  TrainPlain tp= (TrainPlain)commonBiz.getEntityById(trainId, TrainPlain.class);
	  trainPlain = tp;
	  //得到输入分数段
	  String sql = "select count(*) EMPCOUNT from tbl_train_score t where empscore between "+lowerScore+ " and "+higherScore;
	  //获取该分数段的人数 及平均分 最高分最低分
	  //画图
	  String empCounts = ((Map)((Page)commonBiz.findPageBySql_MapList(sql, 1, 1)).getList().get(0)).get("EMPCOUNT").toString();
	  analyseDesc=analyseDesc+","+lowerScore+" 到 "+higherScore+" 分数段人数" +empCounts+" 人 ";
	  String scoreListSql = "select * from tbl_train_score where trainId='"+trainId+"'";
	  List scoreList = commonBiz.findSQL2EntityList(scoreListSql, TrainScore.class);
	  ActionContext.getContext().put("scoreList", scoreList);
	  
	  return "toresultpage";
  }
  
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public TrainAuth getTrainAuth() {
		return trainAuth;
	}

	public void setTrainAuth(TrainAuth trainAuth) {
		this.trainAuth = trainAuth;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public TrainPlain getTrainPlain() {
		return trainPlain;
	}
	public void setTrainPlain(TrainPlain trainPlain) {
		this.trainPlain = trainPlain;
	}
	public String getWhichIdea() {
		return whichIdea;
	}
	public void setWhichIdea(String whichIdea) {
		this.whichIdea = whichIdea;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getEmpsScore() {
		return empsScore;
	}
	public void setEmpsScore(String empsScore) {
		this.empsScore = empsScore;
	}
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public TrainScore getTrainScore() {
		return trainScore;
	}
	public void setTrainScore(TrainScore trainScore) {
		this.trainScore = trainScore;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getAnalyseDesc() {
		return analyseDesc;
	}
	public void setAnalyseDesc(String analyseDesc) {
		this.analyseDesc = analyseDesc;
	}
	public double getHigherScore() {
		return higherScore;
	}
	public void setHigherScore(double higherScore) {
		this.higherScore = higherScore;
	}
	public double getLowerScore() {
		return lowerScore;
	}
	public void setLowerScore(double lowerScore) {
		this.lowerScore = lowerScore;
	}
    
}
