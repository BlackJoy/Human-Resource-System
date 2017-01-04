package com.sys.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * ²Ëµ¥Ê÷Àà
 * @author Administrator
 *
 */
public class MenuTree {
	
	private LoadMenu loadMenu = null;
	
	private List<Menu> menus = null;
	
	public MenuTree() {
		menus = new ArrayList<Menu>();
	}

	public LoadMenu getLoadMenu() {
		return loadMenu;
	}

	public void setLoadMenu(LoadMenu loadMenu) {
		this.loadMenu = loadMenu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public void init() {
		loadMenu.loadMenu(this);
	}
}
