package com.sys.hr.zhangtao.action;

import java.util.Date;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.org.Org;
import com.sys.hr.org.Post;
import com.sys.hr.zhangtao.ZhangTao;

public class ZhangTaoAction extends ActionSupport {

	private int pageSize = 10;
	private String ztId;
	private int pageIndex;
	private ZhangTao zt;// 存放新增加的帐套
	private IBaseBIZ commonBiz;
	private String orgId;

	private String orgName;// 存放增加帐套选择的部门名字（在toadd（）中赋值）

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getZtId() {
		return ztId;
	}

	public void setZtId(String ztId) {
		this.ztId = ztId;
	}

	public ZhangTao getZt() {
		return zt;
	}

	public void setZt(ZhangTao zt) {
		this.zt = zt;
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
		String sql = "select * from wage_type where dept_no='"
				+ this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("zt_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "list";
	}

	/**
	 * 跳转帐套增加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {

		 System.out.println(orgId);
		Org org=(Org) commonBiz.getEntityById(orgId, Org.class);
		orgName=org.getOrgShortName();
		return "toAdd";
	}

	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		commonBiz.save(zt);
		return "listAction";
	}

	/**
	 * 跳转修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	 public String toUpdate() throws Exception {
	// post=(Post) commonBiz.getEntityById(postId, Post.class);
		 Org org=(Org) commonBiz.getEntityById(orgId, Org.class);
			orgName=org.getOrgShortName();
		 zt=(ZhangTao) commonBiz.getEntityById(ztId, ZhangTao.class);
	    return "toUpdate";
	 }

	/**
	 * 更新帐套
	 * 
	 * @return
	 * @throws Exception
	 */
	 public String update() throws Exception {
	
    Date date = new Date();
    zt.setCreateDat(date);
	zt.setDeptNo(orgId);
	 commonBiz.update(zt);
	 return "listAction";
	 }

	/**
	 * 删除帐套,需要删除该帐套下的所有工资项
	 * 
	 * @return
	 * @throws Exception
	 */
	 public String delete() throws Exception {
	 ZhangTao delzt = (ZhangTao) commonBiz.getEntityById(ztId, ZhangTao.class);
	 commonBiz.delete(delzt);
	 return "listAction";
	 }

}
