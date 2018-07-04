package com.mood.movie.manager;

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
public class MovieManager {

	@Autowired
	@Qualifier("movieJdbcTemplate")
    protected JdbcTemplate movieJdbcTemplate;
   
	public List<Map<String, Object>> getMovies() {
		String id_user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return DAOSelect.select("SELECT m.* FROM movie m INNER JOIN user_movie um ON m.id_movie = um.id_movie WHERE um.id_user = '" + id_user + "'", movieJdbcTemplate);		
	}
}
