package com.mood.artist.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.mood.common.database.DAOSelect;

@Repository
public class ArtistManager {

	@Autowired
	@Qualifier("artistJdbcTemplate")
    protected JdbcTemplate artistJdbcTemplate;
   
	public List<Map<String, Object>> getArtists() {
		String id_user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return DAOSelect.select("SELECT a.* FROM artist a INNER JOIN user_artist ua ON a.id_artist = ua.id_artist WHERE ua.id_user = '" + id_user + "'", artistJdbcTemplate);		
	}
}
