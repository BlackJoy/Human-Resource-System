package com.sys.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 菜单类
 * @author Administrator
 *
 */
public class Menu implements Comparable<Menu>{

	private String id;			//菜单id
	private String parentId;	//父菜单id
	private String name;		//菜单名
	private String desc;		//菜单描述
	private String action;		//点击菜单后的动作
	private List<Menu> subMenu = new ArrayList<Menu>();		//子菜单列表
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	
	/**
	 * 增加子菜单的方法
	 * @param menu
	 * @return
	 */
	public boolean addMenu(Menu menu){
		return this.getSubMenu().add(menu);
	}

	public int compareTo(Menu menu) {
		int this_id = Integer.parseInt(this.getId().substring(this.getId().lastIndexOf('.') + 1));
		int menu_id = Integer.parseInt(menu.getId().substring(menu.getId().lastIndexOf('.') + 1));
		return this_id - menu_id;
	}
	
	/**
	 * 对子菜单进行排序
	 */
	public void sortSubMenu(){
		Collections.sort(this.getSubMenu());
	}
}
