package com.flight.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flight.entity.user;
import com.flight.repository.role_repo;
import com.flight.repository.user_repo;

@Service
public class config_userdetail_service implements UserDetailsService {

	@Autowired
	user_repo user_reponsetory;
	

	@Autowired
	role_repo role_repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user userx =  new user();
	    user_reponsetory.findonebynemail(username).orElseThrow(() ->
		new UsernameNotFoundException("not find username !!!!! "));
	    userx = user_reponsetory.findonebynemail(username).get();
		return new custom_userdeail(userx,user_reponsetory,role_repository);
	}

	
}
