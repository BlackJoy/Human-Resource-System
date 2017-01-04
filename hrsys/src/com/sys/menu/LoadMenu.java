package com.sys.menu;

import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 加载菜单类
 * @author Administrator
 *
 */
public final class LoadMenu {
	
	private Document doc;
	
	private LoadMenu(){
		try {
			InputStream in = LoadMenu.class.getClassLoader().getResourceAsStream(IMenuConstant.MENU_XML);
			doc = new SAXReader().read(in).getDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 封装Menu属性
	 * @param menu
	 * @param element
	 */
	public void setProperties(Menu menu, Element element) {
		menu.setId(element.attributeValue("id"));
		menu.setParentId(element.attributeValue("parentId"));
		menu.setName(element.attributeValue("name"));
		menu.setDesc(element.attributeValue("desc"));
		menu.setAction(element.attributeValue("action"));
	}
	
	/**
	 * 从xml文件中加载菜单信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void loadMenu(MenuTree tree){
		List<Menu> menuTree = tree.getMenus();
		//加载主菜单
		for (Element element : (List<Element>)doc.getRootElement().elements("menu")) {
			Menu menu = new Menu();
			setProperties(menu, element);
			menuTree.add(menu);
		}
		//加载各子菜单
		for (Element element : (List<Element>)doc.getRootElement().elements("menuItem")) {
			Menu menu = new Menu();
			setProperties(menu, element);
			for (Iterator<Menu> it = menuTree.iterator(); it.hasNext();) {
				Menu p = (Menu) it.next();
				if (addSubMenu(p, menu)) {
					break;
				}
			}
		}
		//菜单排序
		Collections.sort(menuTree);
		for (Menu menu : menuTree) {
			sortSubMenu(menu);
		}
	}
	
	/**
	 * 递归：将子菜单添加到父菜单中
	 * @param parent
	 * @param child
	 * @return
	 */
	private boolean addSubMenu(Menu parent, Menu child) {
		if (parent.getId().equals(child.getParentId())) {
			return parent.addMenu(child);
		} else {
			//看看该child是否应挂在子类的某个菜单下面
			for (Menu p : parent.getSubMenu()) {
				addSubMenu(p, child);
			}
		}
		return false;
	}
	
	/**
	 * 递归：菜单排序
	 * @param subMenu
	 */
	private void sortSubMenu(Menu subMenu) {
		if (subMenu.getSubMenu() != null && subMenu.getSubMenu().size() > 0) {
			Collections.sort(subMenu.getSubMenu());
			for (Menu sm : subMenu.getSubMenu()) {
				sortSubMenu(sm);
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			MenuTree tree = new MenuTree();
			LoadMenu load = new LoadMenu();
			load.loadMenu(tree);
			List<Menu> menus = tree.getMenus();
			System.out.println("加载完毕...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
