package com.sys.hr.socialSecurity.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.socialSecurity.Contribution_Rate;
import com.sys.login.User;
import com.sys.hr.socialSecurity.baoXianItem;

public class ContribRateAction extends ActionSupport {

	private int pageSize = 10;
	private String CId;
	private String template_Id;
	private int pageIndex;

	private Contribution_Rate contribRate;//存放新增加的缴费比例信息
	private List<baoXianItem> bXList;//存放添加时的保险项
	private IBaseBIZ commonBiz;

	

	public List<baoXianItem> getbXList() {
		return bXList;
	}

	public void setbXList(List<baoXianItem> bXList) {
		this.bXList = bXList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCId() {
		return CId;
	}

	public void setCId(String cId) {
		CId = cId;
	}

	public String getTemplate_Id() {
		return template_Id;
	}

	public void setTemplate_Id(String template_Id) {
		this.template_Id = template_Id;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Contribution_Rate getContribRate() {
		return contribRate;
	}

	public void setContribRate(Contribution_Rate contribRate) {
		this.contribRate = contribRate;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public String main() throws Exception {
		return "tomain";
	}

	/**
	 * 缴费比例信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		//String sql = "select * from CONTRIBUTION_RATE where template_Id='" + this.template_Id + "'";
		String sql = "select * from CONTRIBUTION_RATE";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		sql=null;
		
		ActionContext.getContext().put("contribRate_list_page", page);
		ActionContext.getContext().put("template_Id", template_Id);
		return "list";
	}

	/**
	 * 跳转账户增加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {
		bXList = commonBiz.findALl2EntityList("BAOXIANITEM", baoXianItem.class);
		ActionContext.getContext().put("bXList", bXList);
		return "toAdd";
	}

	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		//post.setOrgid(orgId);
		baoXianItem bXItem = (baoXianItem)commonBiz.getEntityById(contribRate.getTemplate_Id(), baoXianItem.class);
		contribRate.setCurItem(bXItem.getCurItem());
		commonBiz.save(contribRate);
		
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		contribRate=(Contribution_Rate) commonBiz.getEntityById(CId, Contribution_Rate.class);
		bXList = commonBiz.findALl2EntityList("BAOXIANITEM", baoXianItem.class);
		ActionContext.getContext().put("bXList", bXList);
		
		return "toUpdate";
	}

	/**
	 * 更新账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	
		//post.setOrgid(orgId);
		baoXianItem bXItem = (baoXianItem)commonBiz.getEntityById(contribRate.getTemplate_Id(), baoXianItem.class);
		contribRate.setCurItem(bXItem.getCurItem());
		commonBiz.update(contribRate);
		return "listAction";
	}

	/**
	 * 删除账号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		Contribution_Rate contribRate = (Contribution_Rate) commonBiz.getEntityById(CId, Contribution_Rate.class);
		commonBiz.delete(contribRate);
		return "listAction";
	}

}
