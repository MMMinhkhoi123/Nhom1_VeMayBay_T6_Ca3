package com.flight.service;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.flight.reponse.location_reponse;
import com.flight.reponse.getdata.location_data;
import com.flight.request.location_request;

public interface I_location {

	public ResponseEntity<location_reponse>  save(location_request locattion, MultipartFile muti);
	public ResponseEntity<location_reponse>  remove(Long id);
	public ResponseEntity<location_data>  get(int page, int limit, String key);
	public ResponseEntity<List<location_request>>  get();
}
