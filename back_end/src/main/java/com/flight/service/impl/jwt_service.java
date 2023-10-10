package com.flight.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entity.auth_jwt;
import com.flight.entity.user;
import com.flight.repository.jwt_repo;
import com.flight.security.Time_contranst;
import com.flight.service.I_jwt;

@Service
public class jwt_service implements I_jwt {

	private Time_contranst time;
	
	@Autowired
	jwt_repo jwtrepository;
	
	@Override
	public void save(String jsontoken, user user) {
		auth_jwt data = new auth_jwt();
		data.setAccToken(jsontoken);
		data.setUser(user);
		Date dates = new Date();
		data.setExpiration(dates.getTime() + time.time_refeash_Token);
		jwtrepository.save(data);
	}

}
