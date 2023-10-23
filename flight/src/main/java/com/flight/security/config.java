package com.flight.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class config {
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		List<String> methods = new ArrayList<String>();
		methods.add("GET");
		methods.add("POST");
		methods.add("DELETE");
		methods.add("PUT");
		List<String> Origins = new ArrayList<String>();
		Origins.add("http://localhost:8080");
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Origins);
		configuration.setAllowedMethods(methods);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(e -> e.disable());
		http.authorizeHttpRequests((req) -> req.requestMatchers("*/test").hasAuthority("checker").anyRequest().permitAll());
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	 }


	@Bean
    public  authenfilter  jwtAuthenticationFilter() {
        return new authenfilter();
    }
	
}
