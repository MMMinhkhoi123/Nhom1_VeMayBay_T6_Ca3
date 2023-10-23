package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.flight_reponse;
import com.flight.reponse.getdata.flight_data;
import com.flight.reponse.getdata.location_data;
import com.flight.request.flight_request;

public interface I_flight {
	public ResponseEntity<flight_reponse>  save(flight_request flight);
	public ResponseEntity<flight_reponse>  update(flight_request flight);
	public ResponseEntity<flight_data>  get(int page, int limit);
	public ResponseEntity<List<flight_request>>  get();
	public void delete(Long Id);
}
