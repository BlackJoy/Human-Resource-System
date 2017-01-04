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

public class OrgTreeTagBox extends SimpleTagSupport {

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
					out.println("<li isend='true'><img src='images/L2.gif' align='middle' /><input name='chkOrg' id='chkOrg' type='checkbox' value='"+orgTree.getOrgCode()+"&"+orgTree.getOrgFullName()+"&"+orgTree.getId()+"'>"+orgTree.getOrgShortName()+"</>");
				} else {
					out.println("<li isend='true'><div class='org_parent' style='cursor: pointer; display: inline;' isend='true'><img src='images/M1.gif' align='middle' /></div><input name='chkOrg' id='chkOrg' type='checkbox' value='"+orgTree.getOrgCode()+"&"+orgTree.getOrgFullName()+"&"+orgTree.getId()+"'>"+orgTree.getOrgShortName()+"</>");
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
			//为checkbox锟斤拷level锟斤拷锟斤拷锟斤拷锟矫层级值
//			String level = l + ">"+org.getId();
			String chk = "";
			//锟斤拷锟斤拷慵�
			int count = org.getOrgCode().length() - org.getOrgCode().replaceAll("-", "").length();
			String imgs = "";
			for (int c = 0; c < count; c++) {
				if (c == 0) {
					imgs+="<img src='images/L4.gif' style='float:left; clear:both;'/>";
				} else {
					imgs+="<img src='images/L4.gif' style='float:left;'/>";
				}
			}
			if (i == orgTree.size() - 1) {//锟斤拷锟斤拷锟揭伙拷锟皆拷锟�
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没锟斤拷锟斤拷元锟斤拷
					out.println("<li isend='true'>"+imgs+"<img src='images/L2.gif' align='middle' /><input name='chkOrg' id='chkOrg' type='checkbox' value='"+org.getOrgCode()+"&"+org.getOrgFullName()+"&"+org.getId()+"'>"+org.getOrgShortName()+"</>");
				} else {//锟斤拷锟斤拷元锟斤拷
					out.println("<li isend='true'>"+imgs+"<div class='org_parent' style='cursor: pointer; display: inline;' isend='true'><img src='images/M1.gif' align='middle' /></div><input name='chkOrg' id='chkOrg' type='checkbox' value='"+org.getOrgCode()+"&"+org.getOrgFullName()+"&"+org.getId()+"'>"+org.getOrgShortName()+"</>");
					out.println("<ul style='display:none;'>");// style='display:none;'
					showOrg(org.getOrgList(), out);
					out.println("</ul>");
				}
			} else {//锟斤拷锟斤拷锟斤拷锟揭伙拷锟皆拷锟�
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没锟斤拷锟斤拷元锟斤拷
					out.println("<li isend='false'>"+imgs+"<img src='images/L1.gif' align='middle' /><input name='chkOrg' id='chkOrg' type='checkbox' value='"+org.getOrgCode()+"&"+org.getOrgFullName()+"&"+org.getId()+"'>"+org.getOrgShortName()+"</>");
				} else {//锟斤拷锟斤拷元锟斤拷
					out.println("<li isend='false'>"+imgs+"<div class='org_parent' style='cursor: pointer; display: inline;' isend='false'><img src='images/P1.gif' align='middle' /></div><input name='chkOrg' id='chkOrg' type='checkbox' value='"+org.getOrgCode()+"&"+org.getOrgFullName()+"&"+org.getId()+"'>"+org.getOrgShortName()+"</>");
					out.println("<ul style='display:none;'>");// style='display:none;'
					showOrg(org.getOrgList(), out);
					out.println("</ul>");
				}
			}
			out.println("</li>");
		}
	}
}
