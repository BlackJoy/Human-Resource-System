package com.sys.init;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sys.approve.LoadApproveContentConfig;
import com.sys.authority.AuthorityTree;
import com.sys.authority.biz.IAuthorityBIZ;
import com.sys.menu.MenuTree;

public class SysInitServlet extends HttpServlet {

	private static final long serialVersionUID = 5896157262526064199L;

	private IAuthorityBIZ authorityBiz;
	
	private MenuTree menuTree;
	
	private AuthorityTree authorityTree;
	
	private LoadApproveContentConfig loadApproveContent;
	
//	private IRoleAuthorityBIZ roleAuthorityBiz;
	
	public SysInitServlet() {
	}
	
	public void init() throws ServletException {
		//获取spring容器
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		authorityBiz = (IAuthorityBIZ)ctx.getBean("authorityBiz");
		menuTree = (MenuTree)ctx.getBean("menuTree");
		authorityTree = (AuthorityTree)ctx.getBean("authorityTree");
		loadApproveContent = (LoadApproveContentConfig) ctx.getBean("loadApproveContent");
//		roleAuthorityBiz = (IRoleAuthorityBIZ)ctx.getBean("roleAuthorityBiz");
		
		//将菜单树、权限树、权限列表放到应用程序范围
		ServletContext application = this.getServletContext();
		application.setAttribute("menuTree", menuTree.getMenus());
		application.setAttribute("authorityTree", authorityTree.getAuthoritiesTree());
		application.setAttribute("authorities", authorityTree.getAuthorities());
		application.setAttribute("approveContent", loadApproveContent.loadApproveContent());
		try {
//			authorityBiz.deleteAll();
			authorityBiz.saveAuthorities(authorityTree.getAuthorities());
//			List<RoleAuthority> ras = new ArrayList<RoleAuthority>();
//			for (Authority authority : authorityTree.getAuthorities()) {
//				RoleAuthority ra = new RoleAuthority();
//				ra.setRoleId("1");
//				ra.setAuthorityId(authority.getId());
//				ras.add(ra);
//			}
//			roleAuthorityBiz.saveRoleAuthority(ras);
			System.out.println("init completed...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
