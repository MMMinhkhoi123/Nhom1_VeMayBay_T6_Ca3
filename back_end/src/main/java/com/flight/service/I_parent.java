package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.entity.parent_einvoice;
import com.flight.reponse.history_reponse;
import com.flight.request.einvoice_request;

public interface I_parent {

	public parent_einvoice save_lv1();
	public void updateeinvoice(String codepay);
	public void delete(String codepay);
}
