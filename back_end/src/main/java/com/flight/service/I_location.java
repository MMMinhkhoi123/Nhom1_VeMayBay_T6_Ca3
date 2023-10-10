package com.flight.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.location_reponse;
import com.flight.reponse.getdata.location_data;
import com.flight.request.location_request;

public interface I_location {

	public ResponseEntity<location_reponse>  save(location_request locattion);
	public ResponseEntity<location_reponse>  remove(Long id);
	public ResponseEntity<location_data>  get(int page, int limit, String key);
	public ResponseEntity<List<location_request>>  get();
}
