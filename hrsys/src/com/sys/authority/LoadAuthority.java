package com.sys.authority;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sys.menu.LoadMenu;

/**
 * 加载权限类
 * @author Administrator
 *
 */
public class LoadAuthority {
	
	private Document doc;
	
	public LoadAuthority() {
		try {
			InputStream in = LoadMenu.class.getClassLoader().getResourceAsStream(IAuthorityConstant.AUTHORITY_XML);
			doc = new SAXReader().read(in).getDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadAuthority(AuthorityTree tree) {
		List<Authority> authorities = tree.getAuthorities();
		for (Element element : (List<Element>)doc.getRootElement().elements("authority")) {
			Authority authority = new Authority();
			setProperties(authority, element);
			authorities.add(authority);
		}
		List<Authority> authorityTree = tree.getAuthoritiesTree();
		//装载父权限
		for (Authority authority : authorities) {
			if (authority.getParentId() == null || authority.getParentId().equals("")) {
				authorityTree.add(authority);
			}
		}
		//装载子权限
		for (Authority child : authorities) {
			if (child.getParentId() != null && !child.getParentId().equals("")) {
				for (Authority parent : authorityTree) {
					if (addSubAuthority(parent, child)) {
						break;
					}
				}
			}
		}
		//权限排序
		Collections.sort(authorityTree);
		for (Authority authority : authorityTree) {
			sortSubAuthority(authority);
		}
	}
	
	/**
	 * 封装Authority的属性
	 * @param authority
	 * @param element
	 */
	private void setProperties(Authority authority, Element element){
		authority.setId(element.attributeValue("id"));
		authority.setParentId(element.attributeValue("parentId"));
		authority.setMenuId(element.attributeValue("menuId"));
		authority.setName(element.attributeValue("name"));
		authority.setDesc(element.attributeValue("desc"));
	}
	
	/**
	 * 递归：将子权限添加到父权限中
	 * @param parent
	 * @param child
	 * @return
	 */
	private boolean addSubAuthority(Authority parent, Authority child) {
		if (parent.getId().equals(child.getParentId())) {
			return parent.addAuthority(child);
		} else {
			for (Authority p : parent.getSubAuthority()) {
				addSubAuthority(p, child);
			}
		}
		return false;
	}
	
	/**
	 * 递归：对子权限排序
	 * @param authority
	 */
	private void sortSubAuthority(Authority authority){
		if (authority.getSubAuthority() != null && authority.getSubAuthority().size() > 0) {
			Collections.sort(authority.getSubAuthority());
			for (Authority a : authority.getSubAuthority()) {
				sortSubAuthority(a);
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			AuthorityTree tree = new AuthorityTree();
			LoadAuthority load = new LoadAuthority();
			load.loadAuthority(tree);
			List<Authority> as = tree.getAuthorities();
			List<Authority> at = tree.getAuthoritiesTree();
			System.out.println("加载完毕...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
