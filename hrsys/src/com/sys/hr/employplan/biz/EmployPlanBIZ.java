package com.sys.hr.employplan.biz;

import java.util.List;

import com.sys.common.Page;
import com.sys.hr.org.Org;

public interface EmployPlanBIZ {
	/**
	 * ������Ƹ�ƻ���
	 * @param orgId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page findEmployPlanByPlanId(String orgId, int pageIndex, int pageSize) throws Exception;
	/**
	 * ��Ƹ�ƻ������ҳ�������Ƹ�ƻ���
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
	 * ����orgId������ѯ
	 * @param orgId
	 * @return
	 */
	public List findOrg(String orgId);
	public Page findPlanList(List<Org> list,String orgId,int pageIndex,int pageSize);
	public Page findPlanMangList(List<Org> list,String orgId,int pageIndex,int pageSize);
}
