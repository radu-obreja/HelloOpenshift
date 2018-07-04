package com.mood.ui.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mood.common.util.PageUtils;

@Controller
public class UIController {

	@GetMapping("/")
	String root(ModelMap modal) {
		PageUtils.setPermissions(modal, "index");
		return "index";
	}

	@GetMapping("/index")
	String index(ModelMap modal) {
		PageUtils.setPermissions(modal, "index");
		return "index";
	}

	@GetMapping("/login")
	String login(ModelMap modal) {
		PageUtils.setPermissions(modal, "login");
		return "login";
	}
	
	@GetMapping("/home")
	String home(ModelMap modal) {
		PageUtils.setPermissions(modal, "home");
		return "home";
	}
	
	@GetMapping("/movies")
	@PreAuthorize("hasAuthority('movie')")
	String movie(ModelMap modal) {
		PageUtils.setPermissions(modal, "movies");
		return "movies";
	}

	@GetMapping("/artists")
	@PreAuthorize("hasAuthority('artist')")
	String artist(ModelMap modal) {
		PageUtils.setPermissions(modal, "artists");
		return "artists";
	}
}