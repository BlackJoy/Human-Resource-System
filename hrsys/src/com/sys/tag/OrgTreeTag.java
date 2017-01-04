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
import com.sys.hr.org.IOrgConstant;
import com.sys.hr.org.Org;
import com.sys.hr.org.biz.IOrgBIZ;

public class OrgTreeTag extends SimpleTagSupport {

	private String action;
	private String target;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		ServletContext servletContext = ((PageContext) jc).getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IOrgBIZ orgBiz = (IOrgBIZ)ctx.getBean("orgBiz");
		Org orgTree = null;
		try {
			orgTree = orgBiz.getNormalOrgTree();
			if (orgTree != null) {
				out.println("<ul>");
				if (orgTree.getOrgList() == null || orgTree.getOrgList().size() == 0) {
					out.println("<li isend='true'><img src='images/L2.gif' align='middle' /><a href='"+action+"?orgId="+orgTree.getId()+"' target='"+target+"'>"+orgTree.getOrgShortName()+"</a></li>");
				} else {
					out.println("<li isend='true'><div class='org_parent' style='cursor: pointer; display: inline;' isend='true'><img src='images/M1.gif' align='middle' /></div><a href='"+action+"?orgId="+orgTree.getId()+"' target='"+target+"'>"+orgTree.getOrgShortName()+"</a>");
					out.println("<ul style='display:none;'>");// style='display:none;'
					showOrg(orgTree.getOrgList(), out);
					out.println("</ul>");
					out.println("</li>");
				}
				out.println("</ul>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showOrg(List<Org> orgTree, JspWriter out) throws IOException {
		for (int i = 0; i < orgTree.size(); i++) {
			Org org = orgTree.get(i);
			//为checkbox的level属性设置层级值
//			String level = l + ">"+org.getId();
			String chk = "";
			//计算层级
			int count = org.getOrgCode().length() - org.getOrgCode().replaceAll("-", "").length();
			String imgs = "";
			for (int c = 0; c < count; c++) {
				if (c == 0) {
					imgs+="<img src='images/L4.gif' style='float:left; clear:both;'/>";
				} else {
					imgs+="<img src='images/L4.gif' style='float:left;'/>";
				}
			}
			if (i == orgTree.size() - 1) {//是最后一个元素
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没有子元素
					out.println("<li isend='true'>"+imgs+"<img src='images/L2.gif' align='middle' /><a href='"+action+"?orgId="+org.getId()+"' target='"+target+"'>"+org.getOrgShortName()+"</a>");
				} else {//有子元素
					out.println("<li isend='true'>"+imgs+"<div class='org_parent' style='cursor: pointer; display: inline;' isend='true'><img src='images/M1.gif' align='middle' /></div><a href='"+action+"?orgId="+org.getId()+"' target='"+target+"'>"+org.getOrgShortName()+"</a>");
					out.println("<ul style='display:none;'>");// style='display:none;'
					showOrg(org.getOrgList(), out);
					out.println("</ul>");
				}
			} else {//不是最后一个元素
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没有子元素
					out.println("<li isend='false'>"+imgs+"<img src='images/L1.gif' align='middle' /><a href='"+action+"?orgId="+org.getId()+"' target='"+target+"'>"+org.getOrgShortName()+"</a>");
				} else {//有子元素
					out.println("<li isend='false'>"+imgs+"<div class='org_parent' style='cursor: pointer; display: inline;' isend='false'><img src='images/P1.gif' align='middle' /></div><a href='"+action+"?orgId="+org.getId()+"' target='"+target+"'>"+org.getOrgShortName()+"</a>");
					out.println("<ul style='display:none;'>");// style='display:none;'
					showOrg(org.getOrgList(), out);
					out.println("</ul>");
				}
			}
			out.println("</li>");
		}
	}
}
