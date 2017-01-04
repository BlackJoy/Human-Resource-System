package com.sys.hr.org.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.Page;
import com.sys.common.biz.IBaseBIZ;
import com.sys.hr.org.Post;
import com.sys.login.User;

public class PostAction extends ActionSupport {

	private int pageSize = 10;
	private String orgId;
	private String postId;
	private int pageIndex;

	private Post post;//��������ӵĸ�λ

	private IBaseBIZ commonBiz;

	public String getPostId() {
		return postId;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public IBaseBIZ getCommonBiz() {
		return commonBiz;
	}

	public void setCommonBiz(IBaseBIZ commonBiz) {
		this.commonBiz = commonBiz;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	 * ��λ�б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		String sql = "select * from tbl_post where orgid='" + this.orgId + "'";
		Page page = commonBiz.findPageBySql_MapList(sql, pageIndex, pageSize);
		ActionContext.getContext().put("post_list_page", page);
		ActionContext.getContext().put("orgId", orgId);
		return "list";
	}

	/**
	 * ��ת�˻�����ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toAdd() throws Exception {

		System.out.println();
		return "toAdd";
	}

	/**
	 * �����˻�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {

		post.setOrgid(orgId);
		commonBiz.save(post);
		return "listAction";
	}

	/**
	 * ��ת�޸�ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toUpdate() throws Exception {
		post=(Post) commonBiz.getEntityById(postId, Post.class);
		return "toUpdate";
	}

	/**
	 * �����˻�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
	
		post.setOrgid(orgId);
		commonBiz.update(post);
		return "listAction";
	}

	/**
	 * ɾ���˺�
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		Post post = (Post) commonBiz.getEntityById(postId, Post.class);
		commonBiz.delete(post);
		return "listAction";
	}

}
