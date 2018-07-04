package com.mood.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		String token = null;
		
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(JWTConfiguration.AUTHORIZATION_HEADER_NAME)) {
					token = cookie.getValue();
					break;
				}
			}
		}

		if (token == null || !token.startsWith(JWTConfiguration.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		AbstractAuthenticationToken authentication = getAuthentication(token);
		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(req, res);
	}

	@SuppressWarnings("unchecked")
	private JWTAuthenticationToken getAuthentication(String token) {
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(JWTConfiguration.SECRET.getBytes()).parseClaimsJws(token.replace(JWTConfiguration.TOKEN_PREFIX, "")).getBody();

			String id_user = claims.getSubject();
			List<Map<String, Object>> authorities = (List<Map<String, Object>>)claims.get("authorities");
			Map<String, Object> user = (Map<String, Object>)claims.get("user");
			List<JWTAuthority> grantedAuthorities = new ArrayList<JWTAuthority>();
			for (Map<String, Object> authority : authorities) {
				grantedAuthorities.add(new JWTAuthority((String)authority.get("authority")));
			}

			if (id_user != null) {
				return new JWTAuthenticationToken(new User(id_user, "none", grantedAuthorities), user, grantedAuthorities);
			}
			
			return null;
		}
		
		return null;
	}
}