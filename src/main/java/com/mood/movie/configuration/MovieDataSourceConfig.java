package com.mood.movie.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MovieDataSourceConfig {

	@Bean
	@Qualifier("movieDataSource")
	@ConfigurationProperties(prefix="hello-openshift-apps.datasource")
	DataSource movieDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Qualifier("movieJdbcTemplate")
	JdbcTemplate movie(@Qualifier("movieDataSource")DataSource movieDataSource) {
		return new JdbcTemplate(movieDataSource);
	}

}