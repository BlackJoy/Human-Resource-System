package com.sys.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sys.dict.DicData;
import com.sys.dict.biz.IDicBIZ;

public class DictDataSelectTag extends SimpleTagSupport {

	private String name;

	private String typeCode;

	private String selected;
	
	private String disabled;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jc = this.getJspContext();
		JspWriter out = jc.getOut();
		ServletContext servletContext = ((PageContext) jc).getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		IDicBIZ dicBiz = (IDicBIZ)ctx.getBean("dicBiz");
		List<DicData> dicDataList = null;
		if (disabled != null && !disabled.trim().equals("")) {
			out.println("<select name='"+name+"' disabled='disabled'>");
		} else {
			out.println("<select name='"+name+"'>");
		}
		try {
			dicDataList = (List<DicData>)dicBiz.getDicDataByDicTypeCode(typeCode);
			if (dicDataList != null) {
				for (Iterator it = dicDataList.iterator(); it.hasNext();) {
					DicData dicData = (DicData) it.next();
					if (selected != null && selected.equals(dicData.getId())) {
						out.println("<option value='"+dicData.getId()+"' selected='selected'>"+dicData.getDicDataName()+"</option>");
					} else {
						out.println("<option value='"+dicData.getId()+"'>"+dicData.getDicDataName()+"</option>");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</select>");
	}
}
