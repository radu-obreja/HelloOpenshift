package com.mood.common.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ListExtractor implements ResultSetExtractor<Map<String, Object>> {

	private List<Map<String, Object>> list;
	
	public ListExtractor(List<Map<String, Object>> list) {
		this.list = list;
	}
	
	@Override
	public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs != null) {
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
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
				list.add(map);
			}
		}
		return null;
	}

}