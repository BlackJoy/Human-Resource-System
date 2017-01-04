package com.sys.hr.org.biz;

import java.util.List;

import com.sys.hr.org.Org;

public interface IOrgBIZ {

	/**
	 * 创建组织树
	 * @return
	 * @throws Exception
	 */
	public Org getOrgTree() throws Exception;
	
	/**
	 * 创建组织（状态为“正常”）树
	 * @return
	 * @throws Exception
	 */
	public Org getNormalOrgTree() throws Exception;
	
	/**
	 * 根据ID查找组织
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Org findById(String id) throws Exception;
	
	/**
	 * 新增组织
	 * @param org
	 * @throws Exception
	 */
	public void saveOrg(Org org) throws Exception;
	
	/**
	 * 更新组织
	 * @param org
	 * @throws Exception
	 */
	public void updateOrg(Org org) throws Exception;
	
	/**
	 * 根据父组织ID查找子组织
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Org> findByParentId(String parentId) throws Exception;
	
	/**
	 * 删除组织
	 * @param org
	 * @throws Exception
	 */
	public void deleteOrg(String id) throws Exception;
	
	/**
	 * 查找包含完整节点名的组织
	 * @return
	 * @throws Exception
	 */
	public List findOrgsIncludFullName() throws Exception;
	
	
	/**
	 *查找所有的记录
	 */
	public List findAll();
	
}
