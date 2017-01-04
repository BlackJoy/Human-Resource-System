package com.sys.hr.employplan.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.hr.org.Org;

public interface EmployPlanBIZ {
	/**
	 * 查找招聘计划书
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findEmployPlanByPlanId(String orgId, int pageIndex, int pageSize) throws Exception;
	/**
	 * 招聘计划书管理页面查找招聘计划书
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findEmployPlanMangByPlanId(String orgId, int pageIndex, int pageSize) throws Exception;

	public List findEmployPlanByList(String[] planId);
	public List PlanListName();
	public Boolean addAll(List pList,String orgId) throws Exception;
	/**
	 * 根据orgId级联查询
	 * @param orgId
	 * @return
	 */
	public List findOrg(String orgId);
	public Page findPlanList(List<Org> list,String orgId,int pageIndex,int pageSize);
	public Page findPlanMangList(List<Org> list,String orgId,int pageIndex,int pageSize);
}
