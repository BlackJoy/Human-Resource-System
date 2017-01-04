package com.sys.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * �˵���
 * @author Administrator
 *
 */
public class Menu implements Comparable<Menu>{

	private String id;			//�˵�id
	private String parentId;	//���˵�id
	private String name;		//�˵���
	private String desc;		//�˵�����
	private String action;		//����˵���Ķ���
	private List<Menu> subMenu = new ArrayList<Menu>();		//�Ӳ˵��б�
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
	 * �����Ӳ˵��ķ���
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
	 * ���Ӳ˵���������
	 */
	public void sortSubMenu(){
		Collections.sort(this.getSubMenu());
	}
}
