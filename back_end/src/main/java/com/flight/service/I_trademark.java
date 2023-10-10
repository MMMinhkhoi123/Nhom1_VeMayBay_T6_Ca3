package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.trademark_reponse;
import com.flight.reponse.getdata.trademark_data;
import com.flight.request.trademark_request;

public interface I_trademark {

	public ResponseEntity<trademark_reponse>  save(trademark_request trademarks);
	public ResponseEntity<trademark_reponse>  remove(Long id);
	public ResponseEntity<trademark_data>  get(int page, int limit, String key);
	public ResponseEntity<List<trademark_request>> get();
}
