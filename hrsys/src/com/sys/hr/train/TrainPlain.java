package com.sys.hr.train;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrainPlain {
	private String id;
	private String trainCode;//培训编号
	private String trainType;//培训类型
	private String trainSubject;//培训项目
	private String trainObject;//培训对象
	private String orgCode;//组织编码
	private String orgFullName;//组织名称
	private Date trainStart;//培训开始时间
	private Date trainEnd;//培训结束时间
	private double trainPrice;//培训价格
	private String trainManner;//培训方式
	private String trainRemarks;//培训备注
	private String trainStatus;//培训状态
	private String applicantName;//主管姓名
	private String applicantCode;//主管编号
	private String trainDetail;//培训内容
	private String trainPurpose;//培训目的
    private String orgId;
	
	private String theoryEval;//理论部分评价
	private Date teTime;	//理论部分评价时间
	private String teEmplyeeCode;//理论部分评价员编号
	private String teEmplyeeName; //理论部分评价员姓名

	private String practiceEval;//实作部分评价
    private String peEmplyeeName;//实作部分评价员姓名
	private Date peTime;//实作部分评价时间
	private String peEmplyeeCode;//实作部分评价员编号
	
	private String curPostIdea;//现岗位意见
    private String curPostManagerCode;//现岗位负责任编号
    private Date curPostIdeaTime;//现岗位评论时间
    
	private String hrPostManagerCode;//人力资源主管编号
    private String hrPostIdea;//人力资源部门意见
    private Date hrPostIdeaTime;//人力资源部门意见时间
    
    private String managerCode;//主管领导编码
    private String managerIdea;//主管领导意见
    private Date managerIdeaTime;//主管领导评论时间
    
      
	private String curPostManagerName;
	
	private Set<TrainAuth> authSet = new HashSet<TrainAuth>(); 
	
    private List<TrainScore> trainScoreList = new ArrayList<TrainScore>();
    public TrainPlain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainPlain(String id, String trainCode, String trainType,
			String trainSubject, String trainObject, String orgCode,
			String orgFullName, Date trainStart, Date trainEnd,
			double trainPrice, String trainManner, String trainRemarks,
			String trainStatus, String applicantName, String applicantCode,
			String trainDetail, String trainPurpose, String theoryEval,
			Date teTime, String teEmplyeeCode, String practiceEval,
			Date peTime, String peEmplyeeCode, String curPostIdea,
			String curPostManagerCode, Date curPostIdeaTime,
			String hrPostManagerCode, String hrPostIdea, Date hrPostIdeaTime,
			String managerCode, String managerIdea, Date managerIdeaTime,
			String teEmplyeeName, String curPostManagerName,
			String peEmplyeeName) {
		super();
		this.id = id;
		this.trainCode = trainCode;
		this.trainType = trainType;
		this.trainSubject = trainSubject;
		this.trainObject = trainObject;
		this.orgCode = orgCode;
		this.orgFullName = orgFullName;
		this.trainStart = trainStart;
		this.trainEnd = trainEnd;
		this.trainPrice = trainPrice;
		this.trainManner = trainManner;
		this.trainRemarks = trainRemarks;
		this.trainStatus = trainStatus;
		this.applicantName = applicantName;
		this.applicantCode = applicantCode;
		this.trainDetail = trainDetail;
		this.trainPurpose = trainPurpose;
		this.theoryEval = theoryEval;
		this.teTime = teTime;
		this.teEmplyeeCode = teEmplyeeCode;
		this.practiceEval = practiceEval;
		this.peTime = peTime;
		this.peEmplyeeCode = peEmplyeeCode;
		this.curPostIdea = curPostIdea;
		this.curPostManagerCode = curPostManagerCode;
		this.curPostIdeaTime = curPostIdeaTime;
		this.hrPostManagerCode = hrPostManagerCode;
		this.hrPostIdea = hrPostIdea;
		this.hrPostIdeaTime = hrPostIdeaTime;
		this.managerCode = managerCode;
		this.managerIdea = managerIdea;
		this.managerIdeaTime = managerIdeaTime;
		this.teEmplyeeName = teEmplyeeName;
		this.curPostManagerName = curPostManagerName;
		this.peEmplyeeName = peEmplyeeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrainCode() {
		return trainCode;
	}
	public void setTrainCode(String trainCode) {
		this.trainCode = trainCode;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getTrainSubject() {
		return trainSubject;
	}
	public void setTrainSubject(String trainSubject) {
		this.trainSubject = trainSubject;
	}
	public String getTrainObject() {
		return trainObject;
	}
	public void setTrainObject(String trainObject) {
		this.trainObject = trainObject;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgFullName() {
		return orgFullName;
	}
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}
	public Date getTrainStart() {
		return trainStart;
	}
	public void setTrainStart(Date trainStart) {
		this.trainStart = trainStart;
	}
	public Date getTrainEnd() {
		return trainEnd;
	}
	public void setTrainEnd(Date trainEnd) {
		this.trainEnd = trainEnd;
	}
	public double getTrainPrice() {
		return trainPrice;
	}
	public void setTrainPrice(double trainPrice) {
		this.trainPrice = trainPrice;
	}
	public String getTrainManner() {
		return trainManner;
	}
	public void setTrainManner(String trainManner) {
		this.trainManner = trainManner;
	}
	public String getTrainRemarks() {
		return trainRemarks;
	}
	public void setTrainRemarks(String trainRemarks) {
		this.trainRemarks = trainRemarks;
	}
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getApplicantCode() {
		return applicantCode;
	}
	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	public String getTrainDetail() {
		return trainDetail;
	}
	public void setTrainDetail(String trainDetail) {
		this.trainDetail = trainDetail;
	}
	public String getTrainPurpose() {
		return trainPurpose;
	}
	public void setTrainPurpose(String trainPurpose) {
		this.trainPurpose = trainPurpose;
	}
	public String getTheoryEval() {
		return theoryEval;
	}
	public void setTheoryEval(String theoryEval) {
		this.theoryEval = theoryEval;
	}
	public Date getTeTime() {
		return teTime;
	}
	public void setTeTime(Date teTime) {
		this.teTime = teTime;
	}
	public String getTeEmplyeeCode() {
		return teEmplyeeCode;
	}
	public void setTeEmplyeeCode(String teEmplyeeCode) {
		this.teEmplyeeCode = teEmplyeeCode;
	}
	public String getPracticeEval() {
		return practiceEval;
	}
	public void setPracticeEval(String practiceEval) {
		this.practiceEval = practiceEval;
	}
	public Date getPeTime() {
		return peTime;
	}
	public void setPeTime(Date peTime) {
		this.peTime = peTime;
	}
	public String getPeEmplyeeCode() {
		return peEmplyeeCode;
	}
	public void setPeEmplyeeCode(String peEmplyeeCode) {
		this.peEmplyeeCode = peEmplyeeCode;
	}
	public String getCurPostIdea() {
		return curPostIdea;
	}
	public void setCurPostIdea(String curPostIdea) {
		this.curPostIdea = curPostIdea;
	}
	public String getCurPostManagerCode() {
		return curPostManagerCode;
	}
	public void setCurPostManagerCode(String curPostManagerCode) {
		this.curPostManagerCode = curPostManagerCode;
	}
	public Date getCurPostIdeaTime() {
		return curPostIdeaTime;
	}
	public void setCurPostIdeaTime(Date curPostIdeaTime) {
		this.curPostIdeaTime = curPostIdeaTime;
	}
	public String getHrPostManagerCode() {
		return hrPostManagerCode;
	}
	public void setHrPostManagerCode(String hrPostManagerCode) {
		this.hrPostManagerCode = hrPostManagerCode;
	}
	public String getHrPostIdea() {
		return hrPostIdea;
	}
	public void setHrPostIdea(String hrPostIdea) {
		this.hrPostIdea = hrPostIdea;
	}
	public Date getHrPostIdeaTime() {
		return hrPostIdeaTime;
	}
	public void setHrPostIdeaTime(Date hrPostIdeaTime) {
		this.hrPostIdeaTime = hrPostIdeaTime;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerIdea() {
		return managerIdea;
	}
	public void setManagerIdea(String managerIdea) {
		this.managerIdea = managerIdea;
	}
	public Date getManagerIdeaTime() {
		return managerIdeaTime;
	}
	public void setManagerIdeaTime(Date managerIdeaTime) {
		this.managerIdeaTime = managerIdeaTime;
	}
	public String getTeEmplyeeName() {
		return teEmplyeeName;
	}
	public void setTeEmplyeeName(String teEmplyeeName) {
		this.teEmplyeeName = teEmplyeeName;
	}
	public String getCurPostManagerName() {
		return curPostManagerName;
	}
	public void setCurPostManagerName(String curPostManagerName) {
		this.curPostManagerName = curPostManagerName;
	}
	public String getPeEmplyeeName() {
		return peEmplyeeName;
	}
	public void setPeEmplyeeName(String peEmplyeeName) {
		this.peEmplyeeName = peEmplyeeName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public List<TrainScore> getTrainScoreList() {
		return trainScoreList;
	}
	public void setTrainScoreList(List<TrainScore> trainScoreList) {
		this.trainScoreList = trainScoreList;
	}
	public Set getAuthSet() {
		return authSet;
	}
	public void setAuthSet(Set<TrainAuth> authSet) {
		this.authSet = authSet;
	}
	
    
}
