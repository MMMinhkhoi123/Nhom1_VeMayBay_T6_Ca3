package com.flight.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class GeneredJWT {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	private Time_contranst time;
	
	public String generateToken(Authentication authentication,Collection<SimpleGrantedAuthority> author) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + time.time_Token);

		Claims claims = Jwts.claims().setSubject(authentication.getName());
		      claims.put("role", author.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) );
				
		String token = Jwts.builder()
				.setSubject(username)
				.signWith(key,SignatureAlgorithm.HS512)
				.setClaims(claims)
				.setExpiration(expireDate)
				.compact();
		return token;
	}
	
	
	public String refeshToken(Authentication authentication,Collection<SimpleGrantedAuthority> author) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + time.time_refeash_Token);

		Claims claims = Jwts.claims().setSubject(authentication.getName());
		      claims.put("role", author.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) );
				
		String token = Jwts.builder()
				.setSubject(username)
				.signWith(key,SignatureAlgorithm.HS512)
				.setClaims(claims)
				.setExpiration(expireDate)
				.compact();
		return token;
	}
	
	public String getUsernameFromJWT(String token){
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}

	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}

	}
	
	public boolean nefttime(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (Exception ex) {
			throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
		}

	}
	
}
