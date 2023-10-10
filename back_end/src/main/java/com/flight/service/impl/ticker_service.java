package com.flight.service.impl;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.entity.flight;
import com.flight.entity.ticker;
import com.flight.reponse.tick_reponse;
import com.flight.reponse.getdata.chair_data;
import com.flight.reponse.getdata.location_data;
import com.flight.reponse.getdata.tick_datav;
import com.flight.reponse.getdata.ticker_data;
import com.flight.repository.chair_repo;
import com.flight.repository.flight_repo;
import com.flight.repository.ticker_repo;
import com.flight.request.location_request;
import com.flight.service.I_ticker;

@Service
public class ticker_service implements I_ticker {
	
	@Autowired
	flight_repo flight_reponsitory;
	
	@Autowired
	ticker_repo tick_reposetory;
	
	@Autowired
	chair_repo chair_reponsetory;
	
	@Override
	public void save(Long idflight, int size, int price) {
		flight flightx = flight_reponsitory.findById(idflight).get();
		ticker tickers = new ticker();
		tickers.setPrice(price);
		tickers.setQuality(size);
		tickers.setStatus(0);
		tickers.setPrice_sale(price);
		tickers.setFlight(flightx);
		tick_reposetory.save(tickers);
	}
	@Override
	public void delete(Long idflight) {
		tick_reposetory.deletebyidflight(idflight);
	}
	@Override
	public void update(Long idflight, int size, int price, int sale) {
		flight flightx = flight_reponsitory.findById(idflight).get();	
		ticker tickerold = tick_reposetory.findbyidflight(idflight);
		ticker tickers = new ticker();
		tickers.setPrice(price);
		tickers.setQuality(size);
		tickers.setStatus(0);
		tickers.setId(tickerold.getId());
		tickers.setPrice_sale(sale);
		tickers.setFlight(flightx);
		tick_reposetory.save(tickers);
	}
	
	
	public void setdata(ticker item,ticker_data data) {
		data.setId(item.getId());
		data.setPrice(item.getPrice());
		data.setIdflight(item.getId());
		data.setQuality(item.getQuality());
		data.setIdflight(item.getFlight().getId());
		data.setId_airfield(item.getFlight().getAirfield().getId());
		data.setId_airplane(item.getFlight().getAirplane().getId());
		data.setLocation_complete(item.getFlight().getLocation_complete().getId());
		data.setLocation_departure(item.getFlight().getLocation().getId());
		data.setTimemove(item.getFlight().getTimemove());
		data.setTime_departure(item.getFlight().getDate_departure().getTime());
		data.setTime_complete(item.getFlight().getDate_complete().getTime());
		data.setTrademark(item.getFlight().getAirplane().getTrademark().getId());
		data.setListchair(getchair(item.getFlight().getAirplane().getId()));
		List<Long> ararytypechair = new ArrayList<Long>();
	    chair_reponsetory.findbyidairplane(item.getFlight().getAirplane().getId()).get().forEach((e) -> {
	         if(ararytypechair.contains(e.getType_chair().getId()) == false) {
	         ararytypechair.add(e.getType_chair().getId());
	         }
	    });
	    data.setTypechair(ararytypechair);
	}
	
	@Override
	public ResponseEntity<List<ticker_data>> get() {
		List<ticker_data> list = new ArrayList<ticker_data>();
		tick_reposetory.findAll().forEach((item) -> {
			ticker_data data = new ticker_data(); 
			if(item.getStatus() != 1) {
				setdata(item, data);
				list.add(data);
			}
		});
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	public List<chair_data> getchair(Long idairoplane) {
		List<chair_data> array = new ArrayList<chair_data>();
		chair_reponsetory.findbyidairplane(idairoplane).get().forEach((e) -> {
			chair_data data = new chair_data();
			data.setId(e.getId());
			data.setSide(e.getSide());
			data.setStatus(e.getStatus());
			data.setStt(e.getStt());
			data.setTypechair(e.getType_chair().getId());
			array.add(data);
		});
		return array;
	}
	@Override
	public tick_reponse getone(Long id) {
	 ticker tick = tick_reposetory.findById(id).get();
	 tick_reponse data = new tick_reponse();
	 data.setPrice(tick.getPrice());
	 data.setIdtick(tick.getId());
		return data;
	}
	@Override
	public ResponseEntity<tick_datav> get(int page, int limit) {
		tick_datav tick_reponse = new tick_datav();
		tick_reponse.setPage(page);
		Pageable data = new PageRequest(page -1,limit,Sort.by("id")) {
		};
		List<ticker_data> list = new ArrayList<ticker_data>();
		tick_reposetory.findAll(data).forEach((item) -> {
			ticker_data datas = new ticker_data(); 
			if(item.getStatus() != 1) {
				setdata(item, datas);
				list.add(datas);
			}
		});
		tick_reponse.setList(list);
		tick_reponse.setToltalpage((int) Math.ceil((double) (tick_reposetory.count()) / limit));
		return new ResponseEntity<>(tick_reponse,HttpStatus.OK);
	}
}
