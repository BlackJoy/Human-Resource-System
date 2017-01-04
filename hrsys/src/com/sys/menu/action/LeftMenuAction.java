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
	 * ����dtree��ʽ��ʾ������ d.add(0,-1,menuname,f('')) d.add(1,0,menuname,f(''))
	 * d.add(2,0,menuname,f(''))
	 * 
	 * @return
	 * @throws Exception
	 *             'f(\'aa\')'
	 */

	public String getLeft() throws Exception {

		// ------------------------------------------

		// �õ���ǰ�û���Ȩ��
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		// ����ǰ�û�Ȩ����Ȩ��IDΪ��,�����Map����
		// ���ã�����ʾ���ܰ�ťʱ�����ж��û��Ƿ���в�����ť��Ȩ��
		Map<String, Authority> authorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			authorityMap.put(authority.getId(), authority);
		}
		ActionContext.getContext().getSession()
				.put("curr_authority_map", authorityMap);

		// ����ǰ�û���Ȩ����Ȩ�޶�Ӧ�Ĳ˵�IDΪ��,�����Map����(�����������ܰ�ťȨ��).
		// ���ã�����ʾ�˵���ʱ�����ж��û��Ƿ���в˵�Ȩ��
		Map<String, Authority> menuAuthorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			if (authority.getMenuId() != null
					&& !authority.getMenuId().trim().equals("")) {
				menuAuthorityMap.put(authority.getMenuId(), authority);
			}
		}
		ActionContext.getContext().getSession()
				.put("curr_menu_authority", menuAuthorityMap);
		// �õ�ϵͳ�˵���
		List<Menu> menuTree = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		Menu curr_menu = null;
		// ������������menuId�����ҳ�menuId��Ӧ�����˵�
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
		// ���û���ҵ�menuId��Ӧ�����˵���menuIdΪ�գ���curr_menu��Ϊ��
		// ���curr_menuΪ�գ���Ĭ��һ�����˵�
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
		// ���curr_menu��ȻΪ�գ����ʾ�û�û��Ȩ��
		// �û�û��Ȩ������ת����ҳ��
		if (curr_menu == null) {
			super.addActionError("��Ŀǰû��Ȩ�ޣ�����ϵ����Ա��");
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
		// --------------------����dtree����¶��ڶ�Ȩ�޵Ŀ���-------------------
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder str = new StringBuilder();
		List<Menu> menulst = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		str.append("d.add(0,-1,'����ϵͳ');");

		for (Menu menu : menulst) {
			for (Authority authority : authorityList) {
				if (menu.getId().equals(authority.getMenuId())) {// ֻ�о���Ȩ�޲ſ��Կ����˵�

					str.append(createMenuStr(menu));
				}
			}
		}

		request.setAttribute("strTree", str.toString());
		// --------------------����dtree����¶��ڶ�Ȩ�޵Ŀ���-------------------

		return "left";
	}

	public String createMenuStr(Menu menu) {
		// �õ���ǰ�û���Ȩ��
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		StringBuilder str = new StringBuilder();
		// �õ���menu���ַ���
		if (menu.getAction() == null || menu.getAction().length() == 0) {// ��һ���˵��������������¼�
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ","
					+ menu.getParentId().replaceAll("\\.", "") + ",'"
					+ menu.getName() + "');");

		}else if(menu.getParentId()==null){//һ���˵�������aciton�������ò�����ת�¼�
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ",0,'"
					+ menu.getName() + "');");
		}else {// ���ܰ�ť����ɾ�Ĳ飩
			str.append("d.add(" + menu.getId().replaceAll("\\.", "") + ","
					+ menu.getParentId().replaceAll("\\.", "") + ",'"
					+ menu.getName() + "','javascript:selectCate(\\\'"
					+ menu.getAction() + "?menuId=" + menu.getId() + "\\\')');");

		}
		// �õ�menu�ĺ��ӵ��ַ���
		if (menu.getSubMenu().size() > 0) {
			for (Menu mm : menu.getSubMenu()) {
				for (Authority authority : authorityList) {
					if (mm.getId().equals(authority.getMenuId())) {// ֻ�о���Ȩ�޲ſ��Կ����˵�

						str.append(createMenuStr(mm));
					}
				}
			}
		}
		return str.toString();
	}

	public String getLeftMenu() throws Exception {
		// �õ���ǰ�û���Ȩ��
		List<Authority> authorityList = (List<Authority>) ActionContext
				.getContext().getSession()
				.get(ILoginConstant.CURRENT_USER_AUTHORITY);

		// ����ǰ�û�Ȩ����Ȩ��IDΪ��,�����Map����
		// ���ã�����ʾ���ܰ�ťʱ�����ж��û��Ƿ���в�����ť��Ȩ��
		Map<String, Authority> authorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			authorityMap.put(authority.getId(), authority);
		}
		ActionContext.getContext().getSession()
				.put("curr_authority_map", authorityMap);

		// ����ǰ�û���Ȩ����Ȩ�޶�Ӧ�Ĳ˵�IDΪ��,�����Map����(�����������ܰ�ťȨ��).
		// ���ã�����ʾ�˵���ʱ�����ж��û��Ƿ���в˵�Ȩ��
		Map<String, Authority> menuAuthorityMap = new HashMap<String, Authority>();
		for (Authority authority : authorityList) {
			if (authority.getMenuId() != null
					&& !authority.getMenuId().trim().equals("")) {
				menuAuthorityMap.put(authority.getMenuId(), authority);
			}
		}
		ActionContext.getContext().getSession()
				.put("curr_menu_authority", menuAuthorityMap);
		// �õ�ϵͳ�˵���
		List<Menu> menuTree = (List<Menu>) ActionContext.getContext()
				.getApplication().get("menuTree");
		Menu curr_menu = null;
		// ������������menuId�����ҳ�menuId��Ӧ�����˵�
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
		// ���û���ҵ�menuId��Ӧ�����˵���menuIdΪ�գ���curr_menu��Ϊ��
		// ���curr_menuΪ�գ���Ĭ��һ�����˵�
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
		// ���curr_menu��ȻΪ�գ����ʾ�û�û��Ȩ��
		// �û�û��Ȩ������ת����ҳ��
		if (curr_menu == null) {
			super.addActionError("��Ŀǰû��Ȩ�ޣ�����ϵ����Ա��");
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
	 * str.append("ʵѵ������Դϵͳ"); str.append("','javascript:selectCate(0,\\\'");
	 * str.append(""); str.append("\\\')');"); for(Menu menu:menuTree) {
	 * if(menu.getParentId()==null) {
	 * 
	 * //�����˵��ַ��� str.append(buildChild(menu)); }
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
