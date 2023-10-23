package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.entity.parent_einvoice;
import com.flight.reponse.bill_parent_reponse;
import com.flight.reponse.bill_reponse;

public interface I_parent {
	public parent_einvoice save_lv1();
	public void updateeinvoice(String codepay);
	public void delete(String codepay);
	public ResponseEntity<bill_parent_reponse> get(int page, int limit, String key);
	public ResponseEntity<List<bill_reponse>> get();
}
