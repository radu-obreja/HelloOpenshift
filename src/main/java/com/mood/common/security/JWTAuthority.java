package com.mood.common.security;

import org.springframework.security.core.GrantedAuthority;


public class JWTAuthority implements GrantedAuthority {
	
	private static final long serialVersionUID = 3191456342692912291L;
	
	private String authority;
	
	public JWTAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	public String toString() {
		return this.authority;
	}
	
}
