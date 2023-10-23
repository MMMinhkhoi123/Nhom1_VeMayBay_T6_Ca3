package com.flight.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flight.entity.user;
import com.flight.repository.role_repo;
import com.flight.repository.user_repo;

public class custom_userdeail implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	user user;
	
    user_repo user_reponsitory;
    
    role_repo role_repository;
    
    
    public custom_userdeail(user user, user_repo user_repo,role_repo role_repo) {
    	this.user = user;
    	this.user_reponsitory = user_repo;
    	this.role_repository = role_repo;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();	
		Optional<user> user = user_reponsitory.findonebynemail(getUsername());

		if (user.isPresent()) {
			role_repository.findallbyid(user.get().getId())
			.forEach( e -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(e.getName()));
			});
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
