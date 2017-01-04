package com.sys.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sys.authority.Authority;
import com.sys.hr.org.IOrgConstant;
import com.sys.hr.org.Org;

public class OrgTableTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		
		Org orgTree = (Org)jc.getAttribute("orgTree", PageContext.REQUEST_SCOPE);
		if (orgTree != null) {
			out.println("<tr height='20px;' valign='middle'>");
			if (orgTree.getOrgList() == null || orgTree.getOrgList().size() == 0) {
				out.println("<td class='orgCode' isend='true'><img src='images/L2.gif' align='middle' /><input class='ckb' type='checkbox' id='"+orgTree.getId()+"' name='orgId' value='"+orgTree.getId()+"' />&nbsp;"+orgTree.getOrgCode()+"</td>");
			} else {
				out.println("<td class='orgCode' isend='true'><div class='org_parent' id='>"+orgTree.getId()+"' style='cursor: pointer; display:inline;' isend='true'><img src='images/M1.gif' align='middle' /></div><input class='ckb' type='checkbox' id='"+orgTree.getId()+"' name='orgId' value='"+orgTree.getId()+"' />&nbsp;"+orgTree.getOrgCode()+"</td>");
			}
			out.println("<td>"+orgTree.getOrgFullName()+"</td>");
			out.println("<td>"+orgTree.getOrgShortName()+"</td>");
			out.println("<td>"+IOrgConstant.ORGTYPE[orgTree.getOrgType()]+"</td>");
			out.println("<td>"+IOrgConstant.ORGSTATUS[orgTree.getOrgStatus()]+"</td>");
			out.println("</tr>");
			if (orgTree.getOrgList() != null && orgTree.getOrgList().size() > 0) {
				out.println("<tr height='20px;' style='display:none;' valign='middle' parentId='>"+orgTree.getId()+"'>");//display:none;
				out.println("<td colspan='5'>");
				out.println("<table border='0' width='100%' style='background: #fff; font-size:12px;' cellpadding='0' cellspacing='0'>");
				showOrg(orgTree.getOrgList(), out, ">"+orgTree.getId());
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
			}
		}
	}
	
	private void showOrg(List<Org> orgs, JspWriter out, String l) throws IOException {
		for (int i = 0; i < orgs.size(); i++) {
			Org org = orgs.get(i);
			String level = l + ">"+org.getId();
			out.println("<tr height='20px;' valign='middle'>");
			String chk = "<input class='ckb' type='checkbox' id='"+org.getId()+"' name='orgId' value='"+org.getId()+"' />&nbsp;"+org.getOrgCode();
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
			if (i == orgs.size() - 1) {//是最后一个元素
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没有子元素
					out.println("<td class='orgCode' isend='true'>"+imgs+"<img src='images/L2.gif' align='middle' />"+chk+"</td>");
				} else {//有子元素
					out.println("<td class='orgCode' isend='true'>"+imgs+"<div class='org_parent' id='"+level+"' style='cursor: pointer; display:inline;' isend='true'><img src='images/M1.gif' align='middle' /></div>"+chk+"</td>");
				}
			} else {//不是最后一个元素
				if (org.getOrgList() == null || org.getOrgList().size() == 0) {//没有子元素
					out.println("<td class='orgCode' isend='false'>"+imgs+"<img src='images/L1.gif' align='middle' />"+chk+"</td>");
				} else {//有子元素
					out.println("<td class='orgCode' isend='false'>"+imgs+"<div class='org_parent' id='"+level+"' style='cursor: pointer; display:inline;' isend='false'><img src='images/P1.gif' align='middle' /></div>"+chk+"</td>");
				}
			}
			out.println("<td>"+org.getOrgFullName()+"</td>");
			out.println("<td>"+org.getOrgShortName()+"</td>");
			out.println("<td>"+IOrgConstant.ORGTYPE[org.getOrgType()]+"</td>");
			out.println("<td>"+IOrgConstant.ORGSTATUS[org.getOrgStatus()]+"</td>");
			out.println("</tr>");
			if (org.getOrgList() != null && org.getOrgList().size() > 0) {
				out.println("<tr height='20px;' style='display:none;' valign='middle' parentId='"+level+"'>");//display:none;
				out.println("<td colspan='5'>");
				out.println("<table border='0' width='100%' style='background: #fff; font-size:12px;' cellpadding='0' cellspacing='0'>");
				showOrg(org.getOrgList(), out, level);
				out.println("</table>");
				out.println("</td>");
				out.println("</tr>");
			}
		}
	}
	
}
