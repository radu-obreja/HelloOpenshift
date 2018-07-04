package com.mood.ui.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.mood.common.database.DAOSelect;
import com.mood.common.security.JWTConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuthenticationFilter extends GenericFilterBean {
	
	private AuthenticationManager authenticationManager;
	private JdbcTemplate authenticationJdbcTemplate;

	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JdbcTemplate authenticationJdbcTemplate) {
		this.authenticationManager = authenticationManager;
		this.authenticationJdbcTemplate = authenticationJdbcTemplate;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		Map<String, Object> creds = new HashMap<String, Object>();
		creds.put("username", ((HttpServletRequest)req).getHeader("username"));
		creds.put("password", ((HttpServletRequest)req).getHeader("password"));
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.get("username"), creds.get("password"), new ArrayList<>()));
		if (auth.isAuthenticated()) {
			User user = (User)auth.getPrincipal();
			List<String> authorities = new ArrayList<String>();
			for (GrantedAuthority authority : user.getAuthorities()) {
				authorities.add(authority.getAuthority());
			}
			
			Claims claims = Jwts.claims().setSubject(user.getUsername());
			claims.put("authorities", user.getAuthorities());
			claims.put("user", DAOSelect.selectOne("SELECT * FROM `user` WHERE id_user = '" + user.getUsername() + "'", authenticationJdbcTemplate));

			String token = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + JWTConfiguration.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, JWTConfiguration.SECRET.getBytes()).compact();

			Cookie authorizationCookie = new Cookie(JWTConfiguration.AUTHORIZATION_HEADER_NAME, JWTConfiguration.TOKEN_PREFIX + token);
			authorizationCookie.setMaxAge(JWTConfiguration.EXPIRATION_TIME * 2);
			authorizationCookie.setPath("/");
			((HttpServletResponse) res).addCookie(authorizationCookie);

		}
		chain.doFilter(req, res);
	}

}