package com.sys.hr.org.biz;

import java.util.List;

import com.sys.hr.org.Org;

public interface IOrgBIZ {

	/**
	 * ������֯��
	 * @return
	 * @throws Exception
	 */
	public Org getOrgTree() throws Exception;
	
	/**
	 * ������֯��״̬Ϊ������������
	 * @return
	 * @throws Exception
	 */
	public Org getNormalOrgTree() throws Exception;
	
	/**
	 * ����ID������֯
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Org findById(String id) throws Exception;
	
	/**
	 * ������֯
	 * @param org
	 * @throws Exception
	 */
	public void saveOrg(Org org) throws Exception;
	
	/**
	 * ������֯
	 * @param org
	 * @throws Exception
	 */
	public void updateOrg(Org org) throws Exception;
	
	/**
	 * ���ݸ���֯ID��������֯
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Org> findByParentId(String parentId) throws Exception;
	
	/**
	 * ɾ����֯
	 * @param org
	 * @throws Exception
	 */
	public void deleteOrg(String id) throws Exception;
	
	/**
	 * ���Ұ��������ڵ�������֯
	 * @return
	 * @throws Exception
	 */
	public List findOrgsIncludFullName() throws Exception;
	
	
	/**
	 *�������еļ�¼
	 */
	public List findAll();
	
}
