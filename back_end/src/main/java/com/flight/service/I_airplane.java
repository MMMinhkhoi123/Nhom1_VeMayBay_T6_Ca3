package com.flight.service;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.flight.reponse.airplane_reponse;
import com.flight.reponse.getdata.airplane_datav;
import com.flight.reponse.getdata.airplane_date;
import com.flight.request.airplane_request;

public interface I_airplane {
	public ResponseEntity<airplane_reponse>  save(airplane_request airplane);
	public ResponseEntity<airplane_reponse>  remove(Long id);
	public ResponseEntity<airplane_reponse>  update(airplane_request airplane);
	public ResponseEntity<airplane_datav>  get(int page, int limit, String key);
	public ResponseEntity<List<airplane_date>> get();
}