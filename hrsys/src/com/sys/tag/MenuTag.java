package com.sys.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sys.authority.Authority;
import com.sys.menu.Menu;

public class MenuTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		Map<String, Authority> auth_map = (Map<String, Authority>)jc.getAttribute("curr_menu_authority", PageContext.SESSION_SCOPE);
		Menu curr_menu = (Menu)jc.getAttribute("curr_menu", PageContext.SESSION_SCOPE);
		int i = 0;
		for (Menu subMenu : curr_menu.getSubMenu()) {
			if (auth_map.get(subMenu.getId()) != null) {
				out.println("<div class='subMenu'>"+subMenu.getName()+"</div>");
				if (++i == 1) {
					out.println("<ul style='padding-left:10px;'>");
				} else {
					out.println("<ul style='padding-left:10px; display:none;'>");
				}
				for (Menu sm : subMenu.getSubMenu()) {
					showSubMenu(sm, out, auth_map);
				}
				out.println("</ul>");
			}
		}
	}
	
	private void showSubMenu(Menu subMenu, JspWriter out, Map auth_map) throws IOException {
		if (auth_map.get(subMenu.getId()) != null) {
			int count = subMenu.getId().length() - subMenu.getId().replaceAll("\\.", "").length();
			for (int i = 0; i < count - 2; i++) {
				if (i == 0) {
					out.println("<img src='images/L4.gif' style='float:left; clear:both;'/>");
				} else {
					out.println("<img src='images/L4.gif' style='float:left;'/>");
				}
			}
			if (subMenu.getSubMenu() == null || subMenu.getSubMenu().size() == 0) {
				out.println("<li><img src='images/L1.gif' align='middle' /><a href='"+subMenu.getAction()+"?menuId="+subMenu.getId()+"' target='mainFrame'>"+subMenu.getName()+"</a></li>");
			} else {
				out.println("<li><div class='branch' style='cursor: pointer;'><img src='images/P1.gif' align='middle' />"+subMenu.getName()+"</div>");
				out.println("<ul style='display:none;'>");
				for (Menu sm : subMenu.getSubMenu()) {
					showSubMenu(sm, out, auth_map);
				}
				out.println("</ul>");
				out.println("</li>");
			}
		}
	}
}
