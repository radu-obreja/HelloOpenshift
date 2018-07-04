package com.mood.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.mood.common.security.JWTConfiguration;

@RestController
@RequestMapping(value = "/api/authentication")
@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class UIControllerREST {

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> authenticate(@RequestBody Map<String, Object> jreq) {
		Map<String,Object> resp = new HashMap<String, Object>();
		return resp;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> logout(@RequestBody Map<String, Object> jreq, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(JWTConfiguration.AUTHORIZATION_HEADER_NAME)) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					httpResponse.addCookie(cookie);
					break;
				}
			}
		}
		Map<String,Object> resp = new HashMap<String, Object>();
		return resp;
	}
	
}