package com.flight.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flight.reponse.tick_reponse;
import com.flight.reponse.getdata.location_data;
import com.flight.reponse.getdata.tick_datav;
import com.flight.reponse.getdata.ticker_data;

public interface I_ticker {
	public void save(Long idflight,int size, int price);
	public void delete(Long idflight);
	public void update(Long idflight,int size, int price, int sale);
	public ResponseEntity<List<ticker_data>> get();
	public tick_reponse getone(Long id);
	public ResponseEntity<tick_datav>  get(int page, int limit);
}
