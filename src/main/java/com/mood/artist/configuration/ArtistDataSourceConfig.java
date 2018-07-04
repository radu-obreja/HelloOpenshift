package com.mood.artist.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ArtistDataSourceConfig {

	@Bean
	@Qualifier("artistDataSource")
	@ConfigurationProperties(prefix="hello-openshift-apps.datasource")
	DataSource artistDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Qualifier("artistJdbcTemplate")
	JdbcTemplate artistJdbcTemplate(@Qualifier("artistDataSource")DataSource artistDataSource) {
		return new JdbcTemplate(artistDataSource);
	}

}