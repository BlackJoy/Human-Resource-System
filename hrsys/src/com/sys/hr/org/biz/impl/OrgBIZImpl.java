package com.sys.hr.org.biz.impl;

import java.util.Iterator;
import java.util.List;

import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;
import com.sys.hr.org.dao.IOrgDAO;

public class OrgBIZImpl implements IOrgBIZ {

	private IOrgDAO orgDao;
	public IOrgDAO getOrgDao() {
		return orgDao;
	}
	public void setOrgDao(IOrgDAO orgDao) {
		this.orgDao = orgDao;
	}
	public Org getOrgTree() throws Exception {
		Org org = null;
		List<Org> orgs = orgDao.findAllOrg();
		//获得根节点
		for (Iterator it = orgs.iterator(); it.hasNext();) {
			Org o = (Org) it.next();
			if (o.getOrgParentId() == null || o.getOrgParentId().equals("")) {
				org = o;
				break;
			}
		}
		loadOrgTree(org, orgs);
		return org;
	}
	
	public Org getNormalOrgTree() throws Exception {
		Org org = null;
		String sql = "select o.* from TBL_Org o where o.orgstatus=1";
		List<Org> orgs = orgDao.findSQL2EntityList(sql, Org.class);
		//获得根节点
		for (Iterator it = orgs.iterator(); it.hasNext();) {
			Org o = (Org) it.next();
			if (o.getOrgParentId() == null || o.getOrgParentId().equals("")) {
				org = o;
				break;
			}
		}
		loadOrgTree(org, orgs);
		return org;
	}
	
	private void loadOrgTree(Org org, List<Org> orgs) {
		String orgId = org.getId();
		for (Iterator it = orgs.iterator(); it.hasNext();) {
			Org o = (Org) it.next();
			if (o.getOrgParentId() != null && o.getOrgParentId().equals(orgId)) {
				org.addSubOrg(o);
				loadOrgTree(o, orgs);
			}
		}
	}
	public Org findById(String id) throws Exception {
		return (Org)orgDao.findById(Org.class, id);
	}
	
	public void saveOrg(Org org) throws Exception {
		orgDao.save(org);
	}
	public void updateOrg(Org org) throws Exception {
		orgDao.update(org);
		//如果状态为“注销”，则其子组织也都改为“注销”
		if (org.getOrgStatus() == 0) {
			String sql = "SELECT o.* FROM Tbl_Org o START WITH o.id = '"+org.getId()+"' CONNECT BY o.orgparentid = PRIOR id";
			List<Org> orgs = orgDao.findSQL2EntityList(sql, Org.class);
			for (Iterator it = orgs.iterator(); it.hasNext();) {
				Org o = (Org) it.next();
				o.setOrgStatus(org.getOrgStatus());
				orgDao.update(o);
			}
		} else {//如果状态为“正常”，则其所有父组织也都改为“正常”
			String sql = "SELECT o.* FROM Tbl_Org o START WITH o.id = '"+org.getId()+"' CONNECT BY o.id = PRIOR orgparentid";
			List<Org> orgs = orgDao.findSQL2EntityList(sql, Org.class);
			for (Iterator it = orgs.iterator(); it.hasNext();) {
				Org o = (Org) it.next();
				o.setOrgStatus(org.getOrgStatus());
				orgDao.update(o);
			}
		}
	}
	public List<Org> findByParentId(String parentId) throws Exception {
		List<Org> orgs = null;
		String sql = "select * from TBL_Org o where o.orgparentid='"+parentId+"'";
		orgs = orgDao.findSQL2EntityList(sql, Org.class);
		return orgs;
	}
	public void deleteOrg(String id) throws Exception {
		String sql = "delete TBL_Org o where o.id='"+id+"'";
		orgDao.deleteSQL(sql);
	}
	public List findOrgsIncludFullName() throws Exception {
		List list = null;
		String sql = "select o.id,substr(sys_connect_by_path(o.orgshortname,'.'),2) fullname from Tbl_Org o where o.orgstatus=1" +
				" start with o.orgparentid is null connect by prior id=o.orgparentid";
		list = orgDao.findSQL2MapList(sql);
		return list;
	}
	public List findAll() {
		// TODO Auto-generated method stub
		List<Org> orgList =orgDao.findAllOrg();
		return orgList;
	}

}
