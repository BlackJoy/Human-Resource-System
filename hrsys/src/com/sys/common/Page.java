package com.sys.common;

import java.io.Serializable;
import java.util.List;

/**
 * 封装页面数据的类
 * @author Administrator
 *
 */
public class Page implements Serializable {

	private static final long serialVersionUID = -5060498096960253102L;
	private List list;
	private int pageIndex;
	private int pageSize;
	private int rowCount;
	public Page(List list, int pageIndex, int pageSize, int rowCount) {
		this.list = list;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
	}
	public Page() {
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}
