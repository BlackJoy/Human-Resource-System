package com.sys.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.sys.authority.Authority;

public class ButtonAuthorityTag extends SimpleTagSupport {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		Map<String, Authority> authorityMap = (Map<String, Authority>)jc.getAttribute("curr_authority_map", PageContext.SESSION_SCOPE);
		if (authorityMap == null || authorityMap.get(id) == null) {
			out.print("disabled='disabled'");
		}
	}
}
