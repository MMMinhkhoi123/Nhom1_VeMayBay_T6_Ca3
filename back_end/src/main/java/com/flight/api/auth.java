package com.flight.api;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.reponse.login_reponse;
import com.flight.reponse.pass_reponse;
import com.flight.reponse.register_reponse;
import com.flight.reponse.token_reponse;
import com.flight.reponse.verify_reponse;
import com.flight.request.forget_request;
import com.flight.request.login_request;
import com.flight.request.pass_request;
import com.flight.request.register_request;
import com.flight.request.token_request;
import com.flight.request.verify_request;
import com.flight.service.impl.user_service;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j
public class auth {
	@Autowired
	user_service userservice;
	
	@PostMapping("/register")
	public ResponseEntity<register_reponse> register(@RequestBody Optional<register_request> registers){
		return userservice.save(registers.get());
	}
	@PostMapping("/login")
	public ResponseEntity<login_reponse> login(@RequestBody Optional<login_request> login){
		return userservice.login(login.get());
	}
	@GetMapping("/token")
	public ResponseEntity<token_reponse> configtoken(@RequestParam("tokens") Optional<String> token){
		return userservice.settoken(token.get());
	}

	@GetMapping("/pass")
	public ResponseEntity<forget_request> foreget(@RequestParam("email") Optional<String> email){
		return userservice.forgetpass(email.get());
	}
	
	@PutMapping("/pass")
	public ResponseEntity<pass_reponse> changepass(@RequestBody Optional<pass_request> pass){
		return userservice.changepass(pass.get());
	}
}
