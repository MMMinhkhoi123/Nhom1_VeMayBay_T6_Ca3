package com.flight.service.impl;

import java.io.Console;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flight.entity.role;
import com.flight.entity.user;
import com.flight.reponse.history_reponse;
import com.flight.reponse.httpreponse;
import com.flight.reponse.login_reponse;
import com.flight.reponse.pass_reponse;
import com.flight.reponse.register_reponse;
import com.flight.reponse.token_reponse;
import com.flight.reponse.verify_reponse;
import com.flight.repository.customer_repo;
import com.flight.repository.parent_repo;
import com.flight.repository.role_repo;
import com.flight.repository.user_repo;
import com.flight.request.forget_request;
import com.flight.request.login_request;
import com.flight.request.pass_request;
import com.flight.request.register_request;
import com.flight.request.token_request;
import com.flight.request.verify_request;
import com.flight.security.GeneredJWT;
import com.flight.security.config_userdetail_service;
import com.flight.service.I_user;

@Service
public class user_service  implements I_user {

	@Autowired
	user_repo user_repository;
	
	@Autowired
	role_repo role_repository;
	
	@Autowired
	GeneredJWT generatorJWT;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	jwt_service jwtservice;

	@Autowired
	emailservice emailservice;
	
	@Autowired
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public ResponseEntity<register_reponse> save(register_request register) {
		user user = new user();
		Set<role> array_role = new HashSet<role>();
		register_reponse reponse = new register_reponse();
		user.setEmail(register.getGmail());
		user.setFullname(register.getFullname());
		user.setPhone(register.getPhone());
		user.setPassword(passwordEncoder.encode(register.getPassword()));
		user.setDate_create(null);
		user.setDate_modify(null);
		Optional<role> roles = role_repository.findById((long) 1);
		if (roles.isPresent()) {
			array_role.add(roles.get());
			user.setRoles(array_role);
		}
		//check validate 
		if(user_repository.findonebynemail(register.getGmail()).isPresent()) {
	       reponse.setMess("email is already use !");
	       reponse.setStatus(false);
	       return new ResponseEntity<register_reponse>(reponse, HttpStatus.BAD_REQUEST);
		} else {
		    user_repository.save(user); 
		    reponse.setMess("register success!");
		    reponse.setStatus(true);
		}
	  return new ResponseEntity<register_reponse>(reponse, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<login_reponse> login(login_request login) {
            login_reponse datareponse = new login_reponse();
		 if(user_repository.findonebynemail(login.getEmail()).isEmpty())
		 {
			 datareponse.setMess("User not exist");
		 } else {
			user users = user_repository.findonebynemail(login.getEmail()).get();
			if (passwordEncoder.matches(login.getPassword(),users.getPassword()) == false) {
				datareponse.setMess("password is not correct");
			}else {
				 Authentication author = manager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
				 SecurityContextHolder.getContext().setAuthentication(author);
			     Collection<SimpleGrantedAuthority> listauthors = new ArrayList<SimpleGrantedAuthority>();
			         user user =  user_repository.findonebynemail(login.getEmail()).get();
				     Long idUser = user.getId();
					 ArrayList<role> arrayrole = role_repository.findallbyid(idUser);	 
					 arrayrole.forEach((e) -> listauthors.add(new SimpleGrantedAuthority(e.getName())));
					 String jsonToken = generatorJWT.generateToken(author, listauthors);
					 String refeshjsonToken = generatorJWT.refeshToken(author, listauthors);
					 List<String> roles = new ArrayList<String>();
					 users.getRoles().forEach((e) -> {
						 roles.add(e.getName());
					 }) ;
					 jwtservice.save(refeshjsonToken, users);
					 datareponse.setName_role(roles);
					 datareponse.setAccessToken(jsonToken);
				     datareponse.setMess("valid");
				 return  new ResponseEntity<>(datareponse,HttpStatus.OK);
			}
		 }	
		 return  new ResponseEntity<>( datareponse,HttpStatus.BAD_REQUEST);
	}

	String key = "MabcD";
	String mess;
	@Override
	public ResponseEntity<forget_request> forgetpass(String email) {
		forget_request data = new forget_request();
		data.setEmail(email);
		Date time = new Date();
		if(!user_repository.findonebynemail(email).isPresent()) {
			data.setMess("email is not exist");
			return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
		} else {
			String x = String.valueOf(time.getTime()).substring(6, 12);
		    key = key + x;
			data.setMess("send success");
			emailservice.sendEmail(email,"Produce pass","New Random Pass For You :" + key);
			user_repository.update_pass(passwordEncoder.encode(key), email);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@ModelAttribute
	public void model() {
		
	}
	
	@Autowired
	private GeneredJWT jwtGenerator;
	@Autowired
	private config_userdetail_service userdetail_service;
	@Override
	public ResponseEntity<token_reponse> settoken(String token) {
		token_reponse reponse = new token_reponse();
		String username = jwtGenerator.getUsernameFromJWT(token);
		user userx = user_repository.findonebynemail(username).get();
		reponse.setEmail(username);
		reponse.setFullname(userx.getFullname());
		List<String> role = new ArrayList<String>();
		userx.getRoles().forEach((e) -> {
			role.add(e.getName());
		});
		reponse.setRole(role);
		reponse.setPhone(userx.getPhone());
		reponse.setToken(token);
		return new ResponseEntity<>(reponse,HttpStatus.OK);
	}
	@Override
	public ResponseEntity<pass_reponse> changepass(pass_request passrequest) {
		pass_reponse pass = new pass_reponse();
		String username = jwtGenerator.getUsernameFromJWT(passrequest.getToken());
		user userx = user_repository.findonebynemail(username).get();
		if (!passwordEncoder.matches(passrequest.getPassold(), userx.getPassword())) {
			pass.setMess("Password old incorrect !");
			pass.setStatus(false);
			pass.setToken(passrequest.getToken());
			return new ResponseEntity<>(pass, HttpStatus.NOT_FOUND);
		}
		user_repository.update_pass(passwordEncoder.encode(passrequest.getPassnew()), username);
		pass.setMess("Password change success !");
		pass.setStatus(true);
		pass.setToken(passrequest.getToken());
		return new ResponseEntity<>(pass, HttpStatus.OK);
	}

	@Autowired
	parent_repo parentreponsetory;

	@Autowired
	customer_repo customerreposetory;
	
	@Override
	public ResponseEntity< List<history_reponse>> gethistory(String token) {
		List<history_reponse> data_reponse = new ArrayList<history_reponse>();
		String username = jwtGenerator.getUsernameFromJWT(token);
		user userx = user_repository.findonebynemail(username).get();
		 parentreponsetory.gethistory(userx.getId()).forEach((e) -> {
			 customerreposetory.findallbyidbill(e.getId()).get().forEach((ex) -> {
				 history_reponse elemyent = new history_reponse();
				 elemyent.setImgtrademark(ex.getTicker().getFlight().getAirplane().getTrademark().getImg());
				 elemyent.setLocationfish(ex.getTicker().getFlight().getLocation_complete().getName());
				 elemyent.setLocationgo(ex.getTicker().getFlight().getLocation().getName());
				 elemyent.setNametrademark(ex.getTicker().getFlight().getAirplane().getTrademark().getName());
				 elemyent.setPrice(ex.getChair().getType_chair().getPrice() + ex.getTicker().getPrice());
				 elemyent.setTimego(ex.getTicker().getFlight().getDate_departure().getTime());
				 elemyent.setTimefish(ex.getTicker().getFlight().getDate_complete().getTime());
				 elemyent.setTimemua(e.getDate_create().getTime());
				 data_reponse.add(elemyent);
			 });
		 });
		return new ResponseEntity<>(data_reponse, HttpStatus.OK);
	}


	
}
