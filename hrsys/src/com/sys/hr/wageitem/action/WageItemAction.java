package com.sys.hr.wageitem.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.wageitem.WageItem;
import com.sys.hr.wageitem.WageTypeRelation;
import com.sys.hr.wageitem.WageTypeRelationId;
import com.sys.hr.zhangtao.ZhangTao;

public class WageItemAction extends ActionSupport {

	private int pageSize = 10;
	private int pageIndex;
	private IBaseBIZ commonBiz;
	private WageItem wageItem;// 存放添加的工资项
	private String ztNo;// 新增工资项属于帐套的编号
	private List<ZhangTao> ztList;// 在增加工资项界面选择帐套
	private String choosezt;//选择要添加到的帐套id
	
	
	

	private String wageId;// 存放选中的工资项id

	public String getWageId() {
		return wageId;
	}

	public void setWageId(String wageId) {
		this.wageId = wageId;
	}

	

	public List<ZhangTao> getZtList() {
		return ztList;
	}

	public void setZtList(List<ZhangTao> ztList) {
		this.ztList = ztList;
	}

	public String getChoosezt() {
		return choosezt;
	}

	public void setChoosezt(String choosezt) {
		this.choosezt = choosezt;
	}

	public String getZtNo() {
		return ztNo;
	}

	public void setZtNo(String ztNo) {
		this.ztNo = ztNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public WageItem getWageItem() {
		return wageItem;
	}

	public void setWageItem(WageItem wageItem) {
		this.wageItem = wageItem;
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
		String sql = "select * from wage_item";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("wageitem_list_page", page);
		return "list";
	}

	/**
	 * 跳转账户增加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {

		ztList = commonBiz.findALl2EntityList("wage_type", ZhangTao.class);
		return "toAdd";
	}

	/**
	 * 增加账户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		ZhangTao zh = (ZhangTao) commonBiz.getEntityById(ztNo, ZhangTao.class);
		zh.getWageItemSet().add(wageItem);
		String ss=wageItem.getWageNo();
		
		
		
		
		//-------------创建工资项表end------------------------

		commonBiz.save(wageItem);
		commonBiz.update(zh);// 维护工资项和帐套的中间表wage_type_relation
		//-------------创建工资项表start------------------------
				String tableName = "code_"+ss;
				commonBiz.createByTableName(tableName);
		return "listAction";
	}

	/**
	 * 删除工资项
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		WageItem wi = (WageItem) commonBiz
				.getEntityById(wageId, WageItem.class);

		return "listAction";
	}
	
	/**
	 * 跳转到编辑界面
	 * @return
	 */

	public String toEdit() {
		ztList = commonBiz.findALl2EntityList("wage_type", ZhangTao.class);//所有的帐套
		List<ZhangTao> list = new ArrayList<ZhangTao>();//用来存放选中的工资项所属于的帐套		
		List<ZhangTao> list2 = new ArrayList<ZhangTao>();//list+list2=ztList	
		wageItem = (WageItem) commonBiz.getEntityById(wageId, WageItem.class);
		String editWageItemId = wageItem.getWageId();
		List<WageTypeRelation> wagetyperelationList=commonBiz.findByCondition("wage_type_relation", "wage_id= '"
				+ editWageItemId+"'", WageTypeRelation.class);
		for(WageTypeRelation wtr : wagetyperelationList){
			String s=wtr.getId().getWageTypeId();//获得工资项对应的帐套
			ZhangTao z=(ZhangTao) commonBiz.getEntityById(s, ZhangTao.class);
			list.add(z);		
		}
		
		
		//确定list2
		for(ZhangTao zt1:ztList){
			boolean flag=false;
			for(ZhangTao zt2:list){
				if((zt1.getWageTypeId().equals(zt2.getWageTypeId()))){
					break;
				}
				flag=true;
			}
			if(flag){
				ZhangTao subZt= (ZhangTao) commonBiz.getEntityById(zt1.getWageTypeId(), ZhangTao.class);
				list2.add(subZt);
			}
		}
		
	
		ActionContext.getContext().put("wageitemztlist", list);
		ActionContext.getContext().put("subztlist", list2);

		return "toEdit";
	}
	
	public String edit() throws Exception{
		ZhangTao z=(ZhangTao) commonBiz.getEntityById(choosezt, ZhangTao.class);
		String id=wageItem.getWageId();
		//wageItem = (WageItem) commonBiz.getEntityById(id, WageItem.class);
		String id2=z.getWageTypeId();
		WageTypeRelation wtr = new WageTypeRelation();
		WageTypeRelationId wtri=new WageTypeRelationId();
		wtri.setWageId(id);
		wtri.setWageTypeId(id2);
		wtr.setId(wtri);
		commonBiz.save(wtr);
		return "listAction";
	}
	
	public String toUpdate(){
		wageItem=(WageItem) commonBiz.getEntityById(wageId, WageItem.class);
		return "toUpdate";
	}
	
	public String update() throws Exception{
		commonBiz.update(wageItem);
		return "listAction";
	}

}
