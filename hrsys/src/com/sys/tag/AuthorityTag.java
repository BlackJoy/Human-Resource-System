package com.sys.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sys.authority.Authority;
import com.sys.authority.biz.IAuthorityBIZ;

public class AuthorityTag extends SimpleTagSupport {

	private String roleId;
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		//加载角色的权限
		ServletContext servletContext = ((PageContext) jc).getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IAuthorityBIZ authorityBiz = (IAuthorityBIZ)ctx.getBean("authorityBiz");
		List<Authority> authorityList = null;
		Map<String, Authority> authorityMap = new HashMap<String, Authority>();
		try {
			authorityList = authorityBiz.getAuthoritiesByRoleId(roleId);
			for (Authority authority : authorityList) {
				authorityMap.put(authority.getId(), authority);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Authority> authorityTree = (List<Authority>)jc.getAttribute("authorityTree", PageContext.APPLICATION_SCOPE);
		if (authorityTree != null && authorityTree.size() > 0) {
			out.println("<ul>");
			showSubAuthority(authorityTree, authorityMap, out, "");
			out.println("</ul>");
		}
	}
	
	private void showSubAuthority(List<Authority> authorityTree, Map<String,Authority> authMap, JspWriter out, String l) throws IOException {
		for (int i = 0; i < authorityTree.size(); i++) {
			Authority auth = authorityTree.get(i);
			//为checkbox的level属性设置层级值
			String level = l + ">"+auth.getId();
			String chk = "";
			if (authMap.get(auth.getId()) != null) {
				chk = "<input class='ckb' type='checkbox' checked='checked' id='"+auth.getId()+"' level='"+level+"' name='authId' value='"+auth.getId()+"' />&nbsp;"+auth.getName();
			} else {
				chk = "<input class='ckb' type='checkbox' id='"+auth.getId()+"' level='"+level+"' name='authId' value='"+auth.getId()+"' />&nbsp;"+auth.getName();
			}
			//计算层级
			int count = auth.getId().length() - auth.getId().replaceAll("\\.", "").length();
			for (int c = 0; c < count; c++) {
				if (c == 0) {
					out.println("<img src='images/L4.gif' style='float:left; clear:both;'/>");
				} else {
					out.println("<img src='images/L4.gif' style='float:left;'/>");
				}
			}
			if (i == authorityTree.size() - 1) {//是最后一个元素
				if (auth.getSubAuthority() == null || auth.getSubAuthority().size() == 0) {//没有子元素
					out.println("<li isend='true'><img src='images/L2.gif' align='middle' />"+chk);
				} else {//有子元素
					out.println("<li isend='true'><div class='auth_parent' style='cursor: pointer;' isend='true'><img src='images/M1.gif' align='middle' />"+chk+"</div>");
				}
			} else {//不是最后一个元素
				if (auth.getSubAuthority() == null || auth.getSubAuthority().size() == 0) {//没有子元素
					out.println("<li isend='false'><img src='images/L1.gif' align='middle' />"+chk);
				} else {//有子元素
					out.println("<li isend='false'><div class='auth_parent' style='cursor: pointer;' isend='false'><img src='images/P1.gif' align='middle' />"+chk+"</div>");
				}
			}
			if (auth.getSubAuthority() != null && auth.getSubAuthority().size() > 0) {
				out.println("<ul style='display:none;'>");
				showSubAuthority(auth.getSubAuthority(), authMap, out, level);
				out.println("</ul>");
			}
			out.println("</li>");
		}
	}
}
