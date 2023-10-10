package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.customer_reponse;
import com.flight.reponse.getdata.customer_data;
import com.flight.request.customer_request;

public interface I_customer  {
	public ResponseEntity<customer_reponse>  save(List<customer_request> customer);
	public  void  update_status(Long id);
	public customer_request get(Long idcus);
	public ResponseEntity<customer_data> gets(int page, int limit, String key);
}
