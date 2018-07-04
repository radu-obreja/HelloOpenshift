package com.mood.common.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MapExtractor implements ResultSetExtractor<Map<String, Object>> {

	private Map<String, Object> map;
	
	public MapExtractor(Map<String, Object> map) {
		this.map = map;
	}
	
	@Override
	public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs != null && rs.next()) {
			for (int i=1; i <= rs.getMetaData().getColumnCount(); i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				try {
					if (rs.getObject(i) instanceof Date) {
						map.put(columnName.toLowerCase(), ((Date)rs.getObject(i)).toString());
					} else {
						map.put(columnName.toLowerCase(), rs.getObject(i));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}