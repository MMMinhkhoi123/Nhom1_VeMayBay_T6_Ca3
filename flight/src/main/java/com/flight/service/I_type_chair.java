package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.type_chair_reponse;
import com.flight.reponse.getdata.typechair_data;
import com.flight.request.type_chair_request;

public interface I_type_chair {
	public ResponseEntity<type_chair_reponse>  save(type_chair_request chair);
	public ResponseEntity<type_chair_reponse>  remove(Long id);
	public ResponseEntity<List<typechair_data>>  get();
}
