package com.modi.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BasicSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Define roles
	public static enum ROLE {
		USER, ADMIN
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication() // Add in memory authentication.
				.withUser("user").password("{noop}user").roles(ROLE.USER.toString()) // Create a user with USER role
				.and() // Chaining
				// Create an admin with both ADMIN and USER roles
				.withUser("admin").password("{noop}admin").roles(ROLE.USER.toString(), ROLE.ADMIN.toString());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic() // Configure HTTP Basic authentication
				.and() // Chaining
				.authorizeRequests() // add restrictions based on URLs
				// GET for USER role only
				.antMatchers(HttpMethod.GET, "/book/**").hasRole(ROLE.USER.toString())
				// POST for ADMIN role only
				.antMatchers(HttpMethod.POST, "/book/**").hasRole(ROLE.ADMIN.toString())
				// PUT for ADMIN role only
				.antMatchers(HttpMethod.PUT, "/book/**").hasRole(ROLE.ADMIN.toString())
				// DELETE for ADMIN role only
				.antMatchers(HttpMethod.DELETE, "/book/**").hasRole(ROLE.ADMIN.toString()) //
				.and() //
				.csrf().disable() //
				.formLogin().disable();
	}
}
