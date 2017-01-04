package com.sys.menu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.authority.Authority;
import com.sys.login.ILoginConstant;
import com.sys.menu.Menu;

public class LeftMenuAction extends ActionSupport {

	private String menuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/***
	 * 采用dtree方式显示功能树 d.add(0,-1,menuname,f('')) d.add(1,0,menuname,f(''))
	 * d.add(2,0,menuname,f(''))
	 * 
	 * @return
	 * @throws Exception
	 *             'f(\'aa\')'
	 */

	public String getLeft() throws Exception {

		// ------------------------------------------

		// 得到当前用户的权限
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		// 将当前用户权限以权限ID为键,保存成Map对象
		// 作用：在显示功能按钮时方便判断用户是否具有操作按钮的权限
		Map<String, Authority> authorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			authorityMap.put(authority.getId(), authority);
		}
		ActionContext.getContext().getSession()
				.put("curr_authority_map", authorityMap);

		// 将当前用户的权限以权限对应的菜单ID为键,保存成Map对象(但不包括功能按钮权限).
		// 作用：在显示菜单树时方便判断用户是否具有菜单权限
		Map<String, Authority> menuAuthorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			if (authority.getMenuId() != null
					&& !authority.getMenuId().trim().equals("")) {
				menuAuthorityMap.put(authority.getMenuId(), authority);
			}
		}
		ActionContext.getContext().getSession()
				.put("curr_menu_authority", menuAuthorityMap);
		// 得到系统菜单树
		List<Menu> menuTree = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		Menu curr_menu = null;
		// 如果有请求参数menuId，则找出menuId对应的主菜单
		if (menuId != null && !menuId.trim().equals("")) {
			for (Menu menu : menuTree) {
				if (menu.getId().equals(menuId)) {
					for (Authority authority : authorityList) {
						if (authority.getMenuId() != null
								&& authority.getMenuId().equals(menuId)) {
							curr_menu = menu;
							break;
						}
					}
					break;
				}
			}
		}
		// 如果没有找到menuId对应的主菜单或menuId为空，则curr_menu都为空
		// 如果curr_menu为空，则默认一个主菜单
		if (curr_menu == null) {
			for (Menu menu : menuTree) {
				boolean b = false;
				for (Authority authority : authorityList) {
					if (authority.getMenuId() != null
							&& authority.getMenuId().equals(menu.getId())) {
						curr_menu = menu;
						b = true;
						break;
					}
				}
				if (b) {
					break;
				}
			}
		}
		// 如果curr_menu依然为空，则表示用户没有权限
		// 用户没有权限则跳转错误页面
		if (curr_menu == null) {
			super.addActionError("您目前没有权限，请联系管理员。");
			return ERROR;
		}
		// ------------------------------------------
		// <s:iterator var="menu" value="#application.menuTree">
		// <s:iterator var="authority" value="#session.curr_user_authority">
		// <s:if test="#menu.id==#authority.menuId">
		// <li><a class="mm_a1"
		// href="<s:property value='#menu.action'/>?menuId=<s:property value='#menu.id'/>"
		// onclick='parent.mainFrame.location.href="page/common/init-content.jsp"'><s:property
		// value="#menu.name"/></a></li>
		// </s:if>
		// </s:iterator>
		// </s:iterator>
		// --------------------采用dtree情况下对于对权限的控制-------------------
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		List<Menu> menulst = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		str.append("d.add(0,-1,'人事系统');");

		for (Menu menu : menulst) {
			for (Authority authority : authorityList) {
				if (menu.getId().equals(authority.getMenuId())) {// 只有具有权限才可以看见菜单

					str.append(createMenuStr(menu));
				}
			}
		}

		request.setAttribute("strTree", str.toString());
		// --------------------采用dtree情况下对于对权限的控制-------------------

		return "left";
	}

	public String createMenuStr(Menu menu) {
		// 得到当前用户的权限
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		StringBuilder str = new StringBuilder();
		// 得到该menu的字符串
		if (menu.getAction() == null || menu.getAction().length() == 0) {// 非一级菜单，不产生单击事件
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ","
					+ menu.getParentId().replaceAll("\\.", "") + ",'"
					+ menu.getName() + "');");

		}else if(menu.getParentId()==null){//一级菜单，具有aciton，但不该产生跳转事件
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ",0,'"
					+ menu.getName() + "');");
		}else {// 功能按钮（增删改查）
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ","
					+ menu.getParentId().replaceAll("\\.", "") + ",'"
					+ menu.getName() + "','javascript:selectCate(\\\'"
					+ menu.getAction() + "?menuId=" + menu.getId() + "\\\')');");

		}
		// 得到menu的孩子的字符串
		if (menu.getSubMenu().size() > 0) {
			for (Menu mm : menu.getSubMenu()) {
				for (Authority authority : authorityList) {
					if (mm.getId().equals(authority.getMenuId())) {// 只有具有权限才可以看见菜单

						str.append(createMenuStr(mm));
					}
				}
			}
		}
		return str.toString();
	}

	public String getLeftMenu() throws Exception {
		// 得到当前用户的权限
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		// 将当前用户权限以权限ID为键,保存成Map对象
		// 作用：在显示功能按钮时方便判断用户是否具有操作按钮的权限
		Map<String, Authority> authorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			authorityMap.put(authority.getId(), authority);
		}
		ActionContext.getContext().getSession()
				.put("curr_authority_map", authorityMap);

		// 将当前用户的权限以权限对应的菜单ID为键,保存成Map对象(但不包括功能按钮权限).
		// 作用：在显示菜单树时方便判断用户是否具有菜单权限
		Map<String, Authority> menuAuthorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			if (authority.getMenuId() != null
					&& !authority.getMenuId().trim().equals("")) {
				menuAuthorityMap.put(authority.getMenuId(), authority);
			}
		}
		ActionContext.getContext().getSession()
				.put("curr_menu_authority", menuAuthorityMap);
		// 得到系统菜单树
		List<Menu> menuTree = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		Menu curr_menu = null;
		// 如果有请求参数menuId，则找出menuId对应的主菜单
		if (menuId != null && !menuId.trim().equals("")) {
			for (Menu menu : menuTree) {
				if (menu.getId().equals(menuId)) {
					for (Authority authority : authorityList) {
						if (authority.getMenuId() != null
								&& authority.getMenuId().equals(menuId)) {
							curr_menu = menu;
							break;
						}
					}
					break;
				}
			}
		}
		// 如果没有找到menuId对应的主菜单或menuId为空，则curr_menu都为空
		// 如果curr_menu为空，则默认一个主菜单
		if (curr_menu == null) {
			for (Menu menu : menuTree) {
				boolean b = false;
				for (Authority authority : authorityList) {
					if (authority.getMenuId() != null
							&& authority.getMenuId().equals(menu.getId())) {
						curr_menu = menu;
						b = true;
						break;
					}
				}
				if (b) {
					break;
				}
			}
		}
		// 如果curr_menu依然为空，则表示用户没有权限
		// 用户没有权限则跳转错误页面
		if (curr_menu == null) {
			super.addActionError("您目前没有权限，请联系管理员。");
			return ERROR;
		}
		ActionContext.getContext().getSession().put("curr_menu", curr_menu);
		return SUCCESS;
	}

	/*
	 * public String getLeft() throws Exception { HttpServletRequest request =
	 * ServletActionContext.getRequest(); List<Menu> menuTree =
	 * (List<Menu>)ActionContext.getContext().getApplication().get("menuTree");
	 * StringBuffer str=new StringBuffer("d.add(0,-1,'" );
	 * str.append("实训人力资源系统"); str.append("','javascript:selectCate(0,\\\'");
	 * str.append(""); str.append("\\\')');"); for(Menu menu:menuTree) {
	 * if(menu.getParentId()==null) {
	 * 
	 * //构建菜单字符串 str.append(buildChild(menu)); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * request.setAttribute("strTree", str); return "left"; }
	 * 
	 * public String buildChild(Menu menu) { StringBuilder str=new
	 * StringBuilder();
	 * 
	 * if(menu.getParentId()==null) {
	 * str.append("d.add("+menu.getId().replaceAll("\\.", "")+",0,'" );
	 * str.append(menu.getName());
	 * str.append("','javascript:selectCate(0,\\\'");
	 * str.append(menu.getAction()); str.append("\\\')');"); } else{
	 * str.append("d.add("+menu.getId().replaceAll("\\.",
	 * "")+","+menu.getParentId().replaceAll("\\.", "")+",'" );
	 * str.append(menu.getName());
	 * str.append("','javascript:selectCate(0,\\\'");
	 * str.append(menu.getAction()); str.append("\\\')');"); }
	 * if(menu.getSubMenu().size()>0) { for(Menu sub:menu.getSubMenu()) {
	 * str.append(buildChild(sub)); } } return str.toString();
	 * 
	 * }
	 */
}
