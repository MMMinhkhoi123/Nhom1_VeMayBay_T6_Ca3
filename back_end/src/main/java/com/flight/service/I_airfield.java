package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.airflied_reponse;
import com.flight.reponse.getdata.airfield_data;
import com.flight.request.airfield_request;

public interface I_airfield {
	public ResponseEntity<airflied_reponse>  save(airfield_request airfield);
	public ResponseEntity<airflied_reponse>  remove(Long id);
	public ResponseEntity<airfield_data> get(int page, int limit, String key);
	public ResponseEntity< List<airfield_request>> get();
}
