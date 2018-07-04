package com.mood.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;

public class PageUtils {

	@SuppressWarnings("unchecked")
	public static void setPermissions(ModelMap modal, String page) {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Collection<GrantedAuthority> authorities = user.getAuthorities();
			Set<String> roles = new HashSet<String>();
			for (GrantedAuthority a : authorities) {
				roles.add(a.getAuthority());
			}
			modal.put("roles", roles);
			modal.put("user", (HashMap<String, Object>)SecurityContextHolder.getContext().getAuthentication().getCredentials());
		} else {
			modal.put("roles", new HashSet<String>());
			modal.put("user", new HashMap<String, Object>());
		}
		modal.put("page", page);
	}
	
}
