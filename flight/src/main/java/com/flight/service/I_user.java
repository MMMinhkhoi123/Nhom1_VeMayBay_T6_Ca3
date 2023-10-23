package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.request.forget_request;
import com.flight.request.login_request;
import com.flight.request.pass_request;
import com.flight.request.register_request;
import com.flight.request.verify_request;
import com.flight.reponse.history_reponse;
import com.flight.reponse.login_reponse;
import com.flight.reponse.pass_reponse;
import com.flight.reponse.register_reponse;
import com.flight.reponse.token_reponse;
import com.flight.reponse.verify_reponse;

public interface I_user {
	public ResponseEntity<register_reponse> save(register_request register);
	public ResponseEntity<login_reponse> login(login_request login);
	public ResponseEntity<forget_request> forgetpass(String email);
	public ResponseEntity<token_reponse> settoken(String token);
	public ResponseEntity<pass_reponse> changepass(pass_request passrequest);
	public ResponseEntity<List<history_reponse>> gethistory(String token);
}
