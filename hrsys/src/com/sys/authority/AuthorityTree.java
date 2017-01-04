package com.sys.authority;

import java.util.ArrayList;
import java.util.List;

/**
 * È¨ÏÞÊ÷Àà
 * @author Administrator
 *
 */
public class AuthorityTree {
	
	private LoadAuthority loadAuthority;
	
	private List<Authority> authorities = null;
	
	private List<Authority> authoritiesTree = null;
	
	public AuthorityTree() {
		authorities = new ArrayList<Authority>();
		authoritiesTree = new ArrayList<Authority>();
	}
	
	public LoadAuthority getLoadAuthority() {
		return loadAuthority;
	}

	public void setLoadAuthority(LoadAuthority loadAuthority) {
		this.loadAuthority = loadAuthority;
	}

	public List<Authority> getAuthoritiesTree() {
		return authoritiesTree;
	}

	public void setAuthoritiesTree(List<Authority> authoritiesTree) {
		this.authoritiesTree = authoritiesTree;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public void init() {
		loadAuthority.loadAuthority(this);
	}
}
