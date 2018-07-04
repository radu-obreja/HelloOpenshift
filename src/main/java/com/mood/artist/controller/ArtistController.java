package com.mood.artist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.mood.artist.manager.ArtistManager;

@RestController
@RequestMapping(value = "/api/artist")
@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class ArtistController {

	@Autowired
    private ArtistManager artistManager;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('artist')")
	public @ResponseBody Map<String, Object> getList(@RequestBody Map<String, Object> jreq) {
		Map<String,Object> resp = new HashMap<String, Object>();
		resp.put("artists", artistManager.getArtists());
		return resp;
	}

}