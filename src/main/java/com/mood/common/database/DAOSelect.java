package com.mood.common.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class DAOSelect {

	public static List<Map<String, Object>> select(String sql, JdbcTemplate jdbcTemplate) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		jdbcTemplate.query(sql, new ListExtractor(list));
		return list;
	}

	public static Map<String, Object> selectOne(String sql, JdbcTemplate jdbcTemplate) {
		Map<String, Object> map = new HashMap<String, Object>();
		jdbcTemplate.query(sql, new MapExtractor(map));
		return map;
	}

}
