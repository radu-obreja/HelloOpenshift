package com.mood.common.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -6916228765834822304L;
	private User principal;
	private Map<String, Object> credentials;

	public JWTAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}

	public JWTAuthenticationToken(User principal, Map<String, Object> credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

}
