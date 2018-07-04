package com.mood.ui.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(1)
public class JWTAuthenticationConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Qualifier("authenticationDataSource")
	@ConfigurationProperties(prefix="hello-openshift.datasource")
	DataSource authenticationDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("authenticationJdbcTemplate")
	JdbcTemplate authenticationJdbcTemplate(@Qualifier("authenticationDataSource")DataSource authenticationDataSource) {
		return new JdbcTemplate(authenticationDataSource);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence rawPassword) {
	            return rawPassword.toString();
	        }
	        @Override
	        public boolean matches(CharSequence rawPassword, String encodedPassword) {
	            return rawPassword.toString().equals(encodedPassword);
	        }
	    };
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(authenticationDataSource())
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("SELECT id_user AS username, user_password AS password, 1 AS enabled FROM `user` WHERE user_login = ?")
			.authoritiesByUsernameQuery("SELECT ur.id_user AS username, ur.id_role AS authority FROM user_role ur INNER JOIN `user` u ON ur.id_user = u.id_user WHERE u.user_login = ?");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.requestMatchers().antMatchers("/api/authentication/authenticate").and()
		.cors().and()
		.csrf().disable()
		.addFilterAfter(new JWTAuthenticationFilter(authenticationManager(), authenticationJdbcTemplate(authenticationDataSource())), BasicAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}