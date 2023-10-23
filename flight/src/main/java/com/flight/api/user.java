package com.flight.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.reponse.customer_reponse;
import com.flight.reponse.history_reponse;
import com.flight.request.customer_request;
import com.flight.request.email_request;
import com.flight.service.impl.customer_service;
import com.flight.service.impl.emailservice;
import com.flight.service.impl.parent_service;
import com.flight.service.impl.user_service;



@RestController
@RequestMapping("/user")
@CrossOrigin
public class user {
	@Autowired
	customer_service sustomerservice;
	
	@Autowired
	emailservice emailservice;
	
	@Autowired
	parent_service parent_service;
	
	@Autowired
	user_service userservice;
	
	@PostMapping("/info")
	public ResponseEntity<customer_reponse> create(@RequestBody Optional<List<customer_request>> request) {
		return sustomerservice.save(request.get());
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<history_reponse>> gethistory(@RequestParam("token") Optional<String> token) {
	return userservice.gethistory(token.get());
	}
	
	
	@PostMapping("/email")
	public String sendmail(@RequestBody Optional<email_request> data) {
		return "send mail success";
	}
	
}
